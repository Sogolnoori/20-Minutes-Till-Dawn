package com.TillDawn.Models;

public class Game {
    private final Player player;
    private final Weapon weapon;
    private float time;

    public Game() {
        this.player = new Player();
        this.weapon = new Weapon();
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
}
