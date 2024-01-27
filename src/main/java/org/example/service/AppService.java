package org.example.service;

import java.util.HashMap;
import java.util.Map;

public class AppService {
    private static Map<String, Integer> userReadings = new HashMap<>();

    public static void printLoginMenu() {
        System.out.println("1. Вход");
        System.out.println("2. Регистрация");
        System.out.println("3. Выйти");
        System.out.print("Выберите команду: ");
    }

    public static void printMainMenu() {
        System.out.println("1. Ввести показания");
        System.out.println("2. Просмотреть показания");
        System.out.println("3. Выйти");
        System.out.print("Выберите команду: ");
    }

    public static void exitApp() {
        System.out.println("Выход из приложения. До свидания!");
        System.exit(0);
    }
}
