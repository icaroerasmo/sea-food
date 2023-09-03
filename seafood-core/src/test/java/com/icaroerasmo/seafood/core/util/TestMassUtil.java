package com.icaroerasmo.seafood.core.util;

import com.icaroerasmo.seafood.core.enums.AddressType;
import com.icaroerasmo.seafood.core.enums.PersonType;
import com.icaroerasmo.seafood.core.model.*;

import java.util.Arrays;

public abstract class TestMassUtil {
    public static Address address () {
        Address address = new Address();
        address.setAddressType(AddressType.AVENUE);
        address.setAddress("Tancredo Neves");
        address.setNumber("148");
        address.setComplement("Loja Casas Bahia");
        return address;
    }
    public static Person person() {
        return person(address());
    }
    public static Person person(Address address) {
        Person person = new Person();
        person.setPersonType(PersonType.LEGAL_ENTITY);
        person.setDocumentNo("987654321");
        person.setName("test test");
        person.setEmail("t@t.com");
        person.setPhone("+5571998655665");
        person.setAddresses(Arrays.asList(address));
        return person;
    }
    public static User user() {
        return user(person());
    }
    public static User user(Person person) {
        User user = new User();
        user.setPassword("P@ssw0rd!");
        user.setUserInfo(person);
        return user;
    }
    public static Store store(User user) {
        Store store = new Store();
        store.setScore(4.95F);
        store.setStoreInfo(user);
        return store;
    }
    public static Item item(Store store) {
        Item item = new Item();
        item.setDescription("Item");
        item.setStore(store);
        return item;
    }
}
