package com.TillDawn.Models;

public class Game {
    private final Player player = new Player();
    private float time;

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
}
