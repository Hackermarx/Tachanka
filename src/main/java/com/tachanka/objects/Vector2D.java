package com.tachanka.objects;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void rotate(double angle) {
        double x1 = x;
        double y1 = y;
        x = Math.cos(angle) * x1 - Math.sin(angle) * y1;
        y = Math.sin(angle) * x1 + Math.cos(angle) * y1;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void move(Vector2D d) {
        move(d.x, d.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
