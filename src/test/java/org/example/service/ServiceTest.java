package org.example.service;

import org.example.entity.Meter;
import org.example.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @org.junit.jupiter.api.Test
    void registerUser() {
        Service service = new Service();
        assertTrue(service.registerUser("user","pass1"));
        assertFalse(service.registerUser("user1","pass"));
    }

    @org.junit.jupiter.api.Test
    void authenticateUser() {
        Service service = new Service();
        User authuser = service.authenticateUser("user1","pass");
        assertNotNull(authuser);
        User invalidUser = service.authenticateUser("user1", "password");
        assertNull(invalidUser);
    }

    @org.junit.jupiter.api.Test
    void addUserAndWaterInfo() {
        Service service = new Service();
        User testuser = new User("test","testpass");
        service.addUserAndWaterInfo(testuser,"February",10,20);
        Meter meter = service.getMeterForUserAndMonth(testuser, "February");
        assertNotNull(meter);
        assertEquals(10, meter.getHotWaterConsumption());
        assertEquals(20, meter.getColdWaterConsumption());
    }

    @org.junit.jupiter.api.Test
    void getMeterForUserAndMonth() {
        Service service = new Service();
        User user = new User("test","test");
        service.addUserAndWaterInfo(user,"March",20,20);
        Meter meter = service.getMeterForUserAndMonth(user, "March");
        assertNotNull(meter);
        assertEquals(20, meter.getHotWaterConsumption());
        assertEquals(20, meter.getColdWaterConsumption());
        assertNull(service.getMeterForUserAndMonth(new User("nonExistingUser", "password"), "May"));
    }

    @org.junit.jupiter.api.Test
    void getAllUsers() {
        Service service = new Service();
        assertEquals(2,service.getAllUsers().size());
    }

    @org.junit.jupiter.api.Test
    void getUserMeterMap() {
        Service service = new Service();
        User user = new User("test","testpass");
        service.addUserAndWaterInfo(user,"June", 2,4);
        Map<String, Meter> meterMap = service.getUserMeterMap(user);
        assertNotNull(meterMap);
        assertEquals(1, meterMap.size());
        assertTrue(meterMap.containsKey("June"));
    }

    @Test
    void isMonthExist() {
        Service service = new Service();
        User testUser = new User("testUser", "testPassword");
        service.registerUser(testUser.getUsername(), testUser.getPassword());
        assertFalse(service.isMonthExist(testUser, "January"));
        service.addUserAndWaterInfo(testUser, "January", 50, 30);
        assertTrue(service.isMonthExist(testUser, "January"));
        User anotherUser = new User("anotherUser", "anotherPassword");
        assertFalse(service.isMonthExist(anotherUser, "January"));
    }
}