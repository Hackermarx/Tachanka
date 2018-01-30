package com.tachanka.objects;

import java.util.ArrayList;
import java.util.Collections;

public class Hitbox {
    private ArrayList<Vector2D> polygon;
    private Vector2D center;

    public Hitbox(ArrayList<Vector2D> polygon) {
        this.polygon = polygon;
    }

    public Hitbox(Vector2D init) {
        polygon = new ArrayList<>(Collections.singletonList(init));
    }

    public void addVertex(Vector2D newVector) {
        polygon.add(newVector);
    }

    public boolean collidesWith(Hitbox hitbox) {
        setCenter();
        if (!isConvex()) {
            throw new IllegalStateException("Shape not convex; can't check collision");
        }
        // TODO: Implement Separating Axis Theorem
        return false;
    }

    private boolean isConvex() {
        if (polygon.size() < 4) {
            return true;
        } else {
            int size = polygon.size();
            for (int i = 0; i < size; i++) {
                Vector2D a = polygon.get((i + 1) % size).sub(polygon.get(i));
                Vector2D b = polygon.get((i + 2) % size).sub(polygon.get((i + 1) % size));

                if (a.crossZ(b) < 0) {
                    return false;
                }
            }
            return true;
        }
    }

    private void setCenter() {
        double nx = 0;
        for (Vector2D x : polygon) {
            nx += x.getX();
        }
        double ny = 0;
        for (Vector2D y : polygon) {
            ny += y.getY();
        }
        center = new Vector2D(nx / polygon.size(), ny / polygon.size());
    }
}
