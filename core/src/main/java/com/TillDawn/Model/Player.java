package com.TillDawn.Model;

import com.TillDawn.Model.Enum.Hero;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    private Hero heroEnum;
    private int hero;
    private Texture playerTexture;
    private Sprite playerSprite;
    private float posX = 1000;
    private float posY = 1000;
    private int playerHealth;
    private float kills = 0;
    private CollisionRect rect;
    private float time = 0;
    private float speed;
    private int xp = 0;
    private float invisibleTimeLeft = 0;


    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public int getHero() {
        return hero;
    }

    public void setHero(int hero) {
        this.hero = hero;
        this.heroEnum = Hero.values()[hero];
        playerTexture = GameAssetManager.getGameAssetManager().getHeroTex()[hero].get(0);
        playerSprite = new Sprite(playerTexture);
        playerSprite.setPosition(this.getPosX(), this.getPosY());
        playerSprite.setSize(playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        rect = new CollisionRect(this.posX, this.posY, playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        this.speed = heroEnum.getSpeed();
        this.playerHealth = heroEnum.getHp();
        App.getCurrentGame().addHearts();
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
        this.rect.setX(this.posX);
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
        this.rect.setY(this.posY);
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
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

    public float getKills() {
        return kills;
    }

    public void addKills() {
        this.kills ++;
    }

    public Hero getHeroEnum() {
        return heroEnum;
    }

    public void reduceHealth(){
        this.playerHealth -= 1;
    }

    public float getInvisibleTimeLeft() {
        return invisibleTimeLeft;
    }

    public void reduceInvisibleTimeLeft(float time){
        this.invisibleTimeLeft -= time;
        if(this.invisibleTimeLeft <= 0){
            this.invisibleTimeLeft = 0;
        }
    }

    public void setInvisibleTimeLeft(float invisibleTimeLeft) {
        this.invisibleTimeLeft = invisibleTimeLeft;
    }
}
