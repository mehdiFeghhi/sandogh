package com.company;

public class VamInDataBase {
    private String amount;
    private int day;
    private int month;
    private int year;
    private String chek;
    private int person;

    public VamInDataBase(String amount, int day, int month, int year, String chek, int person) {
        this.amount = amount;
        this.day = day;
        this.month = month;
        this.year = year;
        this.chek = chek;
        this.person = person;
    }

    public VamInDataBase(String amount, int day, int month, int year, String chek) {
        this.amount = amount;
        this.day = day;
        this.month = month;
        this.year = year;
        this.chek = chek;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getChek() {
        return chek;
    }

    public void setChek(String chek) {
        this.chek = chek;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }
}
