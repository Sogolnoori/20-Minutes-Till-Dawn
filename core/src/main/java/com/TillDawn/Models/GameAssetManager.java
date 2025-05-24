package com.TillDawn.Models;

import com.badlogic.gdx.Gdx;
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

    private final String smg = "smg/SMGStill.png";
    private final Texture smgTexture = new Texture(smg);

    private final String bullet = "bullet.png";


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

    public Texture getSmgTexture(){
        return smgTexture;
    }

    public String getSmg() {
        return smg;
    }

    public String getBullet(){
        return bullet;
    }

}
