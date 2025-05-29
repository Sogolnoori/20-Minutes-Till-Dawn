package com.TillDawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {
    private final Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private final Sprite sprite = new Sprite(texture);
    private CollisionRect rect;
    private final int damage = 5;
    private int x;
    private int y;

    public Bullet(float xStart, float yStart, int x, int y){
        this.sprite.setSize(20 , 20);
        this.x = x;
        this.y = y;
        this.sprite.setX(xStart);
        this.sprite.setY(yStart);
        this.rect = new CollisionRect(x, y, texture.getWidth() * 3, texture.getHeight() * 3);
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getDamage() {
        return damage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CollisionRect getRect() {
        return rect;
    }
}
