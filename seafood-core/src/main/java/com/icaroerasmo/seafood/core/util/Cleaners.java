package com.icaroerasmo.seafood.core.util;

import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.model.User;

public abstract class Cleaners {
    public static void clean(Store store) {
        if(store != null) {
            User storeInfo = store.getStoreInfo();
            if(storeInfo != null) {
                storeInfo.setPassword(null);
            }
        }
    }
    public static void clean(Item item) {
        if(item != null) {
            clean(item.getStore());
        }
    }

    public static void clean(Sell sell) {
        if(sell.getBuyer() != null) {
            User buyer = sell.getBuyer();
            if(buyer != null) {
                buyer.setPassword(null);
                buyer.getUserInfo().setDocumentNo(null);
                buyer.getUserInfo().setPhone(null);
                buyer.getUserInfo().setEmail(null);
                buyer.getUserInfo().setAddresses(null);
            }
        }
        clean(sell.getStore());
    }
}
