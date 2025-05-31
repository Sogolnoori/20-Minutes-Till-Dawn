package com.TillDawn.Model;

import com.TillDawn.Model.Enum.MonsterEnum;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Monster {
    private static final float speed = 1f;
    private final int monster;
    private final MonsterEnum monsterEnum;
    private Texture monsterTexture;
    private Sprite monsterSprite;
    private float posX;
    private float posY;
    private int monsterHealth;
    private CollisionRect rect;
    private float time = 0;

    public Monster(int monster, float posX, float posY) {
        this.monster = monster;
        this.posX = posX;
        this.posY = posY;
        this.monsterEnum = MonsterEnum.values()[monster];
        this.monsterHealth = monsterEnum.getHp();
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
        this.rect.x = this.posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
        this.rect.y = this.posY;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    public void setMonsterHealth(int monsterHealth) {
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

    public void reduceHealth() {
        this.monsterHealth -= 1;
    }

    public MonsterEnum getMonsterEnum() {
        return monsterEnum;
    }
}
