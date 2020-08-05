package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//import java.awt.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ClosePrograme {
    public Stage stage ;
    public ClosePrograme(Stage stage){
        this.stage = stage;
    }
    public void getClose() {
        BorderPane borderPane = new BorderPane();
        Stage stage2 = new Stage();
        Label label = new Label("آیا می خواهید از برنامه خارج شوید ؟");
        label.setAlignment(Pos.CENTER);
        BorderPane.setMargin(label,new Insets(24,6,12,32));
        borderPane.setTop(label);
        Button buttonYes = new Button("بله");
        BorderPane.setMargin(buttonYes,new Insets(12,24,12,24));
        borderPane.setRight(buttonYes);
        buttonYes.setOnAction( e -> {
            stage2.close();
            this.stage.close();
        });
        Button buttonNo = new Button("خیر");
        BorderPane.setMargin(buttonNo,new Insets(12,24,12,24));
        borderPane.setLeft(buttonNo);
        buttonNo.setOnAction( e -> stage2.close());
        //borderPane.getChildren().addAll(label,buttonYes,buttonNo);
        Scene scene = new Scene(borderPane,250,100);
        stage2.setScene(scene);
        stage2.show();
    }

}
