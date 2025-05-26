package com.TillDawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    private int hero;
    private Texture playerTexture;
    private Sprite playerSprite;
    private float posX = 1000;
    private float posY = 1000;
    private float playerHealth = 100;
    private CollisionRect rect;
    private float time = 0;
    private float speed = 5;

    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public int getHero() {
        return hero;
    }

    public void setHero(int hero) {
        this.hero = hero;
        playerTexture = GameAssetManager.getGameAssetManager().getHeroTex()[hero].get(0);
        playerSprite = new Sprite(playerTexture);
        playerSprite.setPosition(this.getPosX(), this.getPosY());

        playerSprite.setSize(playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight(), playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);

    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
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

    public float getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(float playerHealth) {
        this.playerHealth = playerHealth;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
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

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }
}
