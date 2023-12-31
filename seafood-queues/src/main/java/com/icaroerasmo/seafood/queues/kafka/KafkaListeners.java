package com.icaroerasmo.seafood.queues.kafka;

import com.icaroerasmo.seafood.core.dto.ErrorDTO;
import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.Constants;
import com.icaroerasmo.seafood.core.model.DocumentBase;
import com.icaroerasmo.seafood.queues.services.Service;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.UnaryOperator;

@Log4j2
@Component
public class KafkaListeners {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private KafkaTemplate<String, KafkaMessageDTO<?>> kafkaTemplate;
    @RetryableTopic(
            exclude = DataIntegrityViolationException.class,
            backoff = @Backoff(delayExpression = "#{@messagesProperties.getBackoffDelay()}"),
            attempts = "#{@messagesProperties.getNumberOfRetries()}",
            autoCreateTopics = "false")
    @KafkaListener(id = "#{@messagesProperties.getGroupId()}", topics = Constants.KAFKA_INPUT_QUEUE)
    public <T extends DocumentBase> void inputListener(KafkaMessageDTO<T> message) {
        log.info("Message {} received", message);
        process(message);
    }
    @KafkaListener(
            id = "#{@messagesProperties.getGroupId()}"+Constants.KAFKA_DLT_SUFFIX_ID,
            topics = Constants.KAFKA_INPUT_QUEUE+Constants.KAFKA_DLT_SUFFIX)
    public <T extends DocumentBase> void inputListenerDlt(KafkaMessageDTO<T> message) {
        log.info("Message {} could not be processed. Discarding...", message);
    }

    private <T extends DocumentBase> void process(KafkaMessageDTO<T> message) {

        final T payload = message.getPayload();
        final Service<T> service = getServiceBean(payload);

        final UnaryOperator<Mono<?>> doOnError = (mono) ->
             mono.doOnError((ex) -> {
                message.setError(new ErrorDTO(ex));
                kafkaTemplate.send(Constants.KAFKA_OUTPUT_QUEUE, message);
            });

        switch (message.getOperation()) {
            case SAVE -> doOnError.apply(
                                service.save(payload)).
                        doOnSuccess((newPayload) -> {
                            message.setPayload((T) newPayload);
                        }).block();
            case DELETE ->  doOnError.apply(service.delete(payload)).
                        doOnSuccess((resp) -> {}).block();
        }

        kafkaTemplate.send(Constants.KAFKA_OUTPUT_QUEUE, message);
    }

    private <T extends DocumentBase> Service<T> getServiceBean(T payload) {
        final String[] beanNamesForType = context.
                getBeanNamesForType(ResolvableType.
                    forClassWithGenerics(Service.class, payload.getClass()));
        final Service<T> service = (Service<T>) context.getBean(beanNamesForType[0]);
        return service;
    }
}
