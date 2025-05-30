package com.TillDawn.Model;

import com.TillDawn.Model.Enum.WeaponEnum;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    private WeaponEnum weaponEnum;
    private int type = 0;
    private float x;
    private float y;
    private Sprite smgSprite;
    private int maxAmmo;
    private int ammo;
    private boolean autoReload = false;
    private boolean autoAim = false;
    private int damage;
    private int projectile;

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
        this.maxAmmo = ammo;
        this.damage = weaponEnum.getDamage();
        Texture smgTexture = GameAssetManager.getGameAssetManager().getWeaponTex().get(weapon);
        this.smgSprite = new Sprite(smgTexture);
        this.x = 0;
        this.y = 0;
        this.smgSprite.setX(x);
        this.smgSprite.setY(y);
        this.projectile = weaponEnum.getProjectile();

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

    public WeaponEnum getWeaponEnum() {
        return weaponEnum;
    }

    public int getMaxAmmo(){
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void reload() {
        this.ammo = maxAmmo;
    }

    public void addProjectile() {
        this.projectile ++;
    }

    public int getProjectile() {
        return projectile;
    }

    public boolean isAutoAim() {
        return autoAim;
    }

    public void setAutoAim(boolean autoAim) {
        this.autoAim = autoAim;
    }
}
