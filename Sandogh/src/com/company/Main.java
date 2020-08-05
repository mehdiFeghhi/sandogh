package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.SQLException;
//import java.awt.*;

public class Main  extends Application {

    public static void main(String[] args) {
	// write your code here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();

        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        /////////////////////////////////////////////////////////////////////
        Button button2 = new Button("کار با عضو");
        button2.setOnAction( e ->new Scene2(scene,stage).getScene());
        button2.setPrefSize(200,200);
        Button button3 = new Button("بررسی وضعیت کل صندوق");
        button3.setOnAction(e -> {
            try {
                new Scene3(scene,stage).getScene();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        button3.setPrefSize(200,200);
        Button button4 = new Button("خروج");
        button4.setOnAction( e -> new ClosePrograme(stage).getClose());
        button4.setPrefSize(200,50);
        ///////////////////////////////////////////////////////////////////
        root.getChildren().addAll(button2,button3,button4);
        stage.setOnCloseRequest( e ->{
            e.consume();
            new ClosePrograme(stage).getClose();
        } );
        stage.setScene(scene);
        stage.show();
    }
}
