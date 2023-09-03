package com.icaroerasmo.seafood.core.listeners;

import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.util.Cleaners;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ItemMongoListener extends AbstractMongoEventListener<Item> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Item> event) {
        Cleaners.clean(event.getSource());
        super.onBeforeConvert(event);
    }
}
