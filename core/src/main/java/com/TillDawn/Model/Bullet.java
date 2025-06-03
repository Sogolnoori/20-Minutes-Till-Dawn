package com.TillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private final Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private final Sprite sprite = new Sprite(texture);
    private CollisionRect rect;
    private final int damage = 5;
    private float xPos;
    private float yPos;
    private Vector2 direction;

    public Bullet(float xStart, float yStart, int xClicked, int yClicked){
        this.sprite.setSize(10 , 10);
        this.xPos = xStart;
        this.yPos = yStart;
        this.direction = new Vector2(
            Gdx.graphics.getWidth() / 2f - xClicked,
            Gdx.graphics.getHeight() / 2f - yClicked).nor();
        this.sprite.setX(xStart);
        this.sprite.setY(yStart);
        this.rect = new CollisionRect(xClicked, yClicked, sprite.getWidth(), sprite.getHeight());
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
