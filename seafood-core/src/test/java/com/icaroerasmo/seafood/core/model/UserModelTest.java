package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import com.icaroerasmo.seafood.core.util.TestMassUtil;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class UserModelTest extends SeafoodCoreApplicationTests {
    @Test
    void userModelTest() {
        User user = TestMassUtil.user();
        user.setId("1");
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        assertNotNull("user id is null", user.getId());
        assertNotNull("user userInfo is null", user.getUserInfo());
        assertNotNull("user password is null", user.getPassword());
        assertNotNull("user createdAt is null", user.getCreatedAt());
        assertNotNull("user updatedAt is null", user.getUpdatedAt());
    }
}
