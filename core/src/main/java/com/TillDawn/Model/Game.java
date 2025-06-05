package com.TillDawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Game {
    private final Sprite backgroundSprite;
    private final float mapHeight;
    private final float mapWidth;
    private final Player player;
    private final Weapon weapon;
    private final ArrayList<Projectile> bullets;
    private final ArrayList<Projectile> monsterShots;
    private final ArrayList<Projectile> droplets;
    private final AmmoCounter ammoCounter;
    private final ArrayList<Monster> monsters;
    private final MonsterSpawner monsterSpawner;
    private final ArrayList<Heart> hearts;
    private float totalTime;
    private float timeSpent;


    public Game(float time, int hero, int weaponType) {
        this.totalTime = time;
        Texture backgroundTex = new Texture("background.png");
        this.backgroundSprite = new Sprite(backgroundTex);
        this.mapWidth = (float) backgroundTex.getWidth() / 2;
        this.mapHeight = (float) backgroundTex.getHeight() / 2;
        this.backgroundSprite.setSize(mapWidth, mapHeight);
        this.player = new Player();
        this.player.setHero(hero);
        this.weapon = new Weapon();
        this.weapon.setWeaponType(weaponType);
        this.bullets = new ArrayList<>();
        this.monsterShots = new ArrayList<>();
        this.droplets = new ArrayList<>();
        this.ammoCounter = new AmmoCounter(weapon.getWeaponEnum().getAmmo());
        this.monsters = new ArrayList<>();
        this.monsterSpawner = new MonsterSpawner(monsters, monsterShots, time);
        this.hearts = new ArrayList<>();
        this.addHearts();
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

    public float getTotalTime() {
        return totalTime;
    }

    public void updateTimeSpent(float time){
        this.timeSpent += time;
        player.reduceInvisibleTimeLeft(time);
        monsterSpawner.update(time);
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

    public ArrayList<Projectile> getBullets() {
        return bullets;
    }

    public ArrayList<Heart> getHearts() {
        return hearts;
    }

    public AmmoCounter getAmmoCounter() {
        return ammoCounter;
    }

    public ArrayList<Projectile> getMonsterShots() {
        return monsterShots;
    }

    public ArrayList<Projectile> getDroplets() {
        return droplets;
    }
}
