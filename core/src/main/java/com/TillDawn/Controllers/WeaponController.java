package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.Bullet;
import com.TillDawn.Models.Weapon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class WeaponController {
    private final Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private OrthographicCamera camera;


    public WeaponController(Weapon weapon, ArrayList<Bullet> bullets, OrthographicCamera camera) {
        this.weapon = weapon;
        this.bullets = bullets;
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
        bullets.add(new Bullet(weapon.getX(), weapon.getY(), x, y));
        weapon.setAmmo(weapon.getAmmo() - 1);
    }

    public void updateBullets() {
        for(Bullet b : bullets) {
            b.getSprite().draw(Main.getBatch());

            b.setXPos(b.getSprite().getX() - b.getDirection().x * 5);
            b.setYPos(b.getSprite().getY() + b.getDirection().y * 5);

            b.getSprite().setX(b.getXPos());
            b.getSprite().setY(b.getYPos());
        }
    }
}
