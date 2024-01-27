package org.example;

import org.example.entity.Admin;
import org.example.entity.Meter;
import org.example.entity.User;
import org.example.service.AppService;
import org.example.service.Service;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static Service service = new Service();
    private static User currentUser;
    private static Admin admin = new Admin(service);

    public static void main(String[] args) {
        System.out.println("Добро пожаловать!");
        while (true) {
            AppService.printLoginMenu();
            int numLogin = Integer.parseInt(scanner.nextLine());
            switch (numLogin) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    AppService.exitApp();
                case 4:
                    System.out.println("Вход от имени администратора");
                    admin.AdminPass(scanner);
                    break;
                default:
                    System.out.println("Неизвестная команда");
            }

            if (currentUser != null) {
                while (true) {
                    AppService.printMainMenu();
                    int mainMenuChoice = Integer.parseInt(scanner.nextLine());
                    switch (mainMenuChoice) {
                        case 1:
                            recordWaterConsumption();
                            break;
                        case 2:
                            showUserData();
                            break;
                        case 3:
                            AppService.exitApp();
                            break;
                        default:
                            System.out.println("Неизвестная команда");
                    }
                    System.out.println("Желаете выполнить другие действия? (y/n): ");
                    String continueChoice = scanner.nextLine().toLowerCase();
                    if (!continueChoice.equals("y")) {
                        break;
                    }
                }
            }
        }
    }

    private static void login() {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        currentUser = service.authenticateUser(username, password);

        if (currentUser != null) {
            System.out.println("Вход успешен. Добро пожаловать, " + currentUser.getUsername() + "!");
        } else {
            System.out.println("Ошибка входа. Неверное имя пользователя или пароль.");
        }
    }

    private static void register() {
        System.out.print("Введите новое имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        if (service.registerUser(username, password)) {
            System.out.println("Регистрация успешна. Добро пожаловать, " + username + "!");
            currentUser = new User(username, password);
        } else {
            System.out.println("Ошибка регистрации. Пользователь с таким именем уже существует.");
        }
    }

    private static void recordWaterConsumption() {
        if (currentUser != null) {
            try {
                System.out.print("Введите месяц: ");
                String month = scanner.nextLine();
                if(service.isMonthExist(currentUser,month)){
                    System.out.println("Ошибка. Данные для этого месяца уже добавлены.");
                    return;
                }
                System.out.print("Введите количество потраченной горячей воды: ");
                int hotWater = Integer.parseInt(scanner.nextLine());
                System.out.print("Введите количество потраченной холодной воды: ");
                int coldWater = Integer.parseInt(scanner.nextLine());

                service.addUserAndWaterInfo(currentUser, month, hotWater, coldWater);
                System.out.println("Информация о потреблении воды добавлена.");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода. Введите корректное число.");
            }
        } else {
            System.out.println("Ошибка. Пользователь не аутентифицирован.");
        }
    }

    public static void showUserData() {
        if (currentUser != null) {
            System.out.print("Введите месяц: ");
            String month = scanner.nextLine();
            Meter currentUserMeter = service.getMeterForUserAndMonth(currentUser, month);
            if (currentUserMeter != null) {
                System.out.println("Ваши данные по потреблению воды в " + month + ":");
                System.out.println("Горячая вода: " + currentUserMeter.getHotWaterConsumption());
                System.out.println("Холодная вода: " + currentUserMeter.getColdWaterConsumption());
            } else {
                System.out.println("Нет данных о потреблении воды для текущего пользователя в " + month);
            }
        } else {
            System.out.println("Ошибка. Пользователь не аутентифицирован.");
        }
    }


}
