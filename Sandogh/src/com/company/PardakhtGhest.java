package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
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

public class PardakhtGhest {
    private ObservableList<GhestInDataBase> Ghest;
    private Stage stage;
    private Scene scene;
    private TextField explain, priceInput, dayInput,monthInput,yearInput,shomarehInput;
    private PersonInDataBase personInDataBase;
    public PardakhtGhest(PersonInDataBase personInDataBase , Scene scene,Stage stage) {
        this.scene = scene;
        this.stage = stage;
        this.personInDataBase = personInDataBase;
        this.Ghest = findInDataBase(this.personInDataBase.getId());
    }

    public VBox displayTable() {
        TableView<GhestInDataBase> table = new TableView<>();

        //explain column
        TableColumn<GhestInDataBase, String> explainColumn = new TableColumn<>("پرداخت قسط");
        explainColumn.setMinWidth(600);
        explainColumn.setCellValueFactory(new PropertyValueFactory<>("explain"));

        //Money column
        TableColumn<GhestInDataBase, Integer> moneyColumn = new TableColumn<>("مقدار واریزی");
        moneyColumn.setMinWidth(70);
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("pardakht"));

        //Date column
        TableColumn<GhestInDataBase,String> dateColumn = new TableColumn<>("تاریخ واریز");
        dateColumn.setMaxWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date3"));


        //shomareh check
        TableColumn<GhestInDataBase,String> somarhCheckColumn = new TableColumn<>("شماره چک");
        somarhCheckColumn.setMaxWidth(100);
        somarhCheckColumn.setCellValueFactory(new PropertyValueFactory<>("chek"));
        //explain input
        this.explain= new TextField();
        explain.setPromptText("توضیحات");
        explain.setMinWidth(400);

        //Price input
        this.priceInput= new TextField();
        priceInput.setPromptText("مقدار را وارد کنید");
        //Todo when I work with data base



        // shomareh check input
        this.shomarehInput = new TextField();
        shomarehInput.setPromptText("شماره چک");
        //day input
        this.dayInput = new TextField();
        dayInput.setPromptText("روز");
        dayInput.setPrefSize(30,10);

        //month input
        this.monthInput = new TextField();
        monthInput.setPromptText("ماه");
        monthInput.setPrefSize(30,10);

        //year input
        this.yearInput = new TextField();
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


        table.setItems(Ghest);
        table.getColumns().addAll(explainColumn,moneyColumn,dateColumn,somarhCheckColumn);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);
        return vBox;
    }

    private void deleteButtonClicked() {

    }
    private void addButtonClicked() throws SQLException {
        boolean commite = false;
        if (this.monthInput.getText() != null && this.dayInput.getText() != null && this.yearInput.getText() != null && this.priceInput.getText() != null && this.shomarehInput.getText() != null) {
            BigInteger bigInteger = new BigInteger(this.personInDataBase.getDept());
            BigInteger bigInteger1 = new BigInteger(this.priceInput.getText());
            if (bigInteger.compareTo(bigInteger1) >= 0) {
                bigInteger = bigInteger.subtract(bigInteger1);
                this.personInDataBase.setDept(bigInteger.toString());
                ConnectDB.insertGhest(this.explain.getText(), Integer.valueOf(this.dayInput.getText()), Integer.valueOf(this.monthInput.getText()), Integer.valueOf(this.yearInput.getText()), this.priceInput.getText(), this.personInDataBase.getId(), this.shomarehInput.getText());
                ObservableList<SandoghInDataBase> sandoghInDataBases = ConnectDB.selectAllSandoghPosition();
                BigInteger g = new BigInteger("0");
                for (SandoghInDataBase i : sandoghInDataBases) {
                    if (sandoghInDataBases.indexOf(i) == sandoghInDataBases.size() - 1) {
                        g = new BigInteger(i.getTotalMoney());
                    }
                    if (i.getMonth() == Integer.valueOf(this.monthInput.getText()) && i.getYear() == Integer.valueOf(this.yearInput.getText())) {
                        BigInteger s = new BigInteger(i.getTotalMoney());
                        s = s.add(new BigInteger(this.priceInput.getText()));
                        //System.out.println(this.priceInput.getText());
                        BigInteger f = new BigInteger(i.getEnter());
                        f = f.add(new BigInteger(this.priceInput.getText()));
                        ConnectDB.updateSandogh(i.getMonth(), i.getYear(), s.toString(), f.toString(), i.getExit());
                        commite = true;
                    }
                }
                if (commite == false) {
                    g = g.add(new BigInteger(this.priceInput.getText()));
                    ConnectDB.insertSandogh(Integer.valueOf(this.monthInput.getText()), Integer.valueOf(this.yearInput.getText()), g.toString(), this.priceInput.getText(), "0");
                }
                try {
                    ConnectDB.updatePerson(this.personInDataBase.getFirstName(), this.personInDataBase.getLastName(), this.personInDataBase.getMember(), this.personInDataBase.getDept(), this.personInDataBase.getTotalMoney(), personInDataBase.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                PersonPage personPage = new PersonPage(this.stage, this.scene, personInDataBase.getFirstName(), personInDataBase.getLastName());
                personPage.display();
            }
        }

    }

    private ObservableList<GhestInDataBase> findInDataBase(int id) {
        ObservableList<GhestInDataBase> ghests = FXCollections.observableArrayList();
        try {
            ghests = ConnectDB.selectAllGhest(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ghests;
    }
}
