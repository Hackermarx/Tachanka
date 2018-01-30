package com.tachanka.io;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 * A mouselistener class for for this application. It
 * has functionality for detecting single clicks, double clicks,
 * mouse dragging and mouse coord tracking. In order to use this
 * class you extend the class and implement the onMouseReleased,
 * onSingleClick and onDoubleClick methods.
 *
 * Note that it might be useful to check on the mouseevents what
 * button was actually pressed using mouseEvent.isPrimaryButton()
 * etc.
 */
public abstract class MouseListener {
    private int mouseX;
    private int mouseY;
    private boolean isDragging;
    private long tLastPress;
    private int threshold;

    /**
     * Creates a mouse listener for the given scene
     * @param scene The scene to which to attach the mouselistener
     */
    public MouseListener(Scene scene) {
        scene.setOnMouseClicked(this::onMouseClick);
        scene.setOnMouseReleased(this::onMouseReleased);
        scene.setOnMouseMoved(this::setMouseCoord);
        scene.setOnMouseDragged(event -> {
            this.setMouseCoord(event);
            isDragging = true;
        });
        scene.setOnMouseDragReleased(event -> isDragging = false);

        threshold = 500;
        tLastPress = 0;
    }

    /**
     * Fires when a mouse button is released
     * @param mouseEvent the mouse event of releasing a button
     */
    abstract void onMouseReleased(MouseEvent mouseEvent);

    /**
     * Fires when a single click is executed
     * @param mouseEvent the mouse event of clicking a single button
     */
    abstract void onSingleClick(MouseEvent mouseEvent);

    /**
     * Fires when a double click is executed. This happens when
     * the threshold is greater than the difference between the
     * last click and the current click.
     * @param mouseEvent the mouse event of clicking a button twice
     */
    abstract void onDoubleClick(MouseEvent mouseEvent);

    /**
     * Sets the mouse coords based on the given mouseEvent
     * @param mouseEvent the mouse event from which to extract the coords
     */
    private void setMouseCoord(MouseEvent mouseEvent) {
        mouseX = (int) mouseEvent.getX();
        mouseY = (int) mouseEvent.getY();
    }

    private void onMouseClick(MouseEvent mouseEvent) {
        if (System.currentTimeMillis() - threshold < tLastPress) {
            onDoubleClick(mouseEvent);
        } else {
            onSingleClick(mouseEvent);
            tLastPress = System.currentTimeMillis();
        }
    }

    private int getMouseX() {
        return mouseX;
    }

    private int getMouseY() {
        return mouseY;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
