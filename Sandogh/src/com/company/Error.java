package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class Error {
    public String massage;
    public Error(String massage){
        this.massage = massage;
    }
    public void display(){
        Stage stage = new Stage();
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root,200,100);
        Label label = new Label(this.massage);
        Button Ok = new Button("تایید");
        BorderPane.setMargin(label,new Insets(12,12,12,12));
        BorderPane.setMargin(Ok,new Insets(24,12,6,12));
        Ok.setOnAction(e -> stage.close());
        root.setTop(label);
        root.setRight(Ok);
        stage.setScene(scene);
        stage.show();
    }
}
