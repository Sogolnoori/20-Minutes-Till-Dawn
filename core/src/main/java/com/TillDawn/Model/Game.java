package com.TillDawn.Model;

import java.util.ArrayList;

public class Game {
    private final Player player;
    private final Weapon weapon;
    private final ArrayList<Bullet> bullets;
    private final ArrayList<Monster> monsters;
    private float totalTime;
    private float timeSpent;


    public Game() {
        this.player = new Player();
        this.weapon = new Weapon();
        this.bullets = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.monsters.add(new Monster(1));
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
}
