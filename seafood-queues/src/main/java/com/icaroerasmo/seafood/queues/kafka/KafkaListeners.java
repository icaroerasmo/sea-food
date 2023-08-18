package com.icaroerasmo.seafood.queues.kafka;

import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.Constants;
import com.icaroerasmo.seafood.queues.services.Service;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KafkaListeners {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private KafkaTemplate<String, KafkaMessageDTO<?>> kafkaTemplate;

    @KafkaListener(id = "${spring.kafka.producer.group-id}", topics = Constants.KAFKA_INPUT_QUEUE)
    public <T> void inputListener(KafkaMessageDTO<T> message) throws Exception {

        log.info("Message {} received", message);

        final T payload = message.getPayload();
        final Service<T> service = getServiceBean(payload);

        switch (message.getOperation()) {
            case SAVE -> service.save(payload).subscribe((newPayload) -> {
                message.setPayload(newPayload);
                kafkaTemplate.send(Constants.KAFKA_OUTPUT_QUEUE, message);
            });
            case DELETE -> service.delete(payload).subscribe();
        }
    }

    private <T> Service<T> getServiceBean(T payload) {
        final String[] beanNamesForType = context.
                getBeanNamesForType(ResolvableType.
                    forClassWithGenerics(Service.class, payload.getClass()));
        final Service<T> service = (Service<T>) context.getBean(beanNamesForType[0]);
        return service;
    }
}
