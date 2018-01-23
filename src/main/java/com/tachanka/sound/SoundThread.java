package com.tachanka.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundThread extends Thread {
    public SoundThread(String name) {
        super(() -> {

        }, name);
    }

    public void play(Media media) {
        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }

    private class Listener {
        private void play(Media media) {

        }
    }
}
