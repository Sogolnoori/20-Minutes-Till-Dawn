package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Views.PauseMenuView;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.TillDawn.Models.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class PlayerController {
    private Player player;
    private OrthographicCamera camera;

    public PlayerController(Player player, OrthographicCamera camera) {
        this.player = player;
        this.camera = camera;
    }

    public void update() {

        if(player.isPlayerIdle()){
            idleAnimation();
        }
        handlePlayerInput();
    }

    public void handlePlayerInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            player.setPosY(player.getPosY() + player.getSpeed());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.setPosX(player.getPosX() + player.getSpeed());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            player.setPosY(player.getPosY() - player.getSpeed());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.setPosX(player.getPosX() - player.getSpeed());
            player.getPlayerSprite().flip(true, false);
        }
        player.getPlayerSprite().setPosition(player.getPosX(), player.getPosY());

    }

    public void idleAnimation() {
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getHeroAnimations().get(player.getHero());

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if(!animation.isAnimationFinished(player.getTime())){
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else{
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
