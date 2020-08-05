package com.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.math.BigInteger;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShowTableOfPardakhtHaghSandogh {
    private ObservableList<HaghSandoghInDataBase> haghSandoghInDataBases;
    private PersonInDataBase personInDataBase;
    private BigInteger saham;
    public TextField explain,priceInput, dayInput,monthInput,yearInput,shomarehInput;
    public Scene scene;
    public Stage stage;
    public TableView<HaghSandoghInDataBase> table = new TableView<>();

    public ShowTableOfPardakhtHaghSandogh(PersonInDataBase personInDataBase,Scene scene,Stage stage) {
        this.haghSandoghInDataBases = findInDataBase(personInDataBase);
        this.personInDataBase = personInDataBase;
        this.scene = scene;
        this.stage = stage;
        try {
            this.saham = new BigInteger(ConnectDB.selectSaham());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<HaghSandoghInDataBase> findInDataBase(PersonInDataBase personInDataBase) {
        ObservableList<HaghSandoghInDataBase> haghSandoghInDataBases = FXCollections.observableArrayList();

        try {
            haghSandoghInDataBases = ConnectDB.selectAllHagheSandogh();
            if(!haghSandoghInDataBases.isEmpty()) {
                haghSandoghInDataBases.removeIf(i -> i.getPerson() != personInDataBase.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return haghSandoghInDataBases;
    }

    public VBox displayTable() {
        //explain column
        TableColumn<HaghSandoghInDataBase, String> explainColumn = new TableColumn<>("دریافتی صندوق");
        explainColumn.setMinWidth(600);
        explainColumn.setCellValueFactory(new PropertyValueFactory<>("explain"));

        //Money column
        TableColumn<HaghSandoghInDataBase, Integer> moneyColumn = new TableColumn<>("مقدار واریزی");
        moneyColumn.setMinWidth(70);
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("pardakht"));

        //Date column
        TableColumn<HaghSandoghInDataBase,String> dateColumn = new TableColumn<>("تاریخ واریز");
        dateColumn.setMaxWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date3"));


        //shomareh check
        TableColumn<HaghSandoghInDataBase,String> somarhCheckColumn = new TableColumn<>("شماره چک");
        somarhCheckColumn.setMaxWidth(100);
        somarhCheckColumn.setCellValueFactory(new PropertyValueFactory<>("chek"));
        //explain input

        explain= new TextField();
        explain.setPromptText("توضیحات");
        explain.setMinWidth(400);

        //Price input
        priceInput= new TextField();
        priceInput.setText(String.valueOf(this.saham.multiply(BigInteger.valueOf(personInDataBase.getMember()))));


        // shomareh check input
        shomarehInput = new TextField();
        shomarehInput.setPromptText("شماره چک");
        //day input
        dayInput = new TextField();
        dayInput.setPromptText("روز");
        dayInput.setPrefSize(30,10);

        //month input
        monthInput = new TextField();
        monthInput.setPromptText("ماه");
        monthInput.setPrefSize(30,10);

        //year input
        yearInput = new TextField();
        yearInput.setPromptText("سال");
        yearInput.setPrefSize(45,10);
        //Button
        Button addButton = new Button("اضافه کردن");
        addButton.setOnAction(e -> {
            try {
                addButtonClicked();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        Button deleteButton = new Button("حذف کردن ");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(explain, priceInput, dayInput,monthInput,yearInput,shomarehInput,addButton, deleteButton);


        this.table.setItems(haghSandoghInDataBases);
        this.table.getColumns().addAll(explainColumn,moneyColumn,dateColumn,somarhCheckColumn);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);
        return vBox;
    }

    private void deleteButtonClicked() {
        TableView.TableViewSelectionModel selectionModel = this.table.getSelectionModel();

    }

    private void addButtonClicked() throws SQLException {
        boolean commite = false;
        if (this.monthInput.getText() != null && this.dayInput.getText() != null && this.yearInput.getText() != null && this.priceInput.getText() != null && this.shomarehInput.getText() != null) {
            BigInteger bigInteger = new BigInteger(this.personInDataBase.getTotalMoney());
            bigInteger = bigInteger.add(new BigInteger(this.priceInput.getText()));
            this.personInDataBase.setTotalMoney(bigInteger.toString());
            ConnectDB.insertHaghSandogh(this.explain.getText(),Integer.valueOf(this.dayInput.getText()),Integer.valueOf(this.monthInput.getText()),Integer.valueOf(this.yearInput.getText()),this.priceInput.getText(),this.personInDataBase.getId(),this.shomarehInput.getText());
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
                        BigInteger f = new BigInteger(i.getEnter());
                        f = f.add(new BigInteger(this.priceInput.getText()));
                        ConnectDB.updateSandogh(i.getMonth(),i.getYear(),s.toString(),f.toString(),i.getExit());
                        commite = true;
                    }
            }
            if (commite == false){
                g = g.add(new BigInteger(this.priceInput.getText()));
                ConnectDB.insertSandogh(Integer.valueOf(this.monthInput.getText()),Integer.valueOf(this.yearInput.getText()),g.toString(),this.priceInput.getText(),"0");
            }
            try {
                ConnectDB.updatePerson(this.personInDataBase.getFirstName(), this.personInDataBase.getLastName(), this.personInDataBase.getMember(), this.personInDataBase.getDept(), this.personInDataBase.getTotalMoney(), personInDataBase.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PersonPage personPage = new PersonPage(this.stage,this.scene,personInDataBase.getFirstName(),personInDataBase.getLastName());
            personPage.display();
        }
    }


}
