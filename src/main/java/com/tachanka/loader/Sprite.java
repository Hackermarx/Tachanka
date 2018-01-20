package com.tachanka.loader;

import javafx.scene.image.Image;

public class Sprite extends GenericFile {
    private Image image;

    public Sprite(String filePath) {
        super(filePath);
        image = new Image(filePath);
    }
}
