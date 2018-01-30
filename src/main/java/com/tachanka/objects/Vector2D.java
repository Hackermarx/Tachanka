package com.tachanka.objects;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D sub(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public double crossZ(Vector2D other) {
        return this.x * other.y - this.y * other.x;
    }

    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    public Vector2D projectTo(Vector2D other) {
        return other.scale(this.dot(other.normalize()));
    }

    private Vector2D scale(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public Vector2D normalize() {
        return scale(1 / getLength());
    }

    public double getLength() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
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
