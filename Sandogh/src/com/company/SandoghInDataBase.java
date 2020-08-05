package com.company;

public class SandoghInDataBase {
    private int month ;
    private int year;
    private String totalMoney;
    private String enter;
    private String exit;

    public SandoghInDataBase(int month, int year, String totalMoney, String enter, String exit) {
        this.month = month;
        this.year = year;
        this.totalMoney = totalMoney;
        this.enter = enter;
        this.exit = exit;
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

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    @Override
    public String toString() {
        return "year: "+this.year+" month : "+this.month +" money Enter: "+this.enter+" money exit : "+this.exit;
    }
}
