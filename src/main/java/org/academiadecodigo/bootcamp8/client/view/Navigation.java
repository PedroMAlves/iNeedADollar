package org.academiadecodigo.bootcamp8.client.view;

/**
 * Created by codecadet on 13/07/2017.
 */

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp8.client.controller.Controller;
import org.academiadecodigo.bootcamp8.client.utilities.Utilities;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class Navigation {

    public final static String VIEW_PATH = "/view/";

    private LinkedList<Scene> scenes = new LinkedList<>();
    private Map<String, Initializable> controllers = new HashMap<>();

    private Stage stage;

    private static Navigation instance;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Navigation(){}

    public static Navigation getInstance() {
        if (instance == null) {
            instance = new Navigation();
        }
        return instance;
    }

    public void loadScreen(String view) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(VIEW_PATH + view + ".fxml"));
            Parent root = fxmlLoader.load();

            ((Controller) fxmlLoader.getController()).setStage(stage);
            controllers.put(view, fxmlLoader.<Initializable>getController());

            Scene scene = new Scene(root, Utilities.SCREEN_MIN_WIDTH, Utilities.SCREEN_MIN_HEIGHT);
            scenes.push(scene);

            setScene(scene);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void back() {
        if (scenes.isEmpty()) {
            return;
        }
        scenes.pop();
        setScene(scenes.peek());
    }

    private void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public void close() {
        stage.close();
    }
}
