package com.TillDawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.Arrays;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private Skin skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

    private final ArrayList<String> heroes = new ArrayList<>(Arrays.asList("SHANA", "DIAMOND", "SCARLET", "LILITH", "DASHER"));
    private final ArrayList<String>[] heroIdles = new ArrayList[heroes.size()];
    private final ArrayList<Texture>[] heroTex = new ArrayList[heroes.size()];
    private final ArrayList<Animation<Texture>> heroAnimations = new ArrayList<>();

    {
        for (int i = 0; i < heroes.size(); i ++){
            heroIdles[i] = new ArrayList<>();
            heroTex[i] = new ArrayList<>();
            for (int j = 0; j <= 5; j ++){
                heroIdles[i].add("Heroes/" + heroes.get(i) + "/" + "idle/Idle_" + j + ".png");
                heroTex[i].add(new Texture(heroIdles[i].get(j)));
            }
            heroAnimations.add(new Animation<Texture>(0.1f, heroTex[i].toArray(new Texture[0])));
        }
    }

    private final ArrayList<String> monsters = new ArrayList<>(Arrays.asList("Tentacle", "EyeBat", "Elder"));
    private final ArrayList<String>[] monsterIdles = new ArrayList[heroes.size()];
    private final ArrayList<Texture>[] monsterTex = new ArrayList[heroes.size()];
    private final ArrayList<Animation<Texture>> monsterAnimations = new ArrayList<>();

    {
        for (int i = 0; i < 2; i ++){
            monsterIdles[i] = new ArrayList<>();
            monsterTex[i] = new ArrayList<>();
            for (int j = 0; j <= 3; j ++){
                monsterIdles[i].add("Monsters/" + monsters.get(i) + "/" +  monsters.get(i) + "_" + j + ".png");
                monsterTex[i].add(new Texture(monsterIdles[i].get(j)));
            }
            monsterAnimations.add(new Animation<Texture>(0.1f, monsterTex[i].toArray(new Texture[0])));
        }
    }

    private final ArrayList<String> weapons = new ArrayList<>(Arrays.asList("REVOLVER", "SHOTGUN", "SMG"));
    private final ArrayList<String> weaponImg = new ArrayList<>();
    private final ArrayList<Texture> weaponTex = new ArrayList<>();

    {
        for (int i = 0; i < weapons.size(); i ++){
            weaponImg.add("Weapons/" + weapons.get(i) + "/" + weapons.get(i) + ".png");
            weaponTex.add(new Texture(weaponImg.get(i)));
        }
    }

    private final String bullet = "bullet.png";

    private final int musicNumber = 1;
    private final ArrayList<Music> musics = new ArrayList<>();

    {
        for (int i = 0; i < musicNumber; i  ++){
            Music music = Gdx.audio.newMusic(Gdx.files.internal("music/" + "music" + i + ".mp3"));
            music.setLooping(true);
            music.setVolume(1f);
            musics.add(music);
        }
    }

    public static GameAssetManager getGameAssetManager() {
        if(gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public  ArrayList<String> getHeroes() {
        return heroes;
    }

    public ArrayList<String>[] getHeroIdles() {
        return heroIdles;
    }

    public ArrayList<Texture>[] getHeroTex() {
        return heroTex;
    }

    public ArrayList<Animation<Texture>> getHeroAnimations() {
        return heroAnimations;
    }

    public ArrayList<String> getWeapons() {
        return weapons;
    }

    public ArrayList<String> getWeaponImg() {
        return weaponImg;
    }

    public ArrayList<Texture> getWeaponTex() {
        return weaponTex;
    }

    public String getBullet(){
        return bullet;
    }

    public int getMusicNumber() {
        return musicNumber;
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }

    public ArrayList<String> getMonsters() {
        return monsters;
    }

    public ArrayList<String>[] getMonsterIdles() {
        return monsterIdles;
    }

    public ArrayList<Texture>[] getMonsterTex() {
        return monsterTex;
    }

    public ArrayList<Animation<Texture>> getMonsterAnimations() {
        return monsterAnimations;
    }
}
