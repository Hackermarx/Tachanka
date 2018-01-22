package com.tachanka.loader;

public class SoundLoader extends FileLoader {
    @Override
    Sound getFile(String filePath) {
        return new Sound(filePath);
    }
}
