package org.academiadecodigo.bootcamp8.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp8.client.service.ServiceRegistry;
import org.academiadecodigo.bootcamp8.client.service.connectionservice.ConnectionService;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Controller {

    @FXML
    private TabPane tab;

    @FXML
    private Tab whoNeedsDollar;

    @FXML
    private ListView<?> historyList;

    @FXML
    private Tab iNeedDollar;

    @FXML
    private Label needDollarLabel;

    @FXML
    private TextArea whyDollar;

    @FXML
    private Tab transactions;

    @FXML
    private ListView<?> historyList2;

    @FXML
    private Hyperlink close;

    @FXML
    private Hyperlink logout;

    @FXML
    private Label balance;

    @FXML
    private Label username;
    private ConnectionService connectionService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectionService = ServiceRegistry.getInstance().getService(ConnectionService.class);

    }
    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @Override
    public void setStage(Stage stage) {

    }

}


