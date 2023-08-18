package com.icaroerasmo.seafood.queues.kafka;

import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.Constants;
import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.item.ItemRepository;
import com.icaroerasmo.seafood.queues.services.Service;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@Component
public class KafkaListeners {

    @Autowired
    private ApplicationContext context;

    @KafkaListener(id = "${spring.kafka.producer.group-id}", topics = Constants.KAFKA_INPUT_QUEUE)
    public <T> void consume(KafkaMessageDTO<T> message) throws Exception {

        log.info("Message {} received", message);

        T payload = message.getPayload();

        final Service<T> service = getServiceBean(payload);

        if(message.getOperation().equals(KafkaOperation.SAVE)) {

            CompletableFuture<T> compFut = new CompletableFuture<>();

            service.save(payload).subscribe((result) -> compFut.complete(result));

            payload = compFut.get();

        } else if(message.getOperation().equals(KafkaOperation.DELETE)) {
            service.delete(payload).subscribe();
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
