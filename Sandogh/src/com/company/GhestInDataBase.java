package com.company;

public class GhestInDataBase {
    private String date3;
    private String explain;
    private int day;
    private int month;
    private int year;
    private String pardakht;
    private int person;
    private  String chek;

    public GhestInDataBase(String explain, int day, int month, int year, String pardakht, int person, String chek) {
        this.explain = explain;
        this.day = day;
        this.month = month;
        this.year = year;
        this.pardakht = pardakht;
        this.person = person;
        this.chek = chek;
        if(day > 9) {
            if (month > 9)
                this.date3 = String.valueOf(this.year) + "//" + String.valueOf(this.month) + "//" + String.valueOf(this.day);
            else
                this.date3 = String.valueOf(this.year) + "//" + "0"+String.valueOf(this.month)+ "//" + String.valueOf(this.day);

        }
        else {
            if (month > 9)
                this.date3 = String.valueOf(this.year) + "//" + String.valueOf(this.month) + "//" + "0"  + String.valueOf(this.day);
            else
                this.date3 = String.valueOf(this.year) + "//" + "0"+String.valueOf(this.month) + "//" + '0' + String.valueOf(this.day);

        }
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
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

    public String getPardakht() {
        return pardakht;
    }

    public void setPardakht(String pardakht) {
        this.pardakht = pardakht;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public String getChek() {
        return chek;
    }

    public void setChek(String chek) {
        this.chek = chek;
    }

    public String getDate3() {
        return date3;
    }

    public void setDate3(String date3) {
        this.date3 = date3;
    }
}
