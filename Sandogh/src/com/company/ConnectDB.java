package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class ConnectDB {
    private final static String url = "jdbc:sqlite:first.db";
    private static Connection connection;
    private static Statement statement;

    public static void connectDb3() throws SQLException {
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement();
    }
    public static void  insertPerson(String firstName,String lastName,int member,String dept,String totalMoney) throws SQLException {
        String sql = "INSERT INTO Person(firstName,lastName,member,dept,totalMoney) VALUES(?,?,?,?,?)";
        connectDb3();
        if(isThisPersonIndataBase(firstName,lastName)){
            //connection.close();
        }
        else {
            connectDb3();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, member);
            preparedStatement.setString(4, dept);
            preparedStatement.setString(5, totalMoney);
            preparedStatement.executeUpdate();
            connection.close();
            statement.close();
        }
    }



    public static void insertPersonHowCanGiveVam(int person) throws SQLException {
        String sql = "INSERT INTO PersonVam(person) VALUES(?)";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,person);
        preparedStatement.executeUpdate();
        connection.close();
        statement.close();
    }
    public static void insertVam(String amount,int day,int month,int year,String chek,int person) throws SQLException {
        String sql = "INSERT INTO vam(amount,day,month,year,chek,person) VALUES(?,?,?,?,?,?)";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,amount);
        preparedStatement.setInt(2,day);
        preparedStatement.setInt(3,month);
        preparedStatement.setInt(4,year);
        preparedStatement.setString(5,chek);
        preparedStatement.setInt(6,person);
        preparedStatement.executeUpdate();
        connection.close();
        statement.close();
    }
    public static void insertSahm(String amount) throws SQLException {
        String sql = "INSERT INTO Saham(amount) VALUES(?)";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,amount);
        preparedStatement.executeUpdate();
        connection.close();
    }
    public static void insertSandogh(int month,int year,String totalMoney,String enter,String exit) throws SQLException {
        String sql = "INSERT INTO  Sandogh(month,year,totalMoney,enter,exit,keys) VALUES(?,?,?,?,?,?)";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,month);
        preparedStatement.setInt(2,year);
        preparedStatement.setString(3,totalMoney);
        preparedStatement.setString(4,enter);
        preparedStatement.setString(5,exit);
        if (month > 9)
            preparedStatement.setString(6,String.valueOf(month)+String.valueOf(year));
        else
            preparedStatement.setString(6,String.valueOf(month)+"0"+String.valueOf(year));

        preparedStatement.executeUpdate();
        connection.close();
    }
    public static boolean updateSandogh(int month,int year,String totalMoney,String enter,String exit) throws SQLException {
        String sql =  "UPDATE Sandogh SET totalMoney = ? , "
                + "enter = ?, "
                +" exit = ?"
                +"WHERE keys = ?";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,totalMoney);
        preparedStatement.setString(2,enter);
        preparedStatement.setString(3,exit);
        if(month > 9)
            preparedStatement.setString(4,String.valueOf(month)+String.valueOf(year));
        else
            preparedStatement.setString(4,String.valueOf(month)+"0"+String.valueOf(year));
        preparedStatement.executeUpdate();
        connection.close();
        return true;

    }
    public static boolean isSandogh(int month,int year) throws SQLException {
        ObservableList<SandoghInDataBase> sandoghs = ConnectDB.selectAllSandoghPosition();
        for (SandoghInDataBase i : sandoghs){
            if(i.getYear()== year && i.getMonth()==month)
                return true;
        }
        return false;

    }
    public static void insertGhest(String explain,int day,int month,int year,String pardakht,int person,String Chek) throws SQLException {
        String sql = "INSERT INTO Ghest(explain,day,month,year,pardakht,person,chek) VALUES(?,?,?,?,?,?,?)";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,explain);
        preparedStatement.setInt(2,day);
        preparedStatement.setInt(3,month);
        preparedStatement.setInt(4,year);
        preparedStatement.setString(5,pardakht);
        preparedStatement.setInt(6,person);
        preparedStatement.setString(7,Chek);
        preparedStatement.setString(7,Chek);
        preparedStatement.executeUpdate();
        connection.close();

    }
    public static void insertHaghSandogh(String explain,int day,int month,int year,String pardakht,int person,String Chek) throws SQLException{
        String sql = "INSERT INTO HaghSandoh(explain,day,month,year,pardakht,person,Chek) VALUES(?,?,?,?,?,?,?)";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,explain);
        preparedStatement.setInt(2,day);
        preparedStatement.setInt(3,month);
        preparedStatement.setInt(4,year);
        preparedStatement.setString(5,pardakht);
        preparedStatement.setInt(6,person);
        preparedStatement.setString(7,Chek);
        preparedStatement.executeUpdate();
        connection.close();

    }
    public static void delateVam(String amount,int day,int month,int year,String chek,int person) throws SQLException {
        String sql = "DELETE FROM vam WHERE Chek = ? AND  amount = ? AND day = ? AND month = ? AND year = ? AND person = ?";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,amount);
        preparedStatement.setInt(2,day);
        preparedStatement.setInt(3,month);
        preparedStatement.setInt(4,year);
        preparedStatement.setString(5,chek);
        preparedStatement.setInt(6,person);
        preparedStatement.executeUpdate();
        connection.close();
        statement.close();
    }
    public static void delateHaghSandogh(String explain,int day,int month,int year,String pardakht,int person,String chek) throws SQLException{
        String sql = "DELETE FROM HaghSandoh WHERE explain = ? AND day = ? AND month = ? AND year = ? AND pardakht = ?AND person = ? AND chek = ?";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,explain);
        preparedStatement.setInt(2,day);
        preparedStatement.setInt(3,month);
        preparedStatement.setInt(4,year);
        preparedStatement.setString(5,pardakht);
        preparedStatement.setInt(6,person);
        preparedStatement.setString(7,chek);
        preparedStatement.executeUpdate();
        connection.close();
        statement.close();
    }
    public static void delateGhest(String explain,int day,int month,int year,String pardakht,int person,String chek) throws SQLException{
        String sql = "DELETE FROM Ghest WHERE explain = ? AND day = ? AND month = ? AND year = ? AND pardakht = ?AND person = ? AND check =?";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,explain);
        preparedStatement.setInt(2,day);
        preparedStatement.setInt(3,month);
        preparedStatement.setInt(4,year);
        preparedStatement.setString(5,pardakht);
        preparedStatement.setInt(6,person);
        preparedStatement.setString(7,chek);
        preparedStatement.executeUpdate();
        connection.close();
        statement.close();
    }
    public static void delatePersonVam(int person) throws SQLException {
        String sql = "DELETE FROM PersonVam WHERE  person = ?";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,person);
        preparedStatement.executeUpdate();
        connection.close();
        statement.close();
    }
    public static boolean isThisPersonIndataBase(String firstName,String lastName) throws SQLException {
        ObservableList<PersonInDataBase>persons = ConnectDB.selectAllPerson();
        for (PersonInDataBase i : persons){
            if(i.getLastName().equals(lastName) && i.getFirstName().equals(firstName))
                return true;
        }
        return false;
    }
    public static boolean updatePerson(String firstName,String lastName,int member,String dept,String totalMoney,int person)throws SQLException{
        ObservableList<PersonInDataBase> person1 = selectAllPerson();
        for (PersonInDataBase i : person1){
            if(i.getFirstName().equals(firstName) && i.getLastName().equals(lastName) && i.getId() != person)
                return false;
        }
        String sql =  "UPDATE Person SET firstName = ? , "
                + "lastName = ? ,"
                +" member = ?,"
                +" dept = ?,"
                +" totalMoney = ?"
                + "WHERE Id = ?";
        connectDb3();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setInt(3,member);
        preparedStatement.setString(4,dept);
        preparedStatement.setString(5,totalMoney);
        preparedStatement.setInt(6,person);
        preparedStatement.executeUpdate();
        connection.close();
        statement.close();
        if (dept.equals("0")){
            try {
                ConnectDB.insertPersonHowCanGiveVam(person);
            }
            catch (SQLException ex){
                ex.printStackTrace();
                System.out.println("I do it before");
            }
        }
        else {
            try {
                ConnectDB.delatePersonVam(person);
                System.out.println("do it sucssesfuly");
            }
            catch (SQLException ex){
                ex.printStackTrace();
                System.out.println("He/or she not here");
            }
        }
        return true;
    }
    public static ObservableList<PersonInDataBase> selectAllPerson() throws SQLException {
        ObservableList<PersonInDataBase> persons = FXCollections.observableArrayList();
        String sql = "SELECT id, firstName,lastName,dept,totalMoney,member  FROM Person ";
        connectDb3();
        Statement stmt   = connection.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()) {
            persons.add(new PersonInDataBase(rs.getString("firstName"), rs.getString("lastName"), rs.getString("dept"), rs.getString("totalMoney"), rs.getInt("member"), rs.getInt("id")));
        }
        connection.close();
        statement.close();
        return persons;
    }
    public static PersonInDataBase selectPerson(String firstName ,String lastName) throws SQLException {
        String sql = "SELECT id, firstName,lastName,dept,totalMoney,member  FROM Person WHERE firstName = ? AND lastName = ?";
        connectDb3();
        PreparedStatement pstmt  = connection.prepareStatement(sql);
        pstmt.setString(1,firstName);
        pstmt.setString(2,lastName);
        ResultSet rs  = pstmt.executeQuery();
        PersonInDataBase personInDataBase = new PersonInDataBase(firstName,lastName,rs.getString("dept"),rs.getString("totalMoney"),rs.getInt("member"),rs.getInt("Id"));
        connection.close();
        statement.close();
        return personInDataBase;
    }

    public static ArrayList<VamInDataBase> selectAllVam() throws SQLException {
        ArrayList<VamInDataBase> vamInDataBases = new ArrayList<>();
        String sql = "SELECT person, amount,day,month,year,chek  FROM vam";
        connectDb3();
        Statement stmt = connection.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()){
            vamInDataBases.add(new VamInDataBase(rs.getString("amount"),rs.getInt("day"),rs.getInt("month"),rs.getInt("year"),rs.getString("chek"),rs.getInt("person")));
        }
        connection.close();
        stmt.close();
        return vamInDataBases;
    }
    public static ObservableList<HaghSandoghInDataBase> selectAllHagheSandogh() throws SQLException {
        ObservableList<HaghSandoghInDataBase> haghSandoghInDataBases = FXCollections.observableArrayList();
        String sql = "SELECT explain,person, pardakht,day,month,year,Chek  FROM HaghSandoh";
        connectDb3();
        Statement stmt = connection.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()){
            haghSandoghInDataBases.add(new HaghSandoghInDataBase(rs.getString("explain"),rs.getInt("day"),rs.getInt("month"),rs.getInt("year"),rs.getString("pardakht"),rs.getInt("person"),rs.getString("Chek")));
        }
        connection.close();
        stmt.close();
        return haghSandoghInDataBases;

    }
    public static ObservableList<GhestInDataBase> selectAllGhest(int id) throws SQLException {
        ObservableList<GhestInDataBase> ghests = FXCollections.observableArrayList();
        String sql = "SELECT explain,person, pardakht,day,month,year,Chek  FROM Ghest";
        connectDb3();
        Statement stmt = connection.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()){
            if (rs.getInt("person") == id)
                ghests.add(new GhestInDataBase(rs.getString("explain"),rs.getInt("day"),rs.getInt("month"),rs.getInt("year"),rs.getString("pardakht"),rs.getInt("person"),rs.getString("Chek")));
        }
        connection.close();
        stmt.close();
        return ghests;
    }
    public static ObservableList<GhestInDataBase> selectAllGhest() throws SQLException {
        ObservableList<GhestInDataBase> ghests = FXCollections.observableArrayList();
        String sql = "SELECT explain,person, pardakht,day,month,year,Chek  FROM Ghest";
        connectDb3();
        Statement stmt = connection.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()){
            ghests.add(new GhestInDataBase(rs.getString("explain"),rs.getInt("day"),rs.getInt("month"),rs.getInt("year"),rs.getString("pardakht"),rs.getInt("person"),rs.getString("Chek")));
        }
        connection.close();
        stmt.close();
        return ghests;
    }
    public static ObservableList<PersonInDataBase> selectAllPersonCanGiveVam() throws SQLException {
        ObservableList<PersonInDataBase> persons = selectAllPerson();
        ArrayList<Integer>arrayList = new ArrayList<>();
        ObservableList<PersonInDataBase> personAccept = FXCollections.observableArrayList();
        String sql = "SELECT person FROM PersonVam";
        connectDb3();
        Statement stmt = connection.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);

        while (rs.next()){ //todo I must make it better
            for(PersonInDataBase i : persons) {
                int g = rs.getInt("person");
                if ( g ==i.getId())
                personAccept.add(i);
            }
        }
        connection.close();
        stmt.close();
//        for (PersonInDataBase i : persons){
//            if(arrayList.contains(i.getId()))
//                personAccept.add(arrayList.indexOf(i.getId()),i);
//        }
        return personAccept;
    }
    public static ObservableList<SandoghInDataBase> selectAllSandoghPosition() throws SQLException {
        ObservableList<SandoghInDataBase> sandoghInDataBases = FXCollections.observableArrayList();
        String sql = "SELECT month ,year,totalMoney,enter,exit,keys FROM Sandogh";
        connectDb3();
        Statement stmt = connection.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()){
            sandoghInDataBases.add(new SandoghInDataBase(rs.getInt("month"),rs.getInt("year"),rs.getString("totalMoney"),rs.getString("enter"),rs.getString("exit")));
        }
        connection.close();
        stmt.close();
        return sandoghInDataBases;
    }
    public static String selectSaham() throws SQLException {
        ArrayList<String> saham = new ArrayList<String>();
        String sql = "SELECT amount FROM Saham";
        connectDb3();
        Statement stmt = connection.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()){
            saham.add(rs.getString("amount"));
        }
        connection.close();
        stmt.close();
        if (saham.size()>0)
            return saham.get(saham.size()-1);
        return "0";
    }



}
