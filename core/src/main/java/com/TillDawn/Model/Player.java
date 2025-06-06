package com.TillDawn.Model;

import com.TillDawn.Model.Enum.Hero;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Hero heroEnum;
    private int hero;
    private Texture playerTexture;
    private Sprite playerSprite;
    private float posX = 1000;
    private float posY = 1000;
    private int playerHealth;
    private int maxHealth;
    private int kills = 0;
    private CollisionRect rect;
    private float time = 0;
    private float speed;
    private int xp = 0;
    private int level = 1;
    private boolean abilityChosen = true;
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
        this.maxHealth = playerHealth;
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

    public CollisionRect getRect() {
        return rect;
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

    public int getKills() {
        return kills;
    }

    public void addKills() {
        this.kills ++;
    }

    public void reduceHealth(){
        this.playerHealth -= 1;
        setInvisibleTimeLeft(1);
    }

    public void addHealth(){
        this.playerHealth ++;
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

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getXpNeeded(){
        return 20 * this.level;
    }

    public void addXp(int xp){
        this.xp += xp;
        if(this.xp >= this.getXpNeeded()){
            this.xp -= this.getXpNeeded();
            this.level++;
            this.abilityChosen = false;
        }
    }

    public boolean isAbilityChosen() {
        return abilityChosen;
    }

    public void setAbilityChosen(boolean abilityChosen) {
        this.abilityChosen = abilityChosen;
    }

    public void addMaxHealth() {
        this.maxHealth ++;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
