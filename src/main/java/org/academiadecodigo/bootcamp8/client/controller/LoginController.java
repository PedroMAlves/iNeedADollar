package org.academiadecodigo.bootcamp8.client.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp8.client.view.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Controller {

    private TextField eMail;

    @FXML
    private VBox box;

    @FXML
    private GridPane loginGrid;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Hyperlink register;

    @FXML
    private Hyperlink help;

    @FXML
    private Hyperlink close;

    @FXML
    void onClose(ActionEvent event) {
        Navigation.getInstance().close();
    }

    @FXML
    void onHelp(ActionEvent event) {

    }

    @FXML
    void onLogin(ActionEvent event) {
        if (login.getText().equals("Login")) {
            if (isLoginFieldEmpty()) {
                return;
            } else {
                //authenticate();
            }
        } else {
            /*if (isAnyFieldEmpty()) {
                return;
            }else{
                addUser();
            }*/
        }
    }

    private boolean isLoginFieldEmpty() {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            return true;
        }

        return false;
    }

    @FXML
    void onRegister(ActionEvent event) {
        if (login.getText().equals("login")) {
            eMailCreate();
            login.setText("register");
            register.setText("login");
            return;
        }
        login.getText().equals("register");
        login.setText("login");
        register.setText("register");
        eMailRemove();
    }

    private void eMailRemove() {
        loginGrid.getChildren().remove(eMail);
        loginGrid.getRowConstraints().get(2).setMinHeight(20);
    }

    private void eMailCreate() {
        eMail = new TextField();
        eMail.setMaxWidth(300);
        eMail.setMaxHeight(16);
        eMail.setPromptText("E-Mail_");
        eMail.setStyle("-fx-border-style: solid");
        loginGrid.addRow(2, eMail);
        loginGrid.getRowConstraints().get(2).setMinHeight(40);
    }

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
