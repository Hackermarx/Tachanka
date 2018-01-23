package com.tachanka.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private Scene scene;
    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);

        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());

        gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLUE);
        gc.fillRect(10, 10, 100, 100);

        root.getChildren().add(canvas);
    }

    public Scene getScene() {
        return scene;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }
}
