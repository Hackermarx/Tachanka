package com.tachanka.loader;

abstract class GenericFile {
    protected String filePath;

    public GenericFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
