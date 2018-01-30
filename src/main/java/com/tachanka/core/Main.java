package com.tachanka.core;

import com.tachanka.io.KeyBoardHandler;
import com.tachanka.io.MouseListener;
import com.tachanka.loader.SoundLoader;
import com.tachanka.loader.TextureLoader;
import javafx.application.Application;
import javafx.scene.Scene;

import java.util.Date;

/**
 * Effectively the backbone of the entire application, the main
 * deck of the Tachanka so to say.
 */
public abstract class Main {
    /**
     * An integer describing the target fps of the application
     */
    private int fps;

    /**
     * A double representing the amount of time that has passed between the
     * last and the current cycle. Can be used for calculating accurate movements
     */
    private double delta;

    /**
     * Two longs used for computing the delta time
     */
    private long t0, t1;

    /**
     * A boolean describing whether or not the application should run
     */
    private boolean isRunning;

    /**
     * The spriteloader for this application
     */
    private TextureLoader textureLoader;

    /**
     * The soundloader for this application
     */
    private SoundLoader soundLoader;

    /**
     * The scene for this application
     */
    private Scene scene;

    /**
     * The actual JavaFX application for this application
     */
    private MainApplication application;

    /**
     * The key handler for this application
     */
    private KeyBoardHandler keyBoardHandler;

    /**
     * The mouse event handler thingie doodler for this
     * application
     */
    private MouseListener mouseListener;

    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    /**
     * Default initialises the fps to 30
     */
    public Main() {
        this.fps = 30;
        init();
    }

    /**
     * Takes the fps and sets it to that given value
     * @param fps The target fps for this application
     */
    public Main(int fps) {
        this.fps = fps;
        init();
    }

    /**
     * Some standard initialisation for the application
     */
    private void init() {
        this.isRunning = true;
        this.delta = 0;
        this.soundLoader = new SoundLoader();
        this.textureLoader = new TextureLoader();

        this.application = new MainApplication();
        Application.launch("");
        this.scene = application.getScene();
        this.keyBoardHandler = new KeyBoardHandler(scene);
    }

    /**
     * This is what you call when you want to start this trainwreck
     */
    public void launch() {
        boot();

        while (isRunning) {
            try {
                Thread.sleep(1000 / fps);
                new Thread(() -> {
                    setT(0);
                    run();
                    setT(1);
                    delta = (t1 - t0) / 1000;
                }, "RunThread").start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    /**
     * A setter for the t0 and t1, combined into one cus efficiency or smth
     */
    private void setT(int t) {
        if (t == 0) {
            t0 = (new Date()).getTime();
        } else if (t == 1) {
            t1 = (new Date()).getTime();
        } else {
            throw new IllegalArgumentException("yo dumb shit");
        }
    }

    public double getDelta() {
        return delta;
    }

    public TextureLoader getTextureLoader() {
        return textureLoader;
    }

    public SoundLoader getSoundLoader() {
        return soundLoader;
    }

    public Scene getScene() {
        return scene;
    }

    public MainApplication getApplication() {
        return application;
    }

    public KeyBoardHandler getKeyBoardHandler() {
        return keyBoardHandler;
    }

    /**
     * First stage of running the application, this is where the application
     * can load images and setup basic stuff
     */
    public abstract void boot();

    /**
     * Basically what the entire application should do every frame
     */
    public abstract void run();

    /**
     * Loads the textures from the given dir into the application
     * @param dir The directory name from which to load
     */
    private void loadTextures(String dir) {
        textureLoader.load(dir);
    }

    /**
     * Loads the sounds from the given dir into the application
     * @param dir The directory name from which to load
     */
    private void loadSounds(String dir) {
        soundLoader.load(dir);
    }
}
