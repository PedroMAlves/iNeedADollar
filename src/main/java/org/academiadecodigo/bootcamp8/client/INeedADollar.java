package org.academiadecodigo.bootcamp8.client;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.academiadecodigo.bootcamp8.client.service.LoginService;
import org.academiadecodigo.bootcamp8.client.service.LoginServiceImpl;
import org.academiadecodigo.bootcamp8.client.service.ServiceRegistry;
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
    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;




    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            socket = new Socket("127.0.0.1", 4040);
            System.out.println("connected");
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
             System.out.println("connected to server, opened sockets");

        } catch (IOException e) {
            System.err.println("Unable to connect to server");
        }

        objectOutputStream.writeObject(new String("sdghsgkjhfsg"));


        LoginService login = new LoginServiceImpl();
        ServiceRegistry.getInstance().addService(login.getName(), login);

        Navigation navigation = Navigation.getInstance();
        navigation.setStage(primaryStage);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        navigation.loadScreen(Utilities.LOGIN_VIEW);
        primaryStage.setTitle("I Need a Dollar");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}