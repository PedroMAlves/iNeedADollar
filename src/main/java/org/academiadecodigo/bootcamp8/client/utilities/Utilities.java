package org.academiadecodigo.bootcamp8.client.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by codecadet on 13/07/2017.
 */
public class Utilities {

    public final static int SCREEN_MIN_WIDTH = 720;
    public final static int SCREEN_MIN_HEIGHT = 405;
    public final static String ICON = "/view/possible-logo.png";
    public static final String LOGIN_VIEW = "login";
    public static final String MAIN_VIEW = "main";
    public static final String INVALID_USER = "Username not valid. Must be 6 characters long only letters and numbers";
    public static final String INVALID_PASS = "Password not valid. Must be 6 characters long only letters and numbers";
    public static final String INVALID_EMAIL = "Email not valid.";

    public static final String LETTERS_NUM_REGEX = "\\w{6,}";

    public static final String EMAIL_REGEX = "\\b[A-Za-z0-9._+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
    public static final String LOGIN_MANAGER = "I Need a Dollar login";
    public static final String EMPTY_FIELDS = "Please make sure you do not have any blank fields";

    public static final String REQUEST_DOLLARS = "Request Sent";

    public static boolean isEmailValid(String email) {
        return email.matches(Utilities.EMAIL_REGEX);
    }

    public static boolean isUsernameValid(String username) {
        return username.matches(Utilities.LETTERS_NUM_REGEX);
    }

    public static boolean isPasswordValid(String password) {
        return password.matches(Utilities.LETTERS_NUM_REGEX);
    }

    public static Optional<ButtonType> userPrompt(Alert.AlertType type, String title, String msg) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        return alert.showAndWait();
    }
}
