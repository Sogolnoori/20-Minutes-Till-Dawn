package com.TillDawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Monster {
    private static final float speed = 1f;
    private final int monster;
    private Texture monsterTexture;
    private Sprite monsterSprite;
    private float posX = 0;
    private float posY = 0;
    private float monsterHealth = 100;
    private CollisionRect rect;
    private float time = 0;

    public Monster(int monster) {
        this.monster = monster;
        this.monsterTexture = GameAssetManager.getGameAssetManager().getMonsterTex()[this.monster].get(0);
        this.monsterSprite = new Sprite(monsterTexture);
        this.monsterSprite.setPosition(this.getPosX(), this.getPosY());
        monsterSprite.setSize(monsterTexture.getWidth() * 2, monsterTexture.getHeight() * 2);
        rect = new CollisionRect(
            this.posX,
            this.posY,
            monsterTexture.getWidth() * 2,
            monsterTexture.getHeight() * 2);

    }

    public Texture getMonsterTexture() {
        return monsterTexture;
    }

    public void setMonsterTexture(Texture monsterTexture) {
        this.monsterTexture = monsterTexture;
    }

    public Sprite getMonsterSprite() {
        return monsterSprite;
    }

    public void setMonsterSprite(Sprite monsterSprite) {
        this.monsterSprite = monsterSprite;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getMonsterHealth() {
        return monsterHealth;
    }

    public void setMonsterHealth(float monsterHealth) {
        this.monsterHealth = monsterHealth;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }

    public int getMonster() {
        return monster;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public static float getSpeed() {
        return speed;
    }


}
