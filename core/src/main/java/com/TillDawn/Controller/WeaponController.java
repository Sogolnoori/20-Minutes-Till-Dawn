package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class WeaponController {
    private final Weapon weapon;
    private final ArrayList<Projectile> bullets;
    private final AmmoCounter ammoCounter;
    private final OrthographicCamera camera;


    public WeaponController(Weapon weapon, ArrayList<Projectile> bullets, AmmoCounter ammoCounter, OrthographicCamera camera) {
        this.weapon = weapon;
        this.bullets = bullets;
        this.ammoCounter = ammoCounter;
        this.camera = camera;
    }

    public void update(){
        weapon.setX(App.getCurrentGame().getPlayer().getPosX() + 30);
        weapon.setY(App.getCurrentGame().getPlayer().getPosY() + 30);
        weapon.getSmgSprite().setX(weapon.getX());
        weapon.getSmgSprite().setY(weapon.getY());
        updateBullets();
    }

    public void handleWeaponRotation(int screenX, int screenY) {
        Vector3 worldCoords = new Vector3(screenX, screenY, 0);
        camera.unproject(worldCoords);

        float mouseX = worldCoords.x;
        float mouseY = worldCoords.y;

        Sprite weaponSprite = weapon.getSmgSprite();
        float weaponCenterX = weapon.getX() + weaponSprite.getWidth() / 2f;
        float weaponCenterY = weapon.getY() + weaponSprite.getHeight() / 2f;

        float angleDeg = (float) Math.toDegrees(Math.atan2(mouseY - weaponCenterY, mouseX - weaponCenterX));

        weaponSprite.setOriginCenter();
        weaponSprite.setRotation(angleDeg);
    }

    public void handleWeaponShoot(int x, int y){
        if(weapon.getAmmo() == 0){
            return;
        }
        bullets.add(new Projectile(weapon.getX(), weapon.getY(), x, y, GameAssetManager.getGameAssetManager().getBulletTex()));
        weapon.setAmmo(weapon.getAmmo() - 1);
        if(weapon.isAutoReload()){
            weapon.reload();
        }
        ammoCounter.setAmmo(weapon.getAmmo());
    }

    public void updateBullets() {
        for(Projectile b : bullets) {
            b.getSprite().draw(Main.getBatch());

            b.setXPos(b.getSprite().getX() - b.getDirection().x * 5);
            b.setYPos(b.getSprite().getY() + b.getDirection().y * 5);

            b.getSprite().setX(b.getXPos());
            b.getSprite().setY(b.getYPos());
        }
    }
}
