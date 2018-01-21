package com.tachanka.loader;

import java.io.File;
import java.util.HashMap;

abstract class FileLoader<GenericFile> {
    private int nFiles;
    private int nLoaded;
    private HashMap<String, GenericFile> map;

    public FileLoader() {
        nFiles = 0;
        nLoaded = 0;
    }

    public void load(String dir) {
        File parent = new File(dir);
        nFiles = countFiles(parent);

        HashMap<String, GenericFile> map = new HashMap<>();
        new Thread(() -> {
            setMap(loadFiles(parent));
            int x = 5;
        }).start();

    }

    private int countFiles(File parent) {
        int ret = 0;

        String[] children = parent.list();
        File temp;

        for (String filePath : children) {
            temp = new File(filePath);
            if (temp.isDirectory()) {
                ret += countFiles(temp);
            } else {
                ret++;
            }
        }

        return ret;
    }

    private HashMap<String, GenericFile> loadFiles(File parent) {
        HashMap<String, GenericFile> ret = new HashMap<>();

        String[] children = parent.list();

        File temp;
        for (String filePath : children) {
            temp = new File(parent.getPath() + "\\" + filePath);
            if (temp.isDirectory()) {
                ret.putAll(loadFiles(temp));
            } else {
                ret.put(parent.getPath() + "\\" + filePath, getFile(parent.getPath() + "\\" + filePath));
                nLoaded++;
            }
        }

        return ret;
    }

    public double getProgress() {
        return (double) nLoaded / nFiles;
    }

    abstract GenericFile getFile(String filePath);

    public void setMap(HashMap<String,GenericFile> map) {
        this.map = map;
    }
}
