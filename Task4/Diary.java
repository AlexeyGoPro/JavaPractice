package com.company;

import java.util.*;

public class Diary {

    private HashSet<BuisinessRecord> buisinessRecords = new HashSet<BuisinessRecord>();

    public void setBuisinessRecords(HashSet<BuisinessRecord> buisinessRecords) {
        this.buisinessRecords = buisinessRecords;
    }

    public HashSet<BuisinessRecord> getBuisinessRecords() {
        return buisinessRecords;
    }

    public Diary(HashSet<BuisinessRecord> buisinessRecords) {
        this.buisinessRecords = buisinessRecords;
    }

    public Diary(BuisinessRecord buisinessRecord) {
        this.buisinessRecords.add(buisinessRecord);
    }

    public BuisinessRecord searchByDateTime(DateTime dateTime) {
        BuisinessRecord temp = new BuisinessRecord();
        temp.setExecuteDate(dateTime);

        for (BuisinessRecord buisinessRecord : this.buisinessRecords) {
            if (buisinessRecord.hashCode() == temp.hashCode()) {
                return buisinessRecord;
            }
        }
        return null;
    }

    public boolean delete(DateTime dateTime) {
        BuisinessRecord temp = new BuisinessRecord();
        temp.setExecuteDate(dateTime);

        for (BuisinessRecord buisinessRecord : this.buisinessRecords) {
            if (buisinessRecord.hashCode() == temp.hashCode()) {
                buisinessRecords.remove(buisinessRecord);
                return true;
            }
        }
        return false;
    }

    public void correct(DateTime dateTime) {
        Scanner scan = new Scanner(System.in);
        String str;
        BuisinessRecord temp = new BuisinessRecord();
        boolean flag = true;
        temp.setExecuteDate(dateTime);

        for (BuisinessRecord buisinessRecord : this.buisinessRecords) {
            if (buisinessRecord.hashCode() == temp.hashCode()) {
                flag = false;
                System.out.println("Ваша запись:");
                buisinessRecord.printBuisinessRecord();

                System.out.println("Введите текст, для изменения записи:");
                str = scan.nextLine();
                buisinessRecord.setWordsStr(str);

                System.out.println("Изменённая запись");
                buisinessRecord.printBuisinessRecord();
            }
        }
        if (flag) {
            System.out.println("Нужная запись не найдена, повторите попытку!");
            DateTime newDT = new DateTime();
            str = scan.nextLine();
            newDT.setDateTime(str);
            correct(newDT);
        }
    }

    public void showAll(boolean asc) {
        List<BuisinessRecord> list = new ArrayList<BuisinessRecord>();
        if (asc) {
            list.addAll(sort(1));
        } else {
            list.addAll(sort(2));
        }
        for (BuisinessRecord buisinessRecord : list) {
            buisinessRecord.printBuisinessRecord();
        }
    }

    public BuisinessRecord showByDateTime(DateTime dateTime) {
        return searchByDateTime(dateTime);
    }

    public BuisinessRecord showLast() {
        List<BuisinessRecord> list = new ArrayList<BuisinessRecord>(buisinessRecords);
        Collections.sort(list);
        return list.get(list.size() - 1);
    }

    public List<BuisinessRecord> sort(int sample) {
        List<BuisinessRecord> list = new ArrayList<BuisinessRecord>(buisinessRecords);
        switch (sample) {
            case (1): {
                Collections.sort(list);
                System.out.println("Отсортирован с первой до последней записи");
                break;
            }
            case (2): {
                Collections.reverse(list);
                System.out.println("Отсортирован с последней до первой записи");
                break;
            }
        }
        return list;
    }


}