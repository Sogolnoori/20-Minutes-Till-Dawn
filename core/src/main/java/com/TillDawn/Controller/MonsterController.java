package com.TillDawn.Controller;

import com.TillDawn.Model.App;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.Model.Monster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class MonsterController {
    private final ArrayList<Monster> monsters;
    OrthographicCamera camera;

    public MonsterController(ArrayList<Monster> monsters, OrthographicCamera camera) {
        this.monsters = monsters;
        this.camera = camera;
    }

    public void update() {
        for (Monster monster : monsters) {
            idleAnimation(monster);
            move(monster);
        }
    }

    public void move(Monster monster) {

        Vector2 direction = new Vector2(
            App.getCurrentGame().getPlayer().getPosX() - monster.getPosX(),
            App.getCurrentGame().getPlayer().getPosY() - monster.getPosY()
        ).nor();

        //RANDOM DIRECTION
//        Random random = new Random();
//        int rand = random.nextInt(2);
//        if(rand == 0) {
//            float angle = random.nextFloat() * 360f;
//            direction = new Vector2(1, 0).setAngleDeg(angle);
//        }

        monster.setPosX(monster.getMonsterSprite().getX() + direction.x * Monster.getSpeed());
        monster.setPosY(monster.getMonsterSprite().getY() + direction.y * Monster.getSpeed());

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

    public void kill(Monster monster) {
        monsters.remove(monster);
    }
}
