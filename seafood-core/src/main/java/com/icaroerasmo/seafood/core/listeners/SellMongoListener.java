package com.icaroerasmo.seafood.core.listeners;

import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.util.Cleaners;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SellMongoListener extends AbstractMongoEventListener<Sell> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Sell> event) {
        Cleaners.clean(event.getSource());
        super.onBeforeConvert(event);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Sell> event) {
        log.info("onAfterSave({}, {})", event.getSource(), event.getDocument());
        super.onAfterSave(event);
    }
}
