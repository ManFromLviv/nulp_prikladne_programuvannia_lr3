package menu;

import droids.Droid;
import droids.type.AirDroid;
import droids.type.AquaDroid;
import droids.type.AttackDroid;
import droids.type.DefenseDroid;

import java.util.ArrayList;
import java.util.Scanner;

import static battle.Battle.battleFight;
import static files.Files.*;

public class Menu {
    public static void runMenu() throws InterruptedException {
        final String directoryPath = "src/history/";
        ArrayList<Droid> droids = new ArrayList<>();
        boolean menuOpen = true;
        while (menuOpen) {
            switch (menu()) {
                case "0": subMenu0(); break;
                case "1": subMenu1(droids); break;
                case "2": subMenu2(droids); break;
                case "3": subMenu3(directoryPath, droids); break;
                case "4": subMenu4(directoryPath, droids); break;
                case "5": subMenu5(droids); break;
                case "6": subMenu6(droids); break;
                case "7": subMenu7(directoryPath); break;
                case "8": subMenu8(directoryPath); break;
                case "9": subMenu9(directoryPath); break;
                default: menuOpen = exitMenu(); break;
            }
        }
    }

    public static String inputCommand() {
        return new Scanner(System.in).next();
    }

    public static boolean checkCommand(String command, int start, int end) {
        boolean isValid = false;
        for (int i = start; i <= end; i++) {
            if (command.equals(String.valueOf(i))) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    public static String getListDroids(ArrayList<Droid> droids) {
        StringBuilder stringBuilder = new StringBuilder();
        if (droids.isEmpty()) {
            stringBuilder.append("\t\tНемає дроїдів").append("\n");
        } else {
            int counter = 1;
            for (Droid droid : droids) {
                stringBuilder.append("\t\t").append(counter++).append(") ").append(droid).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public static void getBattle(String directoryPath, ArrayList<Droid> team1, ArrayList<Droid> team2) {
        String data = battleFight(team1, team2);
        String filename = createFile(directoryPath, data);
        System.out.println("\tДані записані у файл: " + filename);
        System.out.print("\tБажаєте переглянути цей файл ('+' - так): ");
        String command = inputCommand();
        if (command.equals("+")) {
            System.out.println(readFile(filename));
        }
    }

    public static void getListNameFiles(ArrayList<String> stringsNamesRecordFiles) {
        if (stringsNamesRecordFiles.isEmpty()) {
            System.out.println("\tНі один файл ще не був записаний. Ця операція неможлива, проведіть хоча б одну битву.");
        } else {
            System.out.println("\tСписок усіх файлів:");
            int counterRecord = 1;
            for (String name : stringsNamesRecordFiles) {
                System.out.println("\t\t" + counterRecord++ + ") " + name);
            }
        }
    }

    public static String menu() {
        System.out.print(readFile("src/menu/menuCommand.txt"));
        return inputCommand();
    }

    public static boolean exitMenu() throws InterruptedException {
        System.out.print("\tВи дійсно впевнені, що хочете вийти з програми? ('+' - так): ");
        if (inputCommand().equals("+")) {
            System.out.println("\t\tВихід з програми.");
            for (int i = 3; i >= 1; i--) {
                System.out.println("\t\t\tПрограма закриється через " + i + " секунд ...");
                Thread.sleep(1000);
            }
            return false;
        } else {
            System.out.println("\t\tОперацію скасовано - програма продовжує виконання.");
            return true;
        }
    }

    public static void subMenu0() {
        System.out.println("Довідка:");
        System.out.println("\tПрограму створив Вальчевський П., студент ОІ-11сп для ЛР № 3 з Прикладного програмування.");
    }

    public static void subMenu1(ArrayList<Droid> droids) {
        System.out.print(readFile("src/menu/subMenu1Command.txt"));
        String command = inputCommand();
        if (checkCommand(command, 1, 4)) {
            String nameDroid;
            while (true) {
                System.out.print("Уведіть ім'я дроїда: ");
                nameDroid = inputCommand();
                if (!nameDroid.isEmpty()) {
                    switch (command) {
                        case "1": droids.add(new AirDroid(nameDroid)); break;
                        case "2": droids.add(new AttackDroid(nameDroid)); break;
                        case "3": droids.add(new AquaDroid(nameDroid)); break;
                        case "4": droids.add(new DefenseDroid(nameDroid)); break;
                    }
                    return;
                } else {
                    System.out.println("\tІм'я не може бути пустим.");
                }
            }
        }
    }

    public static void subMenu2(ArrayList<Droid> droids) {
        System.out.println("\tСписок дроїдів:");
        System.out.print(getListDroids(droids));
    }

    public static void subMenu3(String dirctoryPath, ArrayList<Droid> droids) {
        int sizeDroids = droids.size();
        if (sizeDroids > 1) {
            subMenu2(droids);
            System.out.print("\tУведіть номер учасника 1 по-списку: ");
            String number1 = inputCommand();
            System.out.print("\tУведіть номер учасника 2 по-списку: ");
            String number2 = inputCommand();

            boolean isValid = checkCommand(number1, 1, sizeDroids) && checkCommand(number2, 1, sizeDroids) && !number2.equals(number1);
            if (isValid) {
                int index1 = Integer.parseInt(number1) - 1;
                int index2 = Integer.parseInt(number2) - 1;
                ArrayList<Droid> team1 = new ArrayList<>();
                team1.add(droids.get(index1));
                ArrayList<Droid> team2 = new ArrayList<>();
                team2.add(droids.get(index2));

                getBattle(dirctoryPath, team1, team2);
            } else {
                System.out.println("\tПотрібно ввести порядковий номер по списку і дроїди мають бути різні!");
            }
        } else {
            System.out.println("\tПотрібно для цієї команди від 2 дроїдів.");
        }
    }

    public static void subMenu4(String dirctoryPath, ArrayList<Droid> droids) {
        int sizeDroids = droids.size();
        if (sizeDroids > 2) {
            int sizeDroid = droids.size();
            ArrayList<Droid> team1 = new ArrayList<>();
            ArrayList<Droid> team2 = new ArrayList<>();

            for (int i = 0; i < sizeDroid; i++) {
                if (i % 2 == 0) {
                    team1.add(droids.get(i));
                } else {
                    team2.add(droids.get(i));
                }
            }

            System.out.println("Список сформованої команди 1:");
            System.out.println(getListDroids(team1));
            System.out.println("Список сформованої команди 2:");
            System.out.println(getListDroids(team2));

            getBattle(dirctoryPath, team1, team2);
        } else {
            System.out.println("\tПотрібно для цієї команди від 3 дроїдів.");
        }
    }

    public static void subMenu5(ArrayList<Droid> droids) {
        subMenu2(droids);
        System.out.print("\tВи дійсно впевнені, що хочете видалити усіх дроїдів? ('+' - так): ");
        if (inputCommand().equals("+")) {
            System.out.println("\t\tСписок дроїдів було очищено.");
            droids.clear();
        } else {
            System.out.println("\t\tОперацію скасовано - список дроїдів НЕ було очищено.");
        }
    }

    public static void subMenu6(ArrayList<Droid> droids) {
        subMenu2(droids);
        int sizeDroids = droids.size();
        if (sizeDroids > 0) {
            System.out.print("\tУведіть номер для видалення по списку: ");
            String number = inputCommand();

            boolean isValid = checkCommand(number, 1, sizeDroids);
            if (isValid) {
                int index = Integer.parseInt(number) - 1;
                System.out.println("\t\t" + droids.get(index));
                System.out.print("\t\tВи дійсно впевнені, що хочете видалити цього дроїда? ('+' - так): ");
                if (inputCommand().equals("+")) {
                    droids.remove(index);
                } else {
                    System.out.println("\t\tОперацію було скасовано!");
                }
            } else {
                System.out.println("\tПотрібно ввести порядковий номер по списку!");
            }
        } else {
            System.out.println("\tПотрібно для цієї команди мінімум 1 дроїд.");
        }
    }

    public static void subMenu7(String directoryPath) {
        ArrayList<String> stringsNamesRecordFiles = getStringNamesRecordFiles(directoryPath);
        getListNameFiles(stringsNamesRecordFiles);
        if (!stringsNamesRecordFiles.isEmpty()) {
            System.out.print("\t\t\tУведіть номер файлу по-списку, який хочете переглянути: ");
            String command = inputCommand();
            boolean isValid = checkCommand(command, 1, stringsNamesRecordFiles.size());

            if (isValid) {
                int index = Integer.parseInt(command) - 1;
                System.out.println(readFile(directoryPath + stringsNamesRecordFiles.get(index)));
            } else {
                System.out.println("\t\t\t\tТакого файлу за таким номером немає у списку!");
            }
        }
    }

    public static void subMenu8(String directoryPath) {
        ArrayList<String> stringsNamesRecordFiles = getStringNamesRecordFiles(directoryPath);
        getListNameFiles(stringsNamesRecordFiles);
        if (!stringsNamesRecordFiles.isEmpty()) {
            System.out.print("\t\t\tУведіть номер файлу по-списку, який хочете видалити: ");
            String command = inputCommand();
            boolean isValid = checkCommand(command, 1, stringsNamesRecordFiles.size());

            if (isValid) {
                int index = Integer.parseInt(command) - 1;
                System.out.print("\t\t\t\tВи дійсно хочете видалити цей файл? ('+' - так): ");
                isValid = inputCommand().equals("+");

                if (isValid && deleteFile(directoryPath + stringsNamesRecordFiles.get(index))) {
                    System.out.println("\t\t\t\tФайл " + stringsNamesRecordFiles.get(index) + " було успішно видалено!");
                } else {
                    System.out.println("\t\t\t\tФайл " + stringsNamesRecordFiles.get(index) + " НЕ було видалено!");
                }
            } else {
                System.out.println("\t\t\t\tТакого файлу за таким номером немає у списку!");
            }
        }
    }

    public static void subMenu9(String directoryPath) {
        ArrayList<String> stringsNamesRecordFiles = getStringNamesRecordFiles(directoryPath);
        getListNameFiles(stringsNamesRecordFiles);
        if (!stringsNamesRecordFiles.isEmpty()) {
            System.out.print("\t\t\t\tВи дійсно хочете видалити усі файли боїв? ('+' - так): ");
            boolean isValid = inputCommand().equals("+");

            if (isValid) {
                if (deleteAllFiles(directoryPath)) {
                    System.out.println("\t\t\t\tФайли було успішно видалено!");
                } else {
                    System.out.println("\t\t\t\tДеякі файли не було видалено!");
                    getListNameFiles(getStringNamesRecordFiles(directoryPath));
                }
            } else {
                System.out.println("\t\t\t\tФайли НЕ було видалено!");
            }
        }
    }
}
