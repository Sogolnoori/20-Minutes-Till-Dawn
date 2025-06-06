package com.TillDawn.Controller;

import com.TillDawn.Model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
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

    private float squareDistance(float x1, float y1, float x2, float y2) {
        return (float) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public void handleWeaponShoot(int xClicked, int yClicked) {
        if(weapon.getAmmo() == 0){
            return;
        }
        Vector2 dir = new Vector2(
            xClicked - Gdx.graphics.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - yClicked);

        if(weapon.isAutoAim() && !App.getCurrentGame().getMonsters().isEmpty()){
            boolean aliveMonster = false;
            float x = 0, y = 0;
            for (Monster monster : App.getCurrentGame().getMonsters()) {
                if(monster.isDying()) continue;
                if(!aliveMonster){
                    x = monster.getMidX();
                    y = monster.getMidY();
                    aliveMonster = true;
                }
                float d1 = squareDistance(weapon.getX(), weapon.getY(), x, y);
                float d2 = squareDistance(weapon.getX(), weapon.getY(), monster.getMidX(), monster.getMidY());
                if(d2 < d1){
                    x = monster.getMidX();
                    y = monster.getMidY();
                }
            }
            if(aliveMonster) {
                dir.set(x - weapon.getX(), y - weapon.getY());
            }
        }
        dir.nor();

        for (int i = 0; i < weapon.getProjectile(); i ++) {
            bullets.add(new Projectile(
                weapon.getX() + dir.x * 25 * i,
                weapon.getY() + dir.y * 25 * i,
                dir,
                GameAssetManager.getGameAssetManager().getBulletTex()));
        }

        weapon.setAmmo(weapon.getAmmo() - 1);
        if(weapon.getAmmo() == 0 && weapon.isAutoReload()){
            weapon.reload();
        }
        ammoCounter.setAmmo(weapon.getAmmo());
    }

    public void updateBullets() {
        for(Projectile b : bullets) {

            b.setXPos(b.getSprite().getX() + b.getDirection().x * 5);
            b.setYPos(b.getSprite().getY() + b.getDirection().y * 5);

            b.getSprite().setX(b.getXPos());
            b.getSprite().setY(b.getYPos());
        }
    }
}
