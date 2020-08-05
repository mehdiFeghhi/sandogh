package com.company;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Scene2 {
    public Stage stage;
    public Scene scene;
    public Scene2(Scene scene,Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    public void getScene() {
        GridPane root = new GridPane();
        root.setPadding(new Insets(150));
        root.setVgap(20);
        root.setHgap(5);
        Scene scene = new Scene(root,600,500);
        //////////////////////////////////////////////////////
        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints(300,150,Double.MAX_VALUE);
        column2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(column1,column2);
        Label label1 = new Label("نام     ");
        TextField textFieldName = new TextField();
        Label label2 = new Label("نام خانوادگی  ");
        TextField textFieldLast = new TextField();
        //////////////////////////////////////////////////

        Button ret = new Button("بازگشت");
        ret.setOnAction( e -> {
            stage.setScene(this.scene);
        });

        //////////////////////////////////////////////////////////
        Button login = new Button("ورود به صحفه");

            login.setOnAction( e -> {
                if(isInDateBase(textFieldName.getText(),textFieldLast.getText()))
                     new PersonPage(stage,scene,textFieldName.getText(),textFieldLast.getText()).display();
                else {
                    new Error("همچین شخصی موجود نسیت").display();
                }
            });

        ///////////////////////////////////////////////////////////
        Button singIn = new Button("ثبت شخص");
        singIn.setOnAction( e -> {
            if(!isInDateBase(textFieldName.getText(),textFieldLast.getText())) {
                new Scene4(scene, this.stage).display(textFieldName.getText(), textFieldLast.getText());
            }
            else{
                new Error("همچین شخصی در سیستم هست").display();
            }
        });

        /////////////////////////////////////////////////////////////
        GridPane.setHalignment(label1, HPos.LEFT);
        GridPane.setHalignment(textFieldLast, HPos.LEFT);
        GridPane.setHalignment(label2, HPos.LEFT);
        GridPane.setHalignment(textFieldLast, HPos.LEFT);
        GridPane.setHalignment(login,HPos.CENTER);
        GridPane.setHalignment(singIn,HPos.CENTER);
        GridPane.setHalignment(ret,HPos.CENTER);
        root.add(label1,0,0);
        root.add(textFieldName,1,0);
        root.add(label2,0,1);
        root.add(textFieldLast,1,1);
        root.add(login,1,2);
        root.add(singIn,1,3);
        root.add(ret,1,4);

        this.stage.setScene(scene);
        this.stage.setFullScreen(true);
        this.stage.show();
    }

    private boolean isInDateBase(String text,String text1) {
        try {
            return ConnectDB.isThisPersonIndataBase(text,text1);
        } catch (SQLException e) {
            System.out.println("connection is fall");
            e.printStackTrace();
            return false;
        }
    }
}
