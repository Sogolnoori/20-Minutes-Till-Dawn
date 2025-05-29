package com.TillDawn.Models;

import java.util.ArrayList;

public class Game {
    private final Player player;
    private final Weapon weapon;
    private final ArrayList<Bullet> bullets;
    private final ArrayList<Monster> monsters;
    private float time;


    public Game() {
        this.player = new Player();
        this.weapon = new Weapon();
        this.bullets = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.monsters.add(new Monster(0));
    }

    public Player getPlayer() {
        return player;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
    public void reduceTime(float time){
        this.time -= time;
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
