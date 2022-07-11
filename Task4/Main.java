package com.company;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashSet<BuisinessRecord> buisinessRecords = new HashSet<BuisinessRecord>();
        int sample = 0;

        start(sample, buisinessRecords);


    }

    public static int menuSecond(Diary diary) {
        Scanner scan = new Scanner(System.in);
        Scanner scanStr = new Scanner(System.in);
        int sample;
        String strSample;
        DateTime dateTime = new DateTime();
        BuisinessRecord buisinessRecord;

        System.out.println("Выберите действие:\n" +
                "1) Поиск записи по дате/времени создания\n" +
                "2) Удаление записи по дате создания\n" +
                "3) Корректировка записи (поиск по дате/времени создания)\n" +
                "4) Показать все записи (сортировка по дате добавления или наоборот)\n" +
                "5) Показать запись (поиск по дате/времени создания)\n" +
                "6) Показать последнюю запись\n" +
                "7) Завершение работы");
        sample = scan.nextInt();
        switch (sample) {
            case (1): {
                System.out.println("Введите дату для поиска записи в формате (dd-MM-yyyy/HH:mm:ss):");
                strSample = scanStr.nextLine();
                System.out.println("*** " + strSample);
                dateTime.setDateTime(strSample);
                if ((buisinessRecord = diary.searchByDateTime(dateTime)) != null) {
                    System.out.println("Найденная запись:");
                    buisinessRecord.printBuisinessRecord();
                } else {
                    System.out.println("Не удалось найти запись по заданной дате");
                    goBack(diary);
                }
                break;
            }
            case (2): {
                System.out.println("Введите дату записи для удаления в формате (dd-MM-yyyy/HH:mm:ss):");
                diary.showAll(true);
                int ans;
                int x = 0;
                Scanner var = new Scanner(System.in);

                strSample = scanStr.nextLine();
                dateTime.setDateTime(strSample);
                for (BuisinessRecord temp : diary.getBuisinessRecords()) {
                    if (temp.getExecuteDate().getDateTimeString().equals(dateTime.getDateTimeString())) {
                        System.out.println();
                        temp.printBuisinessRecord();

                        System.out.println("Удалить эту запись?\n" +
                                "1) Да\n2) Нет");
                        ans = var.nextInt();
                        if (ans == 1) {
                            diary.delete(dateTime);
                            x++;
                            break;
                        } else if (ans == 2) {

                        } else {
                            System.out.println("Неверный номер!");
                        }
                    }
                }
                if (x == 0) {
                    System.out.println("Вы не удалили ни одной записи");
                    goBack(diary);
                } else {
                    System.out.println("Запись/записи были удалены");
                }
                break;
            }
            case (3): {
                System.out.println("Введите дату записи для редактирования в формате (dd-MM-yyyy/HH:mm:ss):");
                diary.showAll(true);
                strSample = scanStr.nextLine();
                dateTime.setDateTime(strSample);
                diary.correct(dateTime);
                break;
            }
            case (4): {
                System.out.println("Как вывести записи?\n" +
                        "1) С первой до последней записи\n" +
                        "2) С последней до первой записи");
                sample = scan.nextInt();
                if (sample == 1) {
                    diary.showAll(true);
                } else if (sample == 2) {
                    diary.showAll(false);
                } else {
                    System.out.println("Неверный номер!");
                    goBack(diary);
                }
                break;
            }
            case (5): {
                System.out.println("Введите дату записи для показа в формате (dd-MM-yyyy/HH:mm:ss):");
                strSample = scanStr.nextLine();
                dateTime.setDateTime(strSample);
                if ((buisinessRecord = diary.searchByDateTime(dateTime)) != null) {
                    System.out.println("Найденная запись:");
                    buisinessRecord.printBuisinessRecord();
                } else {
                    System.out.println("Не удалось найти запись по заданной дате");
                    goBack(diary);
                }
                break;
            }
            case (6): {
                diary.showLast().printBuisinessRecord();
                break;
            }
            case (7): {
                return 7;
            }
            default: {
                System.out.println("Неверный номер!");
                goBack(diary);
            }
        }
        return 0;
    }

    public static void start(int sample, HashSet<BuisinessRecord> buisinessRecords) {
        while (sample != 2) {
            sample = menuFirst();
            if (buisinessRecords.size() == 0 && sample == 2) {
                System.out.println("Напишите минимум одну запись");
                sample = 0;
            } else if (sample == 1) {
                buisinessRecords.add(createNewEntry());
            }
        }
        Diary diary = new Diary(buisinessRecords);
        while (sample != 7) {
            sample = menuSecond(diary);
        }
    }

    public static void goBack(Diary diary) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Чтобы вернуться назад нажмите Enter");
        String str = scan.nextLine();
        menuSecond(diary);
    }

    public static int menuFirst() {
        Scanner scan = new Scanner(System.in);
        int sample;
        System.out.println("Выберите, что хотите сделать?\n" +
                "1) Создать запись\n2) Следующее меню");
        sample = scan.nextInt();
        if (sample < 1 || sample > 2) {
            System.out.println("Неверный номер!");
            return 0;
        }
        return sample;
    }

    public static BuisinessRecord createNewEntry() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите новую запись, через пробел в конце укажите дату и время " +
                "запланированного дела в формате (planned buisiness dd-MM-yyyy/HH:mm:ss):");
        String str = scan.nextLine();
        return new BuisinessRecord(str);
    }


}