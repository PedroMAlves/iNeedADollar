package org.academiadecodigo.bootcamp8.client.controller;

/**
 * Created by Prashanta on 13/07/17.
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Controller{

    @FXML
    private ListView<?> listedActive;

    @FXML
    private Label pay;

    @FXML
    private ListView<?> listedHistory;

    @FXML
    private Label receive;

    @FXML
    private Label balance;

    @FXML
    void pay(MouseEvent event) {

    }

    @FXML
    void receive(MouseEvent event) {

    }

    @Override
    public void setStage(Stage stage) {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

