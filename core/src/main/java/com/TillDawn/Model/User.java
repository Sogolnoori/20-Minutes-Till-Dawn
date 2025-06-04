package com.TillDawn.Model;

import com.TillDawn.Main;

import java.util.Random;

public class User {

    private String username;
    private String password;
    private String favoriteSpell;
    private String avatar;
    private int score;
    private int kills;
    private int maxTimeAlive;

    private Game currentGame = null;

    public User(String username, String password, String favoriteSpell) {
        this.username = username;
        this.password = password;
        this.favoriteSpell = favoriteSpell;
        this.score = 0;
        this.kills = 0;
        this.maxTimeAlive = 0;
        Random rand = new Random();
        avatar = "avatars/" + "avatar" + rand.nextInt(2) + ".png";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavoriteSpell() {
        return favoriteSpell;
    }

    public void setFavoriteSpell(String favoriteSpell) {
        this.favoriteSpell = favoriteSpell;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKills() {
        return kills;
    }

    public void addKills(int kills) {
        this.kills += kills;
    }

    public int getMaxTimeAlive() {
        return maxTimeAlive;
    }

    public void updateMaxTimeAlive(int maxTimeAlive) {
        this.maxTimeAlive = Math.max(maxTimeAlive, this.maxTimeAlive);
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
