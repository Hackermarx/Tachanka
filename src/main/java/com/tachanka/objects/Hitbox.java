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

    public double collidesWith(Hitbox other) {
        setCenter();
        if (!isConvex()) {
            throw new IllegalStateException("Shape not convex; can't check collision");
        }

        ArrayList<Vector2D> normals = this.getNormals();
        normals.addAll(other.getNormals());

        for (Vector2D p : normals) {
            // then we find the min and max lengths of the projections onto p
            double min0 = this.getMinProjectionTo(p);
            double max0 = this.getMaxProjectionTo(p);
            double min1 = other.getMinProjectionTo(p);
            double max1 = other.getMaxProjectionTo(p);

            if (max1 < min0) return max0 - min1;
            if (max0 < min1) return max1 - min0;
        }
        return -1;
    }

    private double getMaxProjectionTo(Vector2D p) {
        double ret = Double.MIN_VALUE;
        double projection;
        for (int i = 0; i < polygon.size(); i++) {
            Vector2D cur = polygon.get((i + 1) % polygon.size()).sub(polygon.get(i)).normalize();
            projection = cur.dot(p);
            ret = Math.max(projection, ret);
        }
        return ret;
    }

    private double getMinProjectionTo(Vector2D p) {
        double ret = Double.MAX_VALUE;
        double projection;
        for (int i = 0; i < polygon.size(); i++) {
            Vector2D cur = polygon.get((i + 1) % polygon.size()).sub(polygon.get(i)).normalize();
            projection = cur.dot(p);
            ret = Math.min(projection, ret);
        }
        return ret;
    }

    private ArrayList<Vector2D> getNormals() {
        ArrayList<Vector2D> ret = new ArrayList<>(polygon.size());
        for (int i = 0; i < polygon.size(); i++) {
            ret.add(polygon.get((i + 1) % polygon.size()).sub(polygon.get(i)).leftNormal());
        }
        return ret;
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
