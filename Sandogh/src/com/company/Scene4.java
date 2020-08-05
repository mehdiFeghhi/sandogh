package com.company;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Scene4 {
    public Stage stage;
    public Scene scene;
    public Scene4(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }
    public void display(String name,String lastName){

        GridPane root = new GridPane();
        root.setPadding(new Insets(50));
        root.setVgap(20);
        root.setHgap(5);
        Scene scene = new Scene(root,600,500);
        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints(300,150,Double.MAX_VALUE);
        column2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(column1,column2);
        //////////////////////////////////////////////////////////////////
        Label first = new Label("نام ");
        GridPane.setHalignment(first, HPos.LEFT);
        Label first2 = new Label(name);
        GridPane.setHalignment(first2,HPos.LEFT);
        Label last = new Label("نام خانوادگی ");
        GridPane.setHalignment(last,HPos.LEFT);
        Label last2 = new Label(lastName);
        GridPane.setHalignment(last2,HPos.LEFT);
        Label users = new Label("تعداد اعضا");
        GridPane.setHalignment(users,HPos.LEFT);
        Label debt  = new Label("بدهی ");
        GridPane.setHalignment(debt,HPos.LEFT);
        Label total = new Label("کل موجودی ");
        GridPane.setHalignment(total,HPos.LEFT);
        TextField textFieldusers = new TextField();
        GridPane.setHalignment(textFieldusers,HPos.LEFT) ;
        TextField textFielddebt = new TextField();
        GridPane.setHalignment(textFielddebt,HPos.LEFT) ;
        TextField textFieldTotal = new TextField();
        GridPane.setHalignment(textFieldTotal,HPos.LEFT) ;
        /////////////////////////////////////////////////////////

        Button buttonSave = new Button("ذخیزه‌‌");
        GridPane.setHalignment(buttonSave,HPos.CENTER);
        buttonSave.setOnAction( e-> Save(name,lastName,Integer.valueOf(textFieldusers.getText()),textFielddebt.getText(),textFieldTotal.getText()));
        Button buttonRet = new Button("بازگشت");
        GridPane.setHalignment(buttonRet,HPos.RIGHT);
        buttonRet.setOnAction( e ->{
            stage.setScene(this.scene);
            this.stage.setFullScreen(true);
        });
        ////////////////////////////////////////////////
        root.add(first,0,0);
        root.add(first2,1,0);
        root.add(last,0,1);
        root.add(last2,1,1);
        root.add(users,0,2);
        root.add(textFieldusers,1,2);
        root.add(debt,0,3);
        root.add(textFielddebt,1,3);
        root.add(total,0,4);
        root.add(textFieldTotal,1,4);
        root.add(buttonRet,0,5);
        root.add(buttonSave,1,5);
        this.stage.setScene(scene);
        this.stage.setFullScreen(true);
        this.stage.show();

    }

    private void Save(String name, String lastName, Integer valueOf, String text, String text1) {
        if(name != "" && lastName != "" && valueOf != null && text != "" && text1!= ""){
            try {
                ConnectDB.insertPerson(name, lastName,valueOf,text,text1);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("we have problem");
            }
        }
    }

}
