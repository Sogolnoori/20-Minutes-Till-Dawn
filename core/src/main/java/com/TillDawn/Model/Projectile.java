package com.TillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private final Sprite sprite;
    private final CollisionRect rect;
    private float xPos;
    private float yPos;
    private final Vector2 direction;

    public Projectile(float xStart, float yStart, Vector2 direction, Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(10 , 10);
        this.xPos = xStart;
        this.yPos = yStart;
        this.direction = direction;
        this.sprite.setX(xStart);
        this.sprite.setY(yStart);
        this.rect = new CollisionRect(xStart, yStart, sprite.getWidth(), sprite.getHeight());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public void setXPos(float xPos) {
        this.xPos = xPos;
        this.rect.setX(xPos);
    }

    public void setYPos(float yPos) {
        this.yPos = yPos;
        this.rect.setY(yPos);
    }
}
