package com.company;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.SQLException;
//import java.awt.*;


public class PersonPage {
        public Stage stage;
        public Scene scene;
        public PersonInDataBase personInDataBase;
        public ShowTableOfPardakhtHaghSandogh hagheSandogh;
        public PardakhtGhest pardakhtGhest;
        public PersonPage(Stage stage, Scene scene, String name, String lastName) {
            this.scene = scene;
            this.stage = stage;
            try {
                this.personInDataBase = ConnectDB.selectPerson(name,lastName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.hagheSandogh = new ShowTableOfPardakhtHaghSandogh(personInDataBase,this.scene,this.stage);
            this.pardakhtGhest = new PardakhtGhest(personInDataBase,this.scene,this.stage);
        }

        public void display() {
            VBox rootAndAllOfVam = new VBox();
            HBox main = new HBox();
            VBox VamAndHaghSandogh = new VBox();
            HBox nap = new HBox();
            //nap.setPrefSize(900,100);
            VamAndHaghSandogh.getChildren().addAll(this.hagheSandogh.displayTable(),nap,this.pardakhtGhest.displayTable());
            GridPane root = new GridPane();
            root.setPadding(new Insets(50));
            root.setVgap(50);
            root.setHgap(20);
            main.getChildren().addAll(root,VamAndHaghSandogh);
            Scene scene = new Scene(main);


            ColumnConstraints column1 = new ColumnConstraints(100);
            ColumnConstraints column2 = new ColumnConstraints(100);
            ColumnConstraints column3 = new ColumnConstraints(100);
            column2.setHgrow(Priority.ALWAYS);
            root.getColumnConstraints().addAll(column1,column2,column3);
            Label label = new Label("اطلاعات شخصی");
            GridPane.setHalignment(label, HPos.LEFT);
            root.add(label,1,0);
            Label name_lastName = new Label("نام و نام خانوادگی");
            GridPane.setHalignment(name_lastName,HPos.LEFT);
            root.add(name_lastName,1,1);
            Label users = new Label("تعداد اعضا");
            GridPane.setHalignment(users,HPos.LEFT);
            root.add(users,1,2);
            Label dept = new Label("بدهی");
            GridPane.setHalignment(dept,HPos.LEFT);
            root.add(dept,1,3);
            Label total = new Label("کل موجودی");
            GridPane.setHalignment(total,HPos.LEFT);
            root.add(total,1,4);
            Label name_lastName2 = new Label(this.personInDataBase.getFirstName()+"  "+this.personInDataBase.getLastName());
            GridPane.setHalignment(name_lastName2,HPos.LEFT);
            root.add(name_lastName2,2,1);
            Label users2 = new Label(String.valueOf(this.personInDataBase.getMember()));
            GridPane.setHalignment(users2,HPos.LEFT);
            root.add(users2,2,2);
            Label dept2 = new Label(String.valueOf(this.personInDataBase.getDept())+"ریال        ");
            GridPane.setHalignment(dept2,HPos.LEFT);
            root.add(dept2,2,3);
            Label total2 = new Label(String.valueOf(this.personInDataBase.getTotalMoney())+"ریال       ");
            GridPane.setHalignment(total2,HPos.LEFT);
            root.add(total2,2,4);
            /////////////////////////////////////////////////

            Button update = new Button("اصلاح");
            update.setOnAction(e ->{
                    stage.setScene(new Update(scene,this.stage).display(this.personInDataBase.getFirstName(),this.personInDataBase.getLastName(),this.personInDataBase.getDept(),this.personInDataBase.getTotalMoney(),this.personInDataBase.getMember(),this.personInDataBase.getId()));

            });

            ////////////////////////////////////////////////
            GridPane.setHalignment(update,HPos.LEFT);
            root.add(update,0,1);
            //Button padakhtHagSandogh = new Button("پرداخت حق صندوق");
            //GridPane.setHalignment(padakhtHagSandogh,HPos.LEFT);
            //root.add(padakhtHagSandogh,0,1);
            //Button pardakhtHaghest = new Button("پرداخت قسط");
            //GridPane.setHalignment(padakhtHagSandogh,HPos.LEFT);
            //root.add(pardakhtHaghest,0,2);
            Button pardakhtVam = new Button("پرداخت وام");
            pardakhtVam.setOnAction(e -> {
                try {
                    ClickButtun();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            GridPane.setHalignment(pardakhtVam,HPos.LEFT);
            root.add(pardakhtVam,0,3);
            Button ret = new Button("برگشت");
            ret.setOnAction(e -> {
                stage.setScene(this.scene);
                this.stage.setFullScreen(true);
            });
            GridPane.setHalignment(ret,HPos.LEFT);
            root.add(ret,0,2);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        }

    private void ClickButtun() throws SQLException {
            if (this.personInDataBase.getDept().equals("0")){
                new GerftanVam(this).getScence();
            }
            else {

                new Error("این شخص بدهی داره چه جور می خوای بهش وام بدی؟ ").display();
            }
    }

}
