package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import com.icaroerasmo.seafood.core.util.TestMassUtil;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class ItemModelTest extends SeafoodCoreApplicationTests {
    @Test
    void itemModelTest() {
        Item item = TestMassUtil.item(new Store());
        item.setId("1");
        item.setCreatedAt(Instant.now());
        item.setUpdatedAt(Instant.now());
        assertNotNull("item id is null", item.getId());
        assertNotNull("item description is null", item.getDescription());
        assertNotNull("item store is null", item.getStore());
        assertNotNull("item createdAt is null", item.getCreatedAt());
        assertNotNull("item updatedAt is null", item.getUpdatedAt());
    }
}
