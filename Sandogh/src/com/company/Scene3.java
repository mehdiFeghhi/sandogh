package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;


public class Scene3 {
    private String hageSandogFromDataBase = ConnectDB.selectSaham();
    private ObservableList<SandoghInDataBase> all= ConnectDB.selectAllSandoghPosition();
    private SandoghInDataBase lastPostion;
    private Scene mainScene;
    private Stage mainStage;
    private Button button = new Button("بررسی");
    private Button buttonRet = new Button("برگشت");
    private ListView<PersonInDataBase> listViewMain = new ListView();
    public Scene3(Scene mainScene, Stage mainStage) throws SQLException {
        this.mainScene = mainScene;
        this.mainStage = mainStage;
        if(!this.all.isEmpty())
            this.lastPostion = this.all.get(this.all.size()-1);

    }

    public void  getScene() {
        GridPane root1 = new GridPane();
        root1.setPadding(new Insets(100,10,10,10));
        root1.setVgap(5);
        root1.setHgap(10);
        Label label12 = new Label("حق صندوق فعلی ");
        Label label22 = new Label(this.hageSandogFromDataBase+"ریال    ");
        GridPane.setConstraints(label12,0,0);
        GridPane.setConstraints(label22,5,0);
        Button button12 = new Button("تایید");
        GridPane.setConstraints(button12,0,1,1,2);
        TextField textField = new TextField();
        textField.setPromptText("حق صندوق جدید را وارد کنید  ریال");
        button12.setOnAction(e -> changeSaham(textField.getText()));
        textField.setPrefWidth(250);
        GridPane.setConstraints(textField,2,1,4,2);
        root1.getChildren().addAll(label12,label22,button12,textField);


        ///////////////////////////////
        VBox root = new VBox();
        HBox hboxTop = new HBox();
        HBox hboxButton = new HBox();
        hboxButton.setAlignment(Pos.CENTER);
        Label label = new Label("مقدار موجودی کل");
        Label label1 = new Label();
        if (this.lastPostion == null)
            label1.setText("0");
        else
            label1.setText(this.lastPostion.getTotalMoney());
        hboxButton.getChildren().addAll(label,label1);

        button.setOnAction(e -> buttonClicked());
        buttonRet.setOnAction(e -> {
            this.mainStage.setScene(mainScene);
            this.mainStage.setFullScreen(true);
        });
        hboxTop.getChildren().addAll(ViewOfAllPersonIsEnd(),root1,getListViewOfAllEnterAndExitOfOneMonth());
        root.getChildren().addAll(hboxTop,hboxButton);
        this.mainStage.setScene(new Scene(root));
        this.mainStage.setY(100);
        this.mainStage.setX(500);
        this.mainStage.show();
    }

    private void changeSaham(String text) {
        if(text != null && text != "" && helper.isNumeric(text)){
            try {
                ConnectDB.insertSahm(text);
                new Scene3(this.mainScene,this.mainStage).getScene();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private ListView getListViewOfAllEnterAndExitOfOneMonth() {
        ListView<SandoghInDataBase> listView = new ListView();
        ObservableList<SandoghInDataBase> list1 = FXCollections.observableArrayList();
        for (int i = this.all.size()-1; i >-1 ; i--){
            list1.add(this.all.get(i));
        }
        listView.getItems().addAll(list1);
        return listView;

    }
    private VBox ViewOfAllPersonIsEnd() {
        VBox vBox = new VBox();
        ObservableList<PersonInDataBase> list = FXCollections.observableArrayList();
        try {
            list = findInDataBasePersonEnd();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.listViewMain.getItems().addAll(list);
        vBox.getChildren().addAll(this.listViewMain,new HBox(this.button,this.buttonRet));
        return vBox;
    }

    private ObservableList<PersonInDataBase> findInDataBasePersonEnd() throws SQLException {
        ObservableList<PersonInDataBase> list = FXCollections.observableArrayList();
        list = ConnectDB.selectAllPersonCanGiveVam();
        return list;
    }
    private void buttonClicked(){
        PersonInDataBase personInDataBase=  this.listViewMain.getSelectionModel().getSelectedItem();
        new PersonPage(this.mainStage,this.mainScene,personInDataBase.getFirstName(),personInDataBase.getLastName()).display();
    }


}
