package com.tachanka.objects;

import com.tachanka.loader.Texture;

public class Sprite {
    private Texture texture;
    private Hitbox hitbox;
    private Vector2D pos;
    private Vector2D offset;
    private Vector2D orient;

    public Sprite(Texture texture, Vector2D pos) {
        this.texture = texture;
        this.pos = pos;
        this.orient = new Vector2D(1, 0);
        this.offset = new Vector2D(0, 0);
        this.hitbox = null;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public void rotate(double angle) {
        orient.rotate(angle);
    }

    public Vector2D getPos() {
        return pos;
    }

    public Vector2D getOffset() {
        return offset;
    }

    public boolean collidesWith(Sprite other) {
        return this.hitbox != null && other.hitbox != null && this.hitbox.collidesWith(other.hitbox) >= 0;
    }
}
