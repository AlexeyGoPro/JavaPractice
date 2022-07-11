package com.company;

import java.lang.management.*;


import java.io.File;

public class Computer {
    GetHard gethard = new GetHard();
    Proc proc = new Proc();
    Ram ram = new Ram();

    public String getCondition(String Cond) {
        if (Cond.equals("Вкл") || Cond.equals("ВКЛ") || Cond.equals("вкл")) {
            System.out.println("Компьютер включён");
            Cond = "T";
            return Cond;
        } else {
            System.out.println("Компьютер выключен");
            Cond = "F";
            return Cond;
        }
    }

    public static boolean getAntivirusStatus() {
        return Math.random() < 0.5;
    }


    public static class Ram {
        public long RamM() {
            long ram = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
            return ram/1000000000;
        }
    }

    public static class Proc {
    }

    public static class GetHard {
        public long GetHard() {
            File file = new File("C:");
            long GetHard = file.getTotalSpace();
            return GetHard/1000000000;
        }
    }

}

