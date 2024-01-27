package org.example.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Meter {
    private int hotWaterConsumption;
    private int coldWaterConsumption;

    public Meter() {
        this.hotWaterConsumption = 0;
        this.coldWaterConsumption = 0;
    }

    public int getHotWaterConsumption() {
        return hotWaterConsumption;
    }

    public int getColdWaterConsumption() {
        return coldWaterConsumption;
    }

    public void addHotWaterConsumption(int amount) {
        hotWaterConsumption += amount;
    }

    public void addColdWaterConsumption(int amount) {
        coldWaterConsumption += amount;
    }
}
