package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import com.icaroerasmo.seafood.core.util.TestMassUtil;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class AddressModelTest extends SeafoodCoreApplicationTests {
    @Test
    void addressModelTest() {
        Address address = TestMassUtil.address();
        assertNotNull("address is null", address.getAddress());
        assertNotNull("address type is null", address.getAddressType());
        assertNotNull("address complement is null", address.getComplement());
        assertNotNull("address number is null", address.getNumber());
    }
}
