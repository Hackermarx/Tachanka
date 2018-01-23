package com.tachanka.objects;

import java.util.ArrayList;
import java.util.Collections;

public class Hitbox {
    private ArrayList<Vector2D> polygon;

    public Hitbox(ArrayList<Vector2D> polygon) {
        this.polygon = polygon;
    }

    public Hitbox(Vector2D init) {
        polygon = new ArrayList<>(Collections.singletonList(init));
    }

    public void addVertex(Vector2D newVector) {
        polygon.add(newVector);
    }

    public boolean isConvex() {
        if (polygon.size() < 4) {
            return true;
        } else {
            // TODO: Implement giftwrapping algorithm
            return false;
        }
    }

    public boolean collidesWith(Hitbox hitbox) {
        // TODO: Implement Seperating Axis Theorem
        return false;
    }
}
