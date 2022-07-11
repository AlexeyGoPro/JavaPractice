package com.company;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class BuisinessRecord extends Sentence implements Comparable<BuisinessRecord> {

    private DateTime executeDate = new DateTime();
    private DateTime createdWhen = new DateTime();

    public BuisinessRecord() {
        super();
    }

    public BuisinessRecord(String words) {
        super(words);
        String[] arrWords = words.split(" ");
        executeDate.setDateTime(arrWords[arrWords.length - 1]);
        deleteDataTime();
        createdWhen.setDateTimeCurr();
    }

    public BuisinessRecord(String[] words) {
        super(words);
        executeDate.setDateTime(words[words.length - 1]);
        deleteDataTime();
        createdWhen.setDateTimeCurr();
    }

    public void setExecuteDate(DateTime executeDate) {
        this.executeDate = executeDate;
    }

    public void setCreatedWhen(DateTime createdWhen) {
        this.createdWhen = createdWhen;
    }

    public DateTime getExecuteDate() {
        return executeDate;
    }

    public DateTime getCreatedWhen() {
        return createdWhen;
    }

    public void printBuisinessRecord() {
        System.out.println(getOriginalSentence() + "\nДата/время выполнения задания: "
                + executeDate.getDateTimeString() + "\nДата/время создания: " + createdWhen.getDateTimeString() + "\n");
    }


    @Override
    public int compareTo(BuisinessRecord o) {
        ZonedDateTime thisZoneDateTime = executeDate.getDateTime().atZone(ZoneId.systemDefault());
        ZonedDateTime oZoneDateTime = o.executeDate.getDateTime().atZone(ZoneId.systemDefault());

        long seconds1 = thisZoneDateTime.toInstant().toEpochMilli() / 1000;
        long seconds2 = oZoneDateTime.toInstant().toEpochMilli() / 1000;
        return (int) (seconds1 - seconds2);
    }

    @Override
    public int hashCode() {
        ZonedDateTime zoneDT = executeDate.getDateTime().atZone(ZoneId.systemDefault());
        return (int) (zoneDT.toInstant().toEpochMilli() / 1000) * 31;
    }
}