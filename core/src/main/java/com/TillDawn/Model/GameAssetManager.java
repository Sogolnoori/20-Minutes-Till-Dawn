package com.TillDawn.Model;

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

    private final ArrayList<String> monsters = new ArrayList<>(Arrays.asList("TreeMonster", "Tentacle", "EyeBat", "Boss"));
    private final ArrayList<String>[] monsterIdles = new ArrayList[monsters.size()];
    private final ArrayList<Texture>[] monsterTex = new ArrayList[monsters.size()];
    private final ArrayList<Animation<Texture>> monsterAnimations = new ArrayList<>();
    {
        for (int i = 0; i < 4; i ++){
            monsterIdles[i] = new ArrayList<>();
            monsterTex[i] = new ArrayList<>();
            for (int j = 0; j <= 3; j ++){
                monsterIdles[i].add("Monsters/" + monsters.get(i) + "/" +  monsters.get(i) + "_" + j + ".png");
                monsterTex[i].add(new Texture(monsterIdles[i].get(j)));
            }
            monsterAnimations.add(new Animation<Texture>(0.2f, monsterTex[i].toArray(new Texture[0])));
        }
    }

    private final ArrayList<Texture>[] explosionTex = new ArrayList[2];
    private final ArrayList<Animation<Texture>> explosionAnimations = new ArrayList<>();
    {
        for (int i = 0; i < 2; i ++){
            explosionTex[i] = new ArrayList<>();
            for (int j = 0; j <= 4; j ++){
                explosionTex[i].add(new Texture("Monsters/Explosions/" + i + "/" + j + ".png"));
            }
            explosionAnimations.add(new Animation<Texture>(0.15f, explosionTex[i].toArray(new Texture[0])));
        }
    }

    private final String monsterProjectile = "Monsters/MonsterProjectile.png";
    private final Texture monsterProjectileTexture = new Texture(monsterProjectile);

    private final ArrayList<String> weapons = new ArrayList<>(Arrays.asList("REVOLVER", "SHOTGUN", "SMG"));
    private final ArrayList<String> weaponImg = new ArrayList<>();
    private final ArrayList<Texture> weaponTex = new ArrayList<>();
    {
        for (int i = 0; i < weapons.size(); i ++){
            weaponImg.add("Weapons/" + weapons.get(i) + "/" + weapons.get(i) + ".png");
            weaponTex.add(new Texture(weaponImg.get(i)));
        }
    }

    private final String ammo = "Weapons/Ammo.png";
    private final Texture ammoTex = new Texture(ammo);

    private final ArrayList<Texture> numbers = new ArrayList<>();
    private final Texture backslash = new Texture("Weapons/Counter/Backslash.png");

    {
        for (int i = 0; i < 16; i++) {
            numbers.add(new Texture("Weapons/Counter/" + i  + ".png"));
        }
    }



    private final ArrayList<String> heartImg = new ArrayList<>();
    private final ArrayList<Texture> heartTex = new ArrayList<>();
    private final Animation<Texture> heartAnimation;

    {
        for (int i = 0; i < 4; i ++){
            heartImg.add("Hearts/" + "HeartAnimation_" + i + ".png");
            heartTex.add(new Texture(heartImg.get(i)));
        }
        heartAnimation = new Animation<Texture>(0.2f, heartTex.get(0), heartTex.get(1), heartTex.get(2));
    }

    private final String bullet = "bullet.png";
    private final Texture bulletTex = new Texture(bullet);

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

    public Texture getBulletTex() {
        return bulletTex;
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

    public String getMonsterProjectile() {
        return monsterProjectile;
    }

    public Texture getMonsterProjectileTexture() {
        return monsterProjectileTexture;
    }

    public ArrayList<String> getHeartImg() {
        return heartImg;
    }

    public ArrayList<Texture> getHeartTex() {
        return heartTex;
    }

    public Animation<Texture> getHeartAnimation() {
        return heartAnimation;
    }

    public String getAmmo() {
        return ammo;
    }

    public Texture getAmmoTex() {
        return ammoTex;
    }

    public ArrayList<Texture> getNumbers() {
        return numbers;
    }

    public Texture getBackslash() {
        return backslash;
    }

    public ArrayList<Texture>[] getExplosionTex() {
        return explosionTex;
    }

    public ArrayList<Animation<Texture>> getExplosionAnimations() {
        return explosionAnimations;
    }
}
