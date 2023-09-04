package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class SellModelTest extends SeafoodCoreApplicationTests {
    @Test
    void sellModelTest() {
        Sell sell = new Sell();
        sell.setId("1");
        sell.setBuyer(new User());
        sell.setStore(new Store());
        sell.setItems(Arrays.asList(new Item()));
        sell.setCreatedAt(Instant.now());
        sell.setUpdatedAt(Instant.now());

        assertNotNull("sell id is null", sell.getId());
        assertNotNull("sell buyer is null", sell.getBuyer());
        assertNotNull("sell store is null", sell.getStore());
        assertNotNull("sell items is null", sell.getItems());
        assertNotNull("sell createdAt is null", sell.getCreatedAt());
        assertNotNull("sell updatedAt is null", sell.getUpdatedAt());
    }
}
