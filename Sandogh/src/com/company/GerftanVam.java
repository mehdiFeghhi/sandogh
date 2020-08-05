package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.sql.SQLException;

public class GerftanVam {
    private Stage stage = new Stage();
    private BigInteger totalSaham;
    private ObservableList<SandoghInDataBase> sandoghInDataBase = ConnectDB.selectAllSandoghPosition();
    private BigInteger AllDaray ;
    private PersonPage personPage ;
    private TextField yearInput, monthInput, dayInput, priceInput, shomarehInput;
    public GerftanVam(PersonPage personPage) throws SQLException {
        this.personPage = personPage;
        this.totalSaham = new BigInteger(personPage.personInDataBase.getTotalMoney());
        if (this.sandoghInDataBase.size()>0)
            this.AllDaray = new BigInteger(sandoghInDataBase.get(sandoghInDataBase.size()-1).getTotalMoney());
        else
            this.AllDaray = new BigInteger("0");
    }

    public void getScence() {
        String canGive = "";
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        HBox hBox1 = new HBox();
        hBox1.setSpacing(20);
        Label label1 = new Label("حداکتر مبلغ پرداختی");
        Label label2 = new Label();
        if (this.AllDaray.compareTo(this.totalSaham.multiply(new BigInteger(String.valueOf(personPage.personInDataBase.getMember()))))==-1) {
            canGive = String.valueOf(this.AllDaray);//todo when I make data base
        }
        else {
           canGive = String.valueOf(this.totalSaham.multiply(new BigInteger(String.valueOf(this.personPage.personInDataBase.getMember()))));
        }
        label2.setText(canGive);
        hBox1.getChildren().addAll(label1,label2);
        VBox.setMargin(hBox1,new Insets(20,20,20,20));
        HBox hBox2 = new HBox();
        VBox.setMargin(hBox2,new Insets(20,20,20,20));
        Label label3 = new Label("مبلغی که براساس حق صندوق گیرش میاد");
        Label label4 = new Label(String.valueOf((this.totalSaham.multiply(new BigInteger(String.valueOf(personPage.personInDataBase.getMember()))))));
        hBox2.getChildren().addAll(label3,label4);

        HBox hBox3 = new HBox();
        VBox.setMargin(hBox3,new Insets(20,20,20,20));
        Label label5 = new Label("مبلغ درخولستی");
        priceInput = new TextField();
        priceInput.setText(canGive);
        hBox3.getChildren().addAll(label5, priceInput);
        HBox hBox4 = new HBox();
        VBox.setMargin(hBox4,new Insets(20,20,20,20));

        Label label6 = new Label("تاریخ");
        yearInput = new TextField();
        yearInput.setPromptText("سال");

        monthInput = new TextField();
        priceInput.setPromptText("ماه");
        dayInput = new TextField();
        dayInput.setPromptText("روز");
        hBox4.getChildren().addAll(label6, yearInput, monthInput, dayInput);
        HBox hBox5 = new HBox();
        VBox.setMargin(hBox5,new Insets(20,20,20,20));

        Label label7 = new Label("شماره چک");
        shomarehInput = new TextField();
        hBox5.getChildren().addAll(label7, shomarehInput);
        HBox hBox6 = new HBox();
        Button save = new Button("ذخیره");
        save.setOnAction(e -> {
            try {
                save();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        Button exit = new Button("برگشت");
        exit.setOnAction(e ->exit());
        VBox.setMargin(hBox6,new Insets(20,20,20,20));
        hBox6.getChildren().addAll(save,exit);
        hBox1.setSpacing(20);
        hBox2.setSpacing(20);
        hBox3.setSpacing(20);
        hBox4.setSpacing(20);
        hBox5.setSpacing(20);
        hBox6.setSpacing(20);
        vBox.getChildren().addAll(hBox1,hBox2,hBox3,hBox4,hBox5,hBox6);
        vBox.setAlignment(Pos.CENTER);

        this.stage.setX(500);
        this.stage.setY(100);
        this.stage.setScene(new Scene(vBox));
        this.stage.show();
    }

    private void exit() {
        this.stage.close();
    }

    
    private void save() throws SQLException {
        boolean commite = false;
        if (this.monthInput.getText() != null && this.yearInput.getText() != null && this.yearInput.getText() != null && this.priceInput.getText() != null && this.shomarehInput.getText() != null) {
            BigInteger bigInteger = new BigInteger(this.personPage.personInDataBase.getTotalMoney());
            bigInteger = bigInteger.subtract(new BigInteger(this.priceInput.getText()));
            this.personPage.personInDataBase.setDept(bigInteger.toString());
            ConnectDB.insertVam(this.priceInput.getText(),Integer.valueOf(this.yearInput.getText()),Integer.valueOf(this.monthInput.getText()),Integer.valueOf(this.dayInput.getText()),this.shomarehInput.getText(),this.personPage.personInDataBase.getId());
            ObservableList<SandoghInDataBase>sandoghInDataBases= ConnectDB.selectAllSandoghPosition();
            BigInteger g = new BigInteger("0");
            for (SandoghInDataBase i : sandoghInDataBases){
                if (sandoghInDataBases.indexOf(i) == sandoghInDataBases.size()-1){
                    g = new BigInteger(i.getTotalMoney());
                }
                if (i.getMonth() == Integer.valueOf(this.monthInput.getText()) && i.getYear() == Integer.valueOf(this.yearInput.getText())){
                    BigInteger s = new BigInteger(i.getTotalMoney());
                    s = s.add(new BigInteger(this.priceInput.getText()));
                    //System.out.println(this.priceInput.getText());
                    BigInteger f = new BigInteger(i.getExit());
                    f = f.add(new BigInteger(this.priceInput.getText()));
                    ConnectDB.updateSandogh(i.getMonth(),i.getYear(),s.toString(),i.getEnter(),f.toString());
                    commite = true;
                }
            }
            if (commite == false){
                g = g.add(new BigInteger(this.priceInput.getText()));
                ConnectDB.insertSandogh(Integer.valueOf(this.monthInput.getText()),Integer.valueOf(this.yearInput.getText()),g.toString(),"0",this.priceInput.getText());
            }
            try {
                ConnectDB.updatePerson(this.personPage.personInDataBase.getFirstName(), this.personPage.personInDataBase.getLastName(), this.personPage.personInDataBase.getMember(), this.personPage.personInDataBase.getDept(), this.personPage.personInDataBase.getTotalMoney(), this.personPage.personInDataBase.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            personPage.display();
            this.stage.close();
        }
    }
}
