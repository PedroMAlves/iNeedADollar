package org.academiadecodigo.bootcamp8.client.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp8.client.service.ServiceRegistry;
import org.academiadecodigo.bootcamp8.client.service.connectionservice.ConnectionService;
import org.academiadecodigo.bootcamp8.client.utilities.Utilities;
import org.academiadecodigo.bootcamp8.client.view.Navigation;
import org.academiadecodigo.bootcamp8.shared.Values;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
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

        try {
            Desktop.getDesktop().browse(new URL("https://docs.google.com/presentation/d/12tmQHi64W8z7TprlpiD3wwZNoRpU" +
                    "3ORlAXaA9FTvuI0/edit?usp=sharing").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onLogin(ActionEvent event) {
        if (login.getText().equals("login")) {
            if (isLoginFieldEmpty()) {
                Utilities.userPrompt(Alert.AlertType.ERROR, Utilities.LOGIN_MANAGER, Utilities.EMPTY_FIELDS);
                return;
            } else {
                authenticate();

            }
        } else {
            if (isAnyFieldEmpty()) {
                Utilities.userPrompt(Alert.AlertType.ERROR, Utilities.LOGIN_MANAGER, Utilities.EMPTY_FIELDS);
                return;
            }else{
                addUser();
            }
        }
    }

    private boolean isLoginFieldEmpty() {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            return true;
        }

        return false;
    }

    private boolean isAnyFieldEmpty() {
        if (isLoginFieldEmpty() || eMail.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private void authenticate() {
        connectionService.authenticateUser(username.getText(), password.getText());

        String reply = connectionService.getReply();


        if (reply.equals(Values.LOGIN_OK)) {
            connectionService.setLoggedUser(username.getText());
            clearFields();
            Navigation.getInstance().loadScreen(Utilities.MAIN_VIEW);
            return;
        }

        Utilities.userPrompt(Alert.AlertType.INFORMATION, Utilities.LOGIN_MANAGER, reply);

    }

    private void addUser() {
        if (!Utilities.isUsernameValid(username.getText())) {
            Utilities.userPrompt(Alert.AlertType.INFORMATION, Utilities.LOGIN_MANAGER, Utilities.INVALID_USER );
            return;
        }

        if (!Utilities.isPasswordValid(password.getText())) {
            Utilities.userPrompt(Alert.AlertType.INFORMATION, Utilities.LOGIN_MANAGER, Utilities.INVALID_PASS);
            return;
        }

        if (!Utilities.isEmailValid(eMail.getText())) {
            Utilities.userPrompt(Alert.AlertType.INFORMATION, Utilities.LOGIN_MANAGER, Utilities.INVALID_EMAIL);
            return;
        }

        connectionService.registerUser(username.getText(), password.getText(), eMail.getText());

        String s = connectionService.getReply();

        if (s.equals(Values.REGISTER_OK)) {
            Utilities.userPrompt(Alert.AlertType.INFORMATION, Utilities.LOGIN_MANAGER, s);
            eMailRemove();
            login.setText("login");
            register.setText("register");
            return;
        }
        Utilities.userPrompt(Alert.AlertType.ERROR, Utilities.LOGIN_MANAGER, s);
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

    private void clearFields() {
        username.setText("");
        password.setText("");
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



    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void onClose(ActionEvent event) {
        connectionService.close();
        Navigation.getInstance().close();
    }


}
