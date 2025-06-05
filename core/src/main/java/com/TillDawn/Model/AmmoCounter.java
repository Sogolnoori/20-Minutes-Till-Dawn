package com.TillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AmmoCounter {
    private final Sprite ammoSprite;
    private final Sprite currentAmmoSprite;
    private final Sprite totalAmmoSprite;
    private final Sprite backslashSprite;
    private int currentAmmo;
    private final int totalAmmo;

    public AmmoCounter(int ammo) {
        this.ammoSprite = new Sprite(GameAssetManager.getGameAssetManager().getAmmoTex());
        this.ammoSprite.setPosition(30, Gdx.graphics.getHeight() - 167);
        this.ammoSprite.setSize((int)(ammoSprite.getWidth() * 1.5), (int)(ammoSprite.getHeight() * 1.5));

        this.currentAmmoSprite = new Sprite(GameAssetManager.getGameAssetManager().getNumbers().get(ammo));
        this.currentAmmoSprite.setPosition(60, Gdx.graphics.getHeight() - 180);
        this.currentAmmoSprite.setSize((int)(currentAmmoSprite.getWidth() * 0.3), (int)(currentAmmoSprite.getHeight() * 0.3));

        this.backslashSprite = new Sprite(GameAssetManager.getGameAssetManager().getBackslash());
        this.backslashSprite.setPosition(115, Gdx.graphics.getHeight() - 170);
        this.backslashSprite.setSize((int)(backslashSprite.getWidth() * 0.09), (int)(backslashSprite.getHeight() * 0.09));

        this.totalAmmoSprite = new Sprite(GameAssetManager.getGameAssetManager().getNumbers().get(ammo));
        this.totalAmmoSprite.setPosition(140, Gdx.graphics.getHeight() - 180);
        this.totalAmmoSprite.setSize((int)(totalAmmoSprite.getWidth() * 0.3), (int)(totalAmmoSprite.getHeight() * 0.3));

        this.currentAmmo = ammo;
        this.totalAmmo = ammo;
    }

    public Sprite getAmmoSprite() {
        return ammoSprite;
    }

    public Sprite getCurrentAmmoSprite() {
        return currentAmmoSprite;
    }

    public Sprite getBackslashSprite() {
        return backslashSprite;
    }

    public Sprite getTotalAmmoSprite() {
        return totalAmmoSprite;
    }

    public void setAmmo(int ammo) {
        currentAmmo = ammo;
        currentAmmoSprite.setTexture(GameAssetManager.getGameAssetManager().getNumbers().get(ammo));
    }
}
