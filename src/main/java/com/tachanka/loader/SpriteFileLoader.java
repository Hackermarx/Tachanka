package com.tachanka.loader;

public class SpriteFileLoader extends FileLoader<Sprite> {
    @Override
    Sprite getFile(String file) {
        return new Sprite(file);
    }
}
