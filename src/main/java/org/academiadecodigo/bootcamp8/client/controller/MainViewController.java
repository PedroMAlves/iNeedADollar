package org.academiadecodigo.bootcamp8.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp8.client.service.ServiceRegistry;
import org.academiadecodigo.bootcamp8.client.service.connectionservice.ConnectionService;
import org.academiadecodigo.bootcamp8.client.utilities.Utilities;
import org.academiadecodigo.bootcamp8.client.view.Navigation;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Controller {

    @FXML
    private TabPane tab;

    @FXML
    private ListView<ObservableList> whoNeedsDollar;

    @FXML
    private Tab iNeedDollar;

    @FXML
    private Label needDollarLabel;

    @FXML
    private TextArea whyDollar;

    @FXML
    private Button whyDollarButton;

    @FXML
    private Hyperlink close;

    @FXML
    private Hyperlink logout;

    @FXML
    private Label balance;

    @FXML
    private Tab transactions;

    @FXML
    private ListView<ObservableList> transactionsList;

    @FXML
    private Button pay;

    @FXML
    private Label username;
    private Stage stage;
    private double x;
    private double y;
    private ConnectionService connectionService;
    private ObservableList <Pane> whoNeedsDolars;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggable();
        connectionService = ServiceRegistry.getInstance().getService(ConnectionService.class);
        username.setText(connectionService.getLoggedUser());
        balance.setText(connectionService.getBalance() + "$");

    }

    @FXML
    void sendDollarRequest(ActionEvent event) {
        String[] insert = {username.getText(), whyDollar.getText()};
        String str = connectionService.requestDollar(insert);
        Utilities.userPrompt(Alert.AlertType.INFORMATION, Utilities.REQUEST_DOLLARS, str);
        whyDollar.setText("");
    }

    @FXML
    void updateDollarNeeds(ActionEvent event) {
    }

    @FXML
    void updateTransactions(ActionEvent event) {

    }

    @FXML
    void needsDollarPrompt(MouseEvent event) {

    }

    @FXML
    void pay(ActionEvent event) {

    }

    private void setDraggable() {

        tab.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });

        tab.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            }
        });
    }

    @FXML
    void logout(ActionEvent event) {
        connectionService.logoutUser();
        Navigation.getInstance().back();
    }

    @FXML
    void close(ActionEvent event) {
        connectionService.close();
        Navigation.getInstance().close();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}


