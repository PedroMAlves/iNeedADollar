package org.academiadecodigo.bootcamp8.client.utilities;

/**
 * Created by codecadet on 13/07/2017.
 */
public class Utilities {

    public final static int SCREEN_MIN_WIDTH = 720;
    public final static int SCREEN_MIN_HEIGHT = 420;
    public static final String LOGIN_VIEW= "login";

    public static final String LETTERS_NUM_REGEX = "\\w{6,}";

    public static final String EMAIL_REGEX = "\\b[A-Za-z0-9._+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";

    public static boolean isEmailValid(String email) {
        return email.matches(Utilities.EMAIL_REGEX);
    }

    public static boolean isUsernameValid(String username) {
        return username.matches(Utilities.LETTERS_NUM_REGEX);
    }

    public static boolean isPasswordValid(String password) {
        return password.matches(Utilities.LETTERS_NUM_REGEX);
    }
}