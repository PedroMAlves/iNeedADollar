package org.academiadecodigo.bootcamp8.client.view;

/**
 * Created by codecadet on 13/07/2017.
 */

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class Navigation {

    public final static int MIN_WIDTH = 470;
    public final static int MIN_HEIGHT = 300;

    public final static String VIEW_PATH = "/view/";

    private LinkedList<Scene> scenes = new LinkedList<>();
    private Map<String, Initializable> controllers = new HashMap<>();

    private Stage stage;

    private static Navigation instance;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Initializable getController(String view) {
        return controllers.get(view);
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
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource(VIEW_PATH + view + ".fxml"));
            Parent root = fxmlLoader.load();

            controllers.put(view, fxmlLoader.<Initializable>getController());

            Scene scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT);
            scenes.push(scene);

            setScene(scene);

        } catch (IOException e) {
            System.out.println(e.getMessage());
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
