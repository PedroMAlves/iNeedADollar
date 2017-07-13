package org.academiadecodigo.bootcamp8.client.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Controller{

    @FXML
    private VBox box;

    @FXML
    private GridPane loginGrid;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button login;

    @FXML
    private Hyperlink register;

    @FXML
    private Hyperlink help;

    private Stage stage;

    private double x;
    private double y;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggable();
    }

    private void setDraggable() {

        box.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });

        box.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
