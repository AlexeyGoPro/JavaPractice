package com.company;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        //НАЧАЛО РАБОТЫ С КОМПЬЮТЕРОМ
        Computer computer = new Computer();
        Scanner in = new Scanner(System.in);
        boolean flag;
        while (flag = true) {
            boolean symb;
            do {
                symb = Boolean.parseBoolean("true");
                System.out.print("Напишите вкл или выкл, если хотите включить/выключить компьютер: ");
                String Cond = in.next();
                computer.getCondition(Cond);
                if (Cond.equals("Вкл") || Cond.equals("ВКЛ") || Cond.equals("вкл")) {
                    symb = false;

                } else {
                    System.out.println("Выход из системы");
                    System.exit(0);
                }
            } while (symb);

            int swchoice = 0;

            System.out.println("\n1. Проверка компьютера на вирусы.\n2. Вывод объёма памяти винчестера\n3.Вывод объёма оперативной памяти\n4. Выход из компьютера\nВыберите действие (цифру): ");
            while (swchoice != 4) {
                swchoice = in.nextInt();
                switch (swchoice) {
                    case (1): {
                        //ПРОВЕРКА КОМПЬЮТЕРА НА ВИРУСЫ

                        if (computer.getAntivirusStatus()) {
                            System.out.println("Вирус обнаружен!");
                        } else {
                            System.out.println("Вирусов не обнаружено");
                        }
                    }

                    break;


                    //ВЫВОД ОБЪЁМА ПАМЯТИ ВИНЧЕСТЕРА
                    case (2): {

                        System.out.println(computer.gethard.GetHard() + " Гб");
                        break;
                    }

                    //ВЫВОД ОБЪЁМА ОПЕРАТИВНОЙ ПАМЯТИ
                    case (3): {
                        System.out.println(computer.ram.RamM() + " Гб");
                        break;
                    }

                    //Выход из компьютера
                    case (4):
                    {
                        break;
                    }
                }
            }
        }
    }
}

