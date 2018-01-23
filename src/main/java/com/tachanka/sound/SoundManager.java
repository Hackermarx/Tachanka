package com.tachanka.sound;

import javafx.scene.media.Media;

import java.util.ArrayList;

public class SoundManager {
    private ArrayList<SoundThread> threadList;

    public SoundManager() {
        threadList = new ArrayList<>();
    }

    public void addSoundThread(SoundThread newThread) {
        new Thread(() -> {

        });
    }

    public void addMediaToThread(Media media, String thread) {
        for (SoundThread soundThread : threadList) {
            if (soundThread.getName().equals(thread)) {
                soundThread.play(media);
                soundThread.start();
            }
        }
    }
}
