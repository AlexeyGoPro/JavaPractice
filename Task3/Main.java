package com.company;

import java.util.*;

public class Main {

    private static final int U = 220;
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ArrayList<Electrical> devices = new ArrayList<Electrical>();

        String load;
        String num = "";
        int maxLoad = 0;
        int sample = 1;

        System.out.println("Введите максимально допустимую нагрузку в Амперах: ");
        load = scan.nextLine();
        for (int i = 0; i < load.length(); i++) {
            if (Character.isDigit(load.charAt(i))) {
                num += load.charAt(i);
                maxLoad += Integer.parseInt(num);
                num = num.substring(1);
            }
        }
        int flag;
        do {
            start(sample, devices, maxLoad);
            System.out.println("Хотите продолжить?\t\n1) Да\t\n2) Нет");
            flag = scan.nextInt();
        } while(flag != 2);

    }

    public static void start(int sample, ArrayList<Electrical> devices, int maxLoad){
        while (sample != 7) {
            sample = menuFirst();
            if (devices.size() == 0 && sample ==7) {
                System.out.println("Выберите минимум 1 прибор");
                sample = 0;
            } else if (sample != 7) {
                devices.add(makeDevice(sample));
            }
        }

        infoElectrical(devices);

        while (sample != 4) {
            sample = menuSecond();
            switch (sample) {
                case (1): {
                    printAllInfo(devices);
                    break;
                }
                case (2): {
                    printOnInfo(devices);
                    break;
                }
                case (3): {
                    printOffInfo(devices);
                    break;
                }
            }
        }
        checkLoadAndOff(maxLoad, devices);
    }

    static void checkLoadAndOff(int maxLoad, ArrayList<Electrical> devices) {
        Scanner scan = new Scanner(System.in);
        int sample;
        double momentLoad;
        if (maxLoad >= getLoad(getFullPowerWatt(devices), U)) {
            System.out.println("Максимально допустимая сила тока не превышена");
        } else {
            devices.remove(2);
            sortList(devices);
            for (int i = 0; i < devices.size(); i++) {
                momentLoad = getLoad(getFullPowerWatt(devices) - devices.get(i).getPowerWatt(), U);
                if (maxLoad >= momentLoad) {
                    printOnInfo(devices);
                    System.out.println("Превышена максимально доступная сила тока.\n" +
                            "Выключить этото электроприбор? [" + cutName(devices, i) + "]\n1) Да\n2) Нет");
                    sample = scan.nextInt();
                    while (sample != 2) {
                        if (sample == 1) {
                            devices.get(i).off();
                            sample = 2;
                            i = devices.size();
                            System.out.println("Максимально допустимая сила тока не превышена");
                            printOnInfo(devices);
                        } else {
                            System.out.println("Неверный номер!");
                        }
                    }
                }
                if ((i == devices.size() - 1 && maxLoad <= momentLoad) || (i == devices.size() - 1 && maxLoad >= momentLoad)) {
                    System.out.println("Не удалось подобрать прибор для выключения" +
                            "или не было выбрано ни одного прибора");
                }
            }
        }
    }

    static int menuFirst() {
        Scanner scan = new Scanner(System.in);
        int sample = 0;
        while (sample < 1 || sample > 7) {
            System.out.println("Выберите электроприбор:\n" +
                    "1) Чайник\t2000Ватт\n2) Холодильник\t400Ватт\n3) Миксер\t500Ватт\n" +
                    "4) Пылесос\t1500Ватт\n5) Телевизор\t200Ватт\n6) Кондиционер\t2400Ватт\n" +
                    "7) Следующий шаг");
            sample = scan.nextInt();
            if (sample == 7) {
            } else if (sample < 1 || sample > 6) {
                System.out.println("Неверный номер!");
            }
        }
        return sample;
    }

    static int menuSecond() {
        Scanner scan = new Scanner(System.in);
        int sample = 0;
        while (sample < 1 || sample > 4) {
            System.out.println("Выберите какие приборы хотите просмотреть:\n" +
                    "1) Все приборы\n2) Включенные приборы\n3) Выключенные приборы\n" +
                    "4) Следующий шаг");
            sample = scan.nextInt();
            if (sample < 1 || sample > 4) {
                System.out.println("Неверный номер!");
            }
        }
        return sample;
    }

    static Electrical makeDevice(int sample) {
        Scanner scan = new Scanner(System.in);
        int powerWatt;

        System.out.println("Введите мощность электроприбора в Ваттах:");
        powerWatt = scan.nextInt();
        switch(sample) {
            case (1): {
                return new Teapot(powerWatt);
            }
            case (2): {
                return new Fridge(powerWatt);
            }
            case (3): {
                return new Mixer(powerWatt);
            }
            case (4): {
                return new VacuumCleaner(powerWatt);
            }
            case (5): {
                return new Television(powerWatt);
            }
            case (6): {
                return new Conditioner(powerWatt);
            }
            default: {
                return null;
            }
        }
    }

    public static void infoElectrical(ArrayList<Electrical> devices) {
        Scanner scan = new Scanner(System.in);
        int sample;

        System.out.println("Введите номер прибора, который хотите включить или выключить.\n" +
                "Для завершения введите 0:");
        sample = scan.nextInt();

        if (sample == 0) {
            return;
        } else if (sample < 0 || sample - 1 >= devices.size()) {
            System.out.println("Электроприбора с таким номером нет");
        } else {
            if (devices.get(sample - 1).getActive() == 0) {
                devices.get(sample - 1).on();
            } else {
                devices.get(sample - 1).off();
            }
        }
        infoElectrical(devices);
    }

    public static void printAllInfo(ArrayList<Electrical> devices) {
        System.out.println("Все электроприборы:");
        System.out.println("Номер\t\tНаименование\t\tМощность\t\tВкл/Выкл");
        for (int i = 0; i < devices.size(); i++) {
            System.out.println((i + 1) + "\t\t\t" + cutName(devices, i) + "\t\t\t\t" + devices.get(i).getPowerWatt() + "\t\t\t\t" + devices.get(i).getActiveUser());
        }
    }

    public static void printOnInfo(ArrayList<Electrical> devices) {
        System.out.println("Все включенные электроприборы:");
        System.out.println("Номер\t\tНаименование\t\tМощность\t\tВкл/Выкл");
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getActive() == 1) {
                System.out.println((i + 1) + "\t\t" + cutName(devices, i) + "\t\t" + devices.get(i).getPowerWatt() + "\t\t" + devices.get(i).getActiveUser());
            }
        }
    }

    public static void printOffInfo(ArrayList<Electrical> devices) {
        System.out.println("Все выключенные электроприборы:");
        System.out.println("Номер\t\tНаименование\t\tМощность\t\tВкл/Выкл");
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getActive() == 0) {
                System.out.println((i + 1) + "\t\t" + cutName(devices, i) + "\t\t" + devices.get(i).getPowerWatt() + "\t\t" + devices.get(i).getActiveUser());
            }
        }
    }

    public static String cutName(ArrayList<Electrical> devices, int i) {
        String str;
        str = devices.get(i).getClass().toString();
        str = str.substring(str.lastIndexOf(".") + 1);
        return str;
    }

    public static int getFullPowerWatt(ArrayList<Electrical> devices) {
        int fullPowerWatt = 0;
        for (Electrical device : devices) {
            if (device.getActive() == 1) {
                fullPowerWatt += device.getPowerWatt();
            }
        }
        return fullPowerWatt;
    }

    public static double getLoad(int p, int u) {
        return (double) p / u;
    }

    public static ArrayList<Electrical> sortList(ArrayList<Electrical> devices) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getActive() == 0) {
                devices.remove(i);
                i--;
            }
        }
        return devices;
    }
}