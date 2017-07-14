package org.academiadecodigo.bootcamp8.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp8.client.service.ServiceRegistry;
import org.academiadecodigo.bootcamp8.client.service.connectionservice.ConnectionService;
import org.academiadecodigo.bootcamp8.client.utilities.Utilities;
import org.academiadecodigo.bootcamp8.client.view.Navigation;
import javafx.scene.control.Button;
import org.academiadecodigo.bootcamp8.shared.message.DualContainer;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Controller {



    @FXML
    private Tab whoNeedsDollarTab;

    @FXML
    private TabPane tab;

    @FXML
    private ListView<VBox> whoNeedsDollarList;

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
    private Pane pane;

    @FXML
    private Pane tab0Underline;

    @FXML
    private Pane tab1Underline;

    @FXML
    private Pane tab2Underline;

    @FXML
    private Label transactionAction;

    @FXML
    private Label transactionUsername;

    @FXML
    private Label username;

    @FXML
    private Label username1;

    @FXML
    private Label username2;

    @FXML
    private Label username11;

    @FXML
    private Label why;

    @FXML
    void pay(ActionEvent event) {

    }

    @FXML
    private AnchorPane anchorPane;

    private Stage stage;
    private double x;
    private double y;
    private ConnectionService connectionService;
    private List<DualContainer> list;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setDraggable();
        whoNeedsDollarList = new ListView<>();
        connectionService = ServiceRegistry.getInstance().getService(ConnectionService.class);
        username.setText(connectionService.getLoggedUser());
        balance.setText(connectionService.getBalance() + "$");
        setList();

    }

    public void setList() {
        list = connectionService.getRequestList();

        ObservableList<VBox> ob = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {

            VBox pane = new VBox();
            pane.setMinWidth(720);
//            pane.setStyle("-fx-min-width: 720px; -fx-min-height: 55px; ");
            pane.setMinHeight(55);
            Label username2 = new Label();
            username2.setText(list.get(i).getName());
            Label why = new Label();
            Button button = new Button();
            button.setText("Donate");

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ob.remove(pane);
                }
            });

            HBox hbox = new HBox();
            hbox.setSpacing(100);
            hbox.getChildren().addAll(why, button);
            why.setText(list.get(i).getRequest());

            pane.getChildren().addAll(username2, hbox);


            username2.setVisible(true);
            why.setVisible(true);

            ob.add(pane);

        }

        whoNeedsDollarList.setItems(ob);
        whoNeedsDollarList.setVisible(true);

        anchorPane.getChildren().addAll(whoNeedsDollarList);
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

    public void tab0(Event event) {
        try {
        tab0Underline.setVisible(true);
        tab1Underline.setVisible(false);
        tab2Underline.setVisible(false);
        } catch (Exception e) {}
    }

    public void tab1(Event event) {
        tab0Underline.setVisible(false);
        tab1Underline.setVisible(true);
        tab2Underline.setVisible(false);
    }

    public void tab2(Event event) {
        tab0Underline.setVisible(false);
        tab1Underline.setVisible(false);
        tab2Underline.setVisible(true);
    }
}


