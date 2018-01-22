package com.tachanka.loader;

public class TextureLoader extends FileLoader {
    @Override
    Texture getFile(String filePath) {
        return new Texture(filePath);
    }
}
