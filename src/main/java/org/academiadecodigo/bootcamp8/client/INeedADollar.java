package org.academiadecodigo.bootcamp8.client;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.academiadecodigo.bootcamp8.client.service.connectionservice.ConnectionService;
import org.academiadecodigo.bootcamp8.client.service.connectionservice.ConnectionServiceImpl;
import org.academiadecodigo.bootcamp8.client.service.loginservice.LoginService;
import org.academiadecodigo.bootcamp8.client.service.loginservice.LoginServiceImpl;
import org.academiadecodigo.bootcamp8.client.service.ServiceRegistry;
import org.academiadecodigo.bootcamp8.client.service.sessionservice.SessionService;
import org.academiadecodigo.bootcamp8.client.service.sessionservice.SessionServiceImpl;
import org.academiadecodigo.bootcamp8.client.utilities.Utilities;
import org.academiadecodigo.bootcamp8.client.view.Navigation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Prashanta on 13/07/17.
 */

public class INeedADollar extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{

        ConnectionService connectionService = new ConnectionServiceImpl();
        SessionService sessionService = new SessionServiceImpl();
        LoginService login = new LoginServiceImpl();

        ServiceRegistry.getInstance().addService(connectionService.getName(), connectionService);
        ServiceRegistry.getInstance().addService(sessionService.getName(), sessionService);
        ServiceRegistry.getInstance().addService(login.getName(), login);


        Navigation navigation = Navigation.getInstance();
        navigation.setStage(primaryStage);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        navigation.loadScreen(Utilities.LOGIN_VIEW);
        primaryStage.setTitle("I Need a Dollar");
        primaryStage.show();
    }


}