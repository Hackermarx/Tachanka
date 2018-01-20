package com.tachanka.loader;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

public class SpriteLoader {
    /**
     * A double between 0 and 1 keeping track of how far done the spriteloader is
     */
    private static Double progress;

    /**
     * An integer giving the amount of sprites to be loaded in the given directory
     */
    private static Integer nFiles;

    /**
     * The file loader
     */
    private static SpriteFileLoader spriteFileLoader;
    private static HashMap<String, Sprite> spriteMap;

    /**
     * A method for loading all the sprites from a given directory into a hashmap.
     * They keys of the hashmap will be the paths to the relevant image in the
     * given directory, without the file extension.
     *
     * @param directory The parent directory of all your sprites (please, only sprites)
     * @return A hashmap containing all the images
     */
    public static HashMap<String, Sprite> loadSprites(String directory) {
        progress = 0.0;
        nFiles = 0;
        spriteFileLoader = new SpriteFileLoader();

        File parent = new File(directory);

        new Thread(() -> {
            nFiles = spriteFileLoader.countFiles(parent);

            setSpriteMap(spriteFileLoader.loadFiles(parent));
        }, "spriteLoading").start();

        return spriteMap;
    }

    private static void setSpriteMap(HashMap<String, Sprite> spriteMap) {
        SpriteLoader.spriteMap = spriteMap;
    }

    public static Double getProgress() {
        return progress;
    }

    public static Integer getnFiles() {
        return nFiles;
    }
}
