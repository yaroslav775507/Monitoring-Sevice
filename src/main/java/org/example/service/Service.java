package org.example.service;

import org.example.entity.Meter;
import org.example.entity.User;

import java.util.*;


public class Service {
    private Map<String, User> usernameUserMap;
    private Map<User, Map<String, Meter>> userMonthMeterMap;
    private static User currentUser;
    static Scanner scanner = new Scanner(System.in);

    private void init() {
        User defaultUser1 = new User("user1", "pass");
        User defaultUser2 = new User("user2", "qwerty");
        usernameUserMap.put("user1", defaultUser1);
        usernameUserMap.put("user2", defaultUser2);
        userMonthMeterMap.put(defaultUser1, new HashMap<>());
        userMonthMeterMap.put(defaultUser2, new HashMap<>());
        Meter meterUser1January = new Meter();
        meterUser1January.addHotWaterConsumption(50);
        meterUser1January.addColdWaterConsumption(30);
        userMonthMeterMap.get(defaultUser1).put("January", meterUser1January);
        Meter meterUser2January = new Meter();
        meterUser2January.addHotWaterConsumption(40);
        meterUser2January.addColdWaterConsumption(20);
        userMonthMeterMap.get(defaultUser2).put("January", meterUser2January);
        Meter meterUser1February = new Meter();
        meterUser1February.addHotWaterConsumption(60);
        meterUser1February.addColdWaterConsumption(40);
        userMonthMeterMap.get(defaultUser1).put("February", meterUser1February);
        Meter meterUser2February = new Meter();
        meterUser2February.addHotWaterConsumption(45);
        meterUser2February.addColdWaterConsumption(25);
        userMonthMeterMap.get(defaultUser2).put("February", meterUser2February);
    }

    public Service() {
        this.usernameUserMap = new HashMap<>();
        this.userMonthMeterMap = new HashMap<>();
        init();
    }

    public boolean registerUser(String username, String password) {
        if (!usernameUserMap.containsKey(username)) {
            User newUser = new User(username, password);
            usernameUserMap.put(username, newUser);
            userMonthMeterMap.put(newUser, new HashMap<>());
            return true;
        }
        return false;
    }

    public User authenticateUser(String username, String password) {
        User user = usernameUserMap.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void addUserAndWaterInfo(User user, String month, int hotWater, int coldWater) {
        Map<String, Meter> monthMeterMap = userMonthMeterMap.getOrDefault(user, new HashMap<>());
        Meter meter = monthMeterMap.getOrDefault(month, new Meter());
        meter.addHotWaterConsumption(hotWater);
        meter.addColdWaterConsumption(coldWater);
        monthMeterMap.put(month, meter);
        userMonthMeterMap.put(user, monthMeterMap);
    }

    public Meter getMeterForUserAndMonth(User user, String month) {
        Map<String, Meter> monthMeterMap = userMonthMeterMap.get(user);
        return (monthMeterMap != null) ? monthMeterMap.get(month) : null;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(usernameUserMap.values());
    }

    public Map<String, Meter> getUserMeterMap(User user) {
        return userMonthMeterMap.getOrDefault(user, new HashMap<>());
    }

    public boolean isMonthExist(User user, String month) {
        Map<String, Meter> monthMeter = userMonthMeterMap.get(user);
        return monthMeter != null && monthMeter.containsKey(month);
    }

}