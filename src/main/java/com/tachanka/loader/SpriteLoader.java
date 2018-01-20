package com.tachanka.loader;

public class SpriteLoader extends FileLoader {
    @Override
    GenericFile getFile(String filePath) {
        return new Sprite(filePath);
    }
}
