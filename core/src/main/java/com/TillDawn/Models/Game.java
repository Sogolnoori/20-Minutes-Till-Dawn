package com.TillDawn.Models;

import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;

public class Game {
    private final Player player;
    private final Weapon weapon;
    private final ArrayList<Monster> monsters;
    private float time;


    public Game() {
        this.player = new Player();
        this.weapon = new Weapon();
        this.monsters = new ArrayList<>();
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
