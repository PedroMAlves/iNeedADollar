package org.academiadecodigo.bootcamp8.client;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.academiadecodigo.bootcamp8.client.service.connectionservice.ConnectionService;
import org.academiadecodigo.bootcamp8.client.service.connectionservice.ConnectionServiceImpl;
import org.academiadecodigo.bootcamp8.client.service.ServiceRegistry;
import org.academiadecodigo.bootcamp8.client.utilities.Utilities;
import org.academiadecodigo.bootcamp8.client.view.Navigation;
import org.academiadecodigo.bootcamp8.shared.sound.Sound;
import javax.swing.*;

/**
 * Created by Prashanta on 13/07/17.
 */

public class INeedADollar extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Sound.play(getClass().getResource("/sounds/NeedDollar.wav"));

        ConnectionService connectionService = new ConnectionServiceImpl();

        ServiceRegistry.getInstance().addService(connectionService.getName(), connectionService);


        Navigation navigation = Navigation.getInstance();
        navigation.setStage(primaryStage);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        navigation.loadScreen(Utilities.LOGIN_VIEW);
        primaryStage.setTitle("I Need a Dollar");
        primaryStage.show();

        setIcon();
    }

    private void setIcon() {
        try {
            com.apple.eawt.Application.getApplication().setDockIconImage(new ImageIcon(getClass().getResource(Utilities.ICON)).getImage());
        } catch (Exception e) {
        }
    }
}