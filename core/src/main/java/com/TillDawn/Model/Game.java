package com.TillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private final Texture backgroundTex;
    private final Sprite backgroundSprite;
    private final float mapHeight;
    private final float mapWidth;
    private final Player player;
    private final Weapon weapon;
    private final ArrayList<Bullet> bullets;
    private final ArrayList<Monster> monsters;
    private final ArrayList<Heart> hearts;
    private float totalTime;
    private float timeSpent;


    public Game() {
        this.backgroundTex = new Texture("background.png");
        this.backgroundSprite = new Sprite(backgroundTex);
        this.mapWidth = (float) backgroundTex.getWidth() / 2;
        this.mapHeight = (float) backgroundTex.getHeight() / 2;
        this.backgroundSprite.setSize(mapWidth, mapHeight);
        this.player = new Player();
        this.weapon = new Weapon();
        this.bullets = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.hearts = new ArrayList<>();
    }

    public Sprite getBackgroundSprite() {
        return backgroundSprite;
    }

    public float getMapHeight() {
        return mapHeight;
    }

    public float getMapWidth() {
        return mapWidth;
    }

    public void addHearts(){
        for (int i = 0; i < player.getPlayerHealth(); i ++){
            hearts.add(new Heart(i));
        }
    }

    public Player getPlayer() {
        return player;
    }

    public float getLeftTime() {
        return totalTime - timeSpent;
    }

    public void setTime(float time) {
        this.totalTime = time;
    }
    public void updateTimeSpent(float time){
        this.timeSpent += time;
        player.reduceInvisibleTimeLeft(time);
    }

    public float getTimeSpent() {
        return timeSpent;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Heart> getHearts() {
        return hearts;
    }
}
