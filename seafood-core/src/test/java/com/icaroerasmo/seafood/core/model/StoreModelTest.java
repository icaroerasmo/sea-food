package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import com.icaroerasmo.seafood.core.util.TestMassUtil;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class StoreModelTest extends SeafoodCoreApplicationTests {
    @Test
    void storeModelTest() {
        Store store = TestMassUtil.store(new User());
        store.setId("1");
        store.setCreatedAt(Instant.now());
        store.setUpdatedAt(Instant.now());

        assertNotNull("store id is null", store.getId());
        assertNotNull("store score is null", store.getScore());
        assertNotNull("store createdAt is null", store.getCreatedAt());
        assertNotNull("store updatedAt is null", store.getUpdatedAt());
    }
}
