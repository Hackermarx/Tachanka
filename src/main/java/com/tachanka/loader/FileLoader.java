package com.tachanka.loader;

import java.io.File;
import java.util.HashMap;

abstract class FileLoader<FileType extends GenericFile> {
    /**
     * Counts the amount of files in a given directory
     * @param parent The directory in which to count files
     * @return The amount of files found
     */
    int countFiles(File parent) {
        int ret = 0;
        String[] children = parent.list();
        File temp;
        assert children != null;
        for (String str : children) {
            temp = new File(str);
            if (temp.isDirectory()) {
                ret += countFiles(temp);
            } else {
                ret++;
            }
        }
        return ret;
    }

    HashMap<String, FileType> loadFiles(File parent) {
        HashMap<String, FileType> ret = new HashMap<>();

        String[] children = parent.list();

        File temp;
        HashMap<String, FileType> dirChild;

        assert children != null;
        for (String file : children) {
            temp = new File(file);
            if (temp.isDirectory()) {
                dirChild = loadFiles(temp);
                for (String key : dirChild.keySet()) {
                    ret.put(key, dirChild.get(key));
                }
            } else {
                ret.put(file.split("\\.")[0], getFile(file));
            }
        }

        return ret;
    }

    abstract FileType getFile(String file);
}
