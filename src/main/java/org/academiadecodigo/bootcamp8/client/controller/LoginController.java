package org.academiadecodigo.bootcamp8.client.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp8.client.service.ServiceRegistry;
import org.academiadecodigo.bootcamp8.client.service.connectionservice.ConnectionService;
import org.academiadecodigo.bootcamp8.client.utilities.Utilities;
import org.academiadecodigo.bootcamp8.client.view.Navigation;
import org.academiadecodigo.bootcamp8.shared.Values;

import java.net.URL;
import java.util.Optional;
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

    @FXML
    private Hyperlink close;

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

    @FXML
    void onHelp(ActionEvent event) {

    }

    @FXML
    void onLogin(ActionEvent event) {
        if (login.getText().equals("Login")) {
            if(isLoginFieldEmpty()){
                return;
            }else{
                authenticate();
            }
        }else {
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

    private void authenticate() {
        connectionService.authenticateUser(username.getText(), password.getText());
        String reply = connectionService.getReply();

        if (reply.equals(Values.LOGIN_OK)) {
            Navigation.getInstance().loadScreen(Utilities.MAIN_VIEW);
            return;
        }

        userPrompt(Alert.AlertType.INFORMATION, Utilities.LOGIN_MANAGER, Values.LOGIN_FAIL);

    }

    private Optional<ButtonType> userPrompt(Alert.AlertType type, String title, String msg){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        return alert.showAndWait();
    }

    @FXML
    void onRegister(ActionEvent event) {
        login.setText("Register");

    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void onClose(ActionEvent event) {
        Navigation.getInstance().close();
    }


}
