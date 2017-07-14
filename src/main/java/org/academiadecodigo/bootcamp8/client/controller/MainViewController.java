package org.academiadecodigo.bootcamp8.client.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp8.client.service.ServiceRegistry;
import org.academiadecodigo.bootcamp8.client.service.connectionservice.ConnectionService;
import org.academiadecodigo.bootcamp8.client.view.Navigation;
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
    private Stage stage;
    private double x;
    private double y;
    private ConnectionService connectionService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggable();
        connectionService = ServiceRegistry.getInstance().getService(ConnectionService.class);

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
    void close(ActionEvent event) {

        connectionService.close();
        Navigation.getInstance().close();

    }

    @FXML
    void logout(ActionEvent event) {
        connectionService.logoutUser();
        Navigation.getInstance().back();

    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}


