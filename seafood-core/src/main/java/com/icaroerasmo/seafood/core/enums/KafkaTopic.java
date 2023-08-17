package com.icaroerasmo.seafood.core.enums;

import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.model.User;

import java.util.Arrays;

public enum KafkaTopic {
    SAVE_ITEM_REQUEST(KafkaOperation.SAVE, KafkaMessageType.REQUEST, Item.class),
    SAVE_ITEM_RESPONSE(KafkaOperation.SAVE, KafkaMessageType.RESPONSE, Item.class),
    DELETE_ITEM_REQUEST(KafkaOperation.DELETE, KafkaMessageType.REQUEST, Item.class),
    DELETE_ITEM_RESPONSE(KafkaOperation.DELETE, KafkaMessageType.RESPONSE, Item.class),
    SAVE_SELL_REQUEST(KafkaOperation.SAVE, KafkaMessageType.REQUEST, Sell.class),
    SAVE_SELL_RESPONSE(KafkaOperation.SAVE, KafkaMessageType.RESPONSE, Sell.class),
    DELETE_SELL_REQUEST(KafkaOperation.DELETE, KafkaMessageType.REQUEST, Sell.class),
    DELETE_SELL_RESPONSE(KafkaOperation.DELETE, KafkaMessageType.RESPONSE, Sell.class),
    SAVE_STORE_REQUEST(KafkaOperation.SAVE, KafkaMessageType.REQUEST, Store.class),
    SAVE_STORE_RESPONSE(KafkaOperation.SAVE, KafkaMessageType.RESPONSE, Store.class),
    DELETE_STORE_REQUEST(KafkaOperation.DELETE, KafkaMessageType.REQUEST, Store.class),
    DELETE_STORE_RESPONSE(KafkaOperation.DELETE, KafkaMessageType.RESPONSE, Store.class),
    SAVE_USER_REQUEST(KafkaOperation.SAVE, KafkaMessageType.REQUEST, User.class),
    SAVE_USER_RESPONSE(KafkaOperation.SAVE, KafkaMessageType.RESPONSE, User.class),
    DELETE_USER_REQUEST(KafkaOperation.DELETE, KafkaMessageType.REQUEST, User.class),
    DELETE_USER_RESPONSE(KafkaOperation.DELETE, KafkaMessageType.RESPONSE, User.class);

    private KafkaOperation operation;
    private KafkaMessageType messageType;
    private Class<?> clazz;

    KafkaTopic(KafkaOperation operation, KafkaMessageType messageType, Class<?> clazz) {
        this.messageType = messageType;
        this.operation = operation;
        this.clazz = clazz;
    }

    public static KafkaTopic findTopic(KafkaOperation operation, KafkaMessageType messageType, Object obj) {
        return Arrays.asList(values()).stream().
                filter(topic ->
                        topic.operation.equals(operation) &&
                        topic.messageType.equals(messageType) &&
                        topic.clazz.getName().equals(obj.getClass().getName())
                ).
                findFirst().orElseThrow(
                        () -> new RuntimeException("not found"));
    }
}
