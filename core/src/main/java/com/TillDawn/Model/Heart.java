package com.TillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Heart {
    private int id;
    private boolean active;
    private Texture heartTexture;
    private Sprite heartSprite;
    private float time = 0;

    public Heart(int id){
        this.id = id;
        this.active = true;
        heartTexture = GameAssetManager.getGameAssetManager().getHeartTex().get(0);
        heartSprite = new Sprite(heartTexture);
        heartSprite.setPosition(id * heartTexture.getWidth() * 2.5f + 10, Gdx.graphics.getHeight() - heartTexture.getHeight() * 2.5f - 35);
        heartSprite.setSize(heartTexture.getWidth() * 2, heartSprite.getHeight() * 2);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        if(!active){
            this.heartTexture = GameAssetManager.getGameAssetManager().getHeartTex().get(3);
            heartSprite = new Sprite(heartTexture);
            heartSprite.setPosition(id * heartTexture.getWidth() * 2.5f + 10, Gdx.graphics.getHeight() - heartTexture.getHeight() * 2.5f - 35);
            heartSprite.setSize(heartTexture.getWidth() * 2, heartSprite.getHeight() * 2);
        }
    }

    public Texture getHeartTexture() {
        return heartTexture;
    }

    public Sprite getHeartSprite() {
        return heartSprite;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
