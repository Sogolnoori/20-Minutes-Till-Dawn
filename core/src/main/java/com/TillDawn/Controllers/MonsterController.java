package com.TillDawn.Controllers;

import com.TillDawn.Models.App;
import com.TillDawn.Models.Bullet;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Models.Monster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

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
}
