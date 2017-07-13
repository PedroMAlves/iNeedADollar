package org.academiadecodigo.bootcamp8.client;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.academiadecodigo.bootcamp8.client.controller.LoginController;
import org.academiadecodigo.bootcamp8.client.utilities.Utilities;
import org.academiadecodigo.bootcamp8.client.view.Navigation;

/**
 * Created by Prashanta on 13/07/17.
 */

public class INeedADollar extends Application {

    @Override
    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        
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