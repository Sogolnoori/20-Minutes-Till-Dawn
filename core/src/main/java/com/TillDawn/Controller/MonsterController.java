package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.Enum.MonsterEnum;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.Model.Monster;
import com.TillDawn.Model.Projectile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MonsterController {
    private final ArrayList<Monster> monsters;
    private final ArrayList<Projectile> monsterShots;
    private final ArrayList<Projectile> droplets;
    OrthographicCamera camera;

    public MonsterController(ArrayList<Monster> monsters, ArrayList<Projectile> monsterShots,ArrayList<Projectile> droplets, OrthographicCamera camera) {
        this.monsters = monsters;
        this.monsterShots = monsterShots;
        this.droplets = droplets;
        this.camera = camera;
    }

    public void update() {
        Iterator<Monster> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Monster monster = iterator.next();

            if (monster.isDying()) {
                playExplosion(monster);
                monster.setDeathTime(monster.getDeathTime() + Gdx.graphics.getDeltaTime());
                if (monster.getDeathTime() > 0.8f) {
                    iterator.remove();
                }
                continue;
            }

            idleAnimation(monster);
            move(monster);
        }
        updateMonsterShots();
    }

    public void move(Monster monster) {

        Vector2 direction = new Vector2(
            App.getCurrentGame().getPlayer().getPosX() - monster.getPosX(),
            App.getCurrentGame().getPlayer().getPosY() - monster.getPosY()
        ).nor();

        monster.setPosX(monster.getMonsterSprite().getX() + direction.x * monster.getSpeed());
        monster.setPosY(monster.getMonsterSprite().getY() + direction.y * monster.getSpeed());

        if(monster.getPosX() > App.getCurrentGame().getPlayer().getPosX()){
            monster.getMonsterSprite().flip(true, false);
        }
        monster.getMonsterSprite().setPosition(monster.getPosX(), monster.getPosY());
    }

    public void idleAnimation(Monster monster) {
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getMonsterAnimations().get(monster.getMonster());

        monster.getMonsterSprite().setRegion(animation.getKeyFrame(monster.getTime()));

        if(!animation.isAnimationFinished(monster.getTime())){
            monster.setTime(monster.getTime() + Gdx.graphics.getDeltaTime());
        }
        else{
            monster.setTime(0);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    private void playExplosion(Monster monster) {
        int explosionId = 0;
        if(!monster.getMonsterEnum().equals(MonsterEnum.Tree)) explosionId = 1;
        Animation<Texture> explosion = GameAssetManager.getGameAssetManager().getExplosionAnimations().get(explosionId);

        monster.getMonsterSprite().setRegion(explosion.getKeyFrame(monster.getDeathTime(), false));
    }

    public void kill(Monster monster) {
        monster.setDying(true);
        monster.setDeathTime(0);
        for (int i = 0; i < 5; i++) {
            float angle = (float)(Math.random() * 360f);
            float distance = 30f + (float)(Math.random() * 70f);

            Vector2 offset = new Vector2(distance, 0).setAngleDeg(angle);

            float finalX = monster.getMidX() + offset.x;
            float finalY = monster.getMidY() + offset.y;

            Texture tex = new Texture("Monsters/Droplet.png");
            droplets.add(new Projectile(finalX, finalY, offset, tex));
        }
    }

    public void newTreeMonster(){
        Random rand = new Random();
        float x, y;
        x = App.getCurrentGame().getMapWidth() * rand.nextFloat();
        y = App.getCurrentGame().getMapHeight() * rand.nextFloat();
        Monster monster = new Monster(0, x, y);
        monsters.add(monster);
    }

    public void updateMonsterShots() {
        for(Projectile b : monsterShots) {
            b.setXPos(b.getSprite().getX() + b.getDirection().x);
            b.setYPos(b.getSprite().getY() + b.getDirection().y);

            b.getSprite().setX(b.getXPos());
            b.getSprite().setY(b.getYPos());
        }
    }
}
