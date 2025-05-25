package com.TillDawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    private int type = 0;
    private  Texture smgTexture;
    private Sprite smgSprite;
    private int ammo = 30;

    public Sprite getSmgSprite() {
        return smgSprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setWeaponType(int weapon) {
        this.type = weapon;
        this.smgTexture = GameAssetManager.getGameAssetManager().getWeaponTex().get(weapon);
        this.smgSprite = new Sprite(smgTexture);
        this.smgSprite.setX(App.getCurrentGame().getPlayer().getPosX());
        this.smgSprite.setY(App.getCurrentGame().getPlayer().getPosY());

        smgSprite.setSize(50, 50);
    }
}
