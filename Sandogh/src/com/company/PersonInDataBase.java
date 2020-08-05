package com.company;

public class PersonInDataBase {
    private String firstName;
    private String lastName;
    private String dept;
    private String totalMoney;
    private int member;
    private int id;

    public PersonInDataBase(String firstName, String lastName, String dept, String totalMoney, int member, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dept = dept;
        this.totalMoney = totalMoney;
        this.member = member;
        this.id = id;
    }

    public PersonInDataBase(String firstName, String lastName, String dept, String totalMoney, int member) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dept = dept;
        this.totalMoney = totalMoney;
        this.member = member;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' ;
    }
}
