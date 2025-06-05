package com.TillDawn.Controller;

import com.TillDawn.Model.App;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.Model.Heart;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.ArrayList;

public class HeartController {
    private final ArrayList<Heart> hearts;
    OrthographicCamera camera;

    public HeartController(ArrayList<Heart> hearts, OrthographicCamera camera) {
        this.hearts = hearts;
        this.camera = camera;
    }

    public void update() {
        for (int i = 0; i < hearts.size(); i++) {
            if(App.getCurrentGame().getPlayer().getPlayerHealth() <= i){
                hearts.get(i).setActive(false);
            }
            else {
                hearts.get(i).setActive(true);
            }
        }
        for (Heart heart : hearts) {
            if(heart.isActive()) {
                idleAnimation(heart);
            }
        }
    }

    public void idleAnimation(Heart heart) {
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getHeartAnimation();

        heart.getHeartSprite().setRegion(animation.getKeyFrame(heart.getTime()));

        if(!animation.isAnimationFinished(heart.getTime())){
            heart.setTime(heart.getTime() + Gdx.graphics.getDeltaTime());
        }
        else{
            heart.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }
}
