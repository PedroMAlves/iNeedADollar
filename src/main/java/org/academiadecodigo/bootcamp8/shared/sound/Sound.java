package org.academiadecodigo.bootcamp8.shared.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

/**
 * Created by codecadet on 14/07/17.
 */
public class Sound {

    public Sound() {
    }

    public static void play(URL resource){
        //URL resource = getClass().getResource("/sounds/cashier.wav");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
