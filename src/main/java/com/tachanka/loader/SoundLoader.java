package com.tachanka.loader;

public class SoundLoader extends FileLoader {
    @Override
    GenericFile getFile(String filePath) {
        return new Sound(filePath);
    }
}
