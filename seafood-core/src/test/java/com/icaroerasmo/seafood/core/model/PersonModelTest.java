package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import com.icaroerasmo.seafood.core.util.TestMassUtil;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class PersonModelTest extends SeafoodCoreApplicationTests {
    void personModelTest() {
        Person person = TestMassUtil.person();
        assertNotNull("person name is null", person.getName());
        assertNotNull("person documentNo is null", person.getDocumentNo());
        assertNotNull("person phone is null", person.getPhone());
        assertNotNull("person type is null", person.getPersonType());
        assertNotNull("person email is null", person.getEmail());
        assertNotNull("person address list is null", person.getAddresses());
    }
}
