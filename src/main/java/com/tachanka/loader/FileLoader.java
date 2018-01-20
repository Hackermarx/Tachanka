package com.tachanka.loader;

import java.io.File;
import java.util.HashMap;

abstract class FileLoader<GenericFile> {
    private int nFiles;
    private int nLoaded;

    public FileLoader() {
        nFiles = 0;
        nLoaded = 0;
    }

    public HashMap<String, GenericFile> load(String dir) {
        File parent = new File(dir);
        nFiles = countFiles(parent);
        HashMap<String, GenericFile> map = loadFiles(parent);
        return map;
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
            temp = new File(filePath);
            if (temp.isDirectory()) {
                ret.putAll(loadFiles(temp));
            } else {
                ret.put(filePath, getFile(filePath));
                nLoaded++;
            }
        }

        return ret;
    }

    public double getProgress() {
        return (double) nLoaded / nFiles;
    }

    abstract GenericFile getFile(String filePath);
}
