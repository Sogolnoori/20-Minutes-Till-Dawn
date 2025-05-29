package com.TillDawn.Models;

public class CollisionRect {
    float x, y;
    float width, height;

    public CollisionRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collidesWith(CollisionRect rect) {
        return x < rect.x + rect.width && y  < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
