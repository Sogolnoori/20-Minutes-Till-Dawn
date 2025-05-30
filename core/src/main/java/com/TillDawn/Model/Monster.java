package com.TillDawn.Model;

import com.TillDawn.Model.Enum.MonsterEnum;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Monster {
    private float speed;
    private final int monster;
    private final MonsterEnum monsterEnum;
    private Texture monsterTexture;
    private Sprite monsterSprite;
    private float posX;
    private float posY;
    private int monsterHealth;
    private CollisionRect rect;
    private float time = 0;

    private boolean isDying = false;
    private float deathTime = 0;

    public Monster(int monster, float posX, float posY) {
        this.monster = monster;
        this.posX = posX;
        this.posY = posY;
        this.monsterEnum = MonsterEnum.values()[monster];
        this.speed = monsterEnum.getSpeed();
        this.monsterHealth = monsterEnum.getHp();
        this.monsterTexture = GameAssetManager.getGameAssetManager().getMonsterTex()[this.monster].get(0);
        this.monsterSprite = new Sprite(monsterTexture);
        this.monsterSprite.setPosition(this.getPosX(), this.getPosY());
        monsterSprite.setSize(monsterTexture.getWidth() * 2, monsterTexture.getHeight() * 2);
        rect = new CollisionRect(
            this.posX,
            this.posY,
            monsterSprite.getWidth(),
            monsterSprite.getHeight());
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void reduceHealth(int amount) {
        this.monsterHealth -= amount;
    }

    public MonsterEnum getMonsterEnum() {
        return monsterEnum;
    }

    public boolean isDying() {
        return isDying;
    }

    public void setDying(boolean dying) {
        this.isDying = dying;
    }

    public float getDeathTime() {
        return deathTime;
    }

    public void setDeathTime(float deathTime) {
        this.deathTime = deathTime;
    }

    public float getMidX(){
        return posX + monsterSprite.getWidth() / 2;
    }

    public float getMidY(){
        return posY + monsterSprite.getHeight() / 2;
    }
}
