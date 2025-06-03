package com.TillDawn.Model;

import com.TillDawn.Model.Enum.WeaponEnum;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    private WeaponEnum weaponEnum;
    private int type = 0;
    private float x;
    private float y;
    private Texture smgTexture;
    private Sprite smgSprite;
    private int ammo;
    private boolean autoReload = false;

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
        this.weaponEnum = WeaponEnum.values()[type];
        this.ammo = weaponEnum.getAmmo();
        this.smgTexture = GameAssetManager.getGameAssetManager().getWeaponTex().get(weapon);
        this.smgSprite = new Sprite(smgTexture);
        this.x = 0;//App.getCurrentGame().getPlayer().getPosX();
        this.y = 0;//App.getCurrentGame().getPlayer().getPosY();
        this.smgSprite.setX(x);
        this.smgSprite.setY(y);

        smgSprite.setSize(50, 50);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }
}
