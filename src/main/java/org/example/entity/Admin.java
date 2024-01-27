package org.example.entity;

import org.example.service.Service;

import java.util.Map;
import java.util.Scanner;

public class Admin {
    private Service service;

    public Admin(Service service) {
        this.service = service;
    }

    private void viewAllUserData() {
        System.out.println("Просмотр всех доступных данных для всех пользователей:");
        for (User user : service.getAllUsers()) {
            Map<String, Meter> userMeterMap = service.getUserMeterMap(user);
            System.out.println("Пользователь: " + user.getUsername());
            if (userMeterMap.isEmpty()) {
                System.out.println("Нет данных о потреблении воды.");
            } else {
                for (Map.Entry<String, Meter> entry : userMeterMap.entrySet()) {
                    String month = entry.getKey();
                    Meter meter = entry.getValue();
                    System.out.println("Месяц: " + month);
                    System.out.println("Горячая вода: " + meter.getHotWaterConsumption());
                    System.out.println("Холодная вода: " + meter.getColdWaterConsumption());
                    System.out.println("-------------------------");
                }
            }
        }
    }
    public void AdminPass(Scanner scanner){
        String login = "admin";
        String pass = "adminpass";
        System.out.println("Вход от имени администратора");
        System.out.println("Введите логин и пароль от имени администратора");
        System.out.println("Введите логин");
        String log = scanner.nextLine();
        if(log.equals(login)){
            System.out.println("Введите пароль");
            String pas = scanner.nextLine();
            if(pas.equals(pass)){
                viewAllUserData();
            }
            else {
                System.out.println("Не правильный ввод");
            }
        }else {
            System.out.println("Не правильный логин");
        }
    }
}
