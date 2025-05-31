package com.TillDawn.Model.Enum;

public enum WeaponEnum {
    Revolver (6, 20, 1, 1),
    Shotgun (2, 10, 4, 1),
    Smg (24, 8, 1, 2);

    private final int ammo;
    private final int damage;
    private final int projectile;
    private final int reloadTime;

    WeaponEnum(int ammo, int damage, int projectile, int reloadTime) {
        this.ammo = ammo;
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectile() {
        return projectile;
    }

    public int getReloadTime() {
        return reloadTime;
    }
}
