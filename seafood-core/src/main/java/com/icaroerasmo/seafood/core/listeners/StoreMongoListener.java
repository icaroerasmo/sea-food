package com.icaroerasmo.seafood.core.listeners;

import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.util.Cleaners;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StoreMongoListener extends AbstractMongoEventListener<Store> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Store> event) {
        Cleaners.clean(event.getSource());
        super.onBeforeConvert(event);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Store> event) {
        log.info("onAfterSave({}, {})", event.getSource(), event.getDocument());
        super.onAfterSave(event);
    }
}
