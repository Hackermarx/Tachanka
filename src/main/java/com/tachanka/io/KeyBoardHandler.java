package com.tachanka.io;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Map;

public class KeyBoardHandler {
    private Map<KeyCode, KeyHandler> keyMap;
    private Scene scene;

    public KeyBoardHandler(Scene scene) {
        keyMap = new HashMap<>();
        this.scene = scene;
        scene.setOnKeyPressed(this::handle);
    }

    private void handle(KeyEvent keyEvent) {
        keyMap.get(keyEvent.getCode()).handle();
    }

    public void addKeyHandler(KeyCode key, KeyHandler keyHandler) {
        keyMap.put(key, keyHandler);
    }
}
