package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.*;
import com.TillDawn.Views.GameView;
import com.TillDawn.Views.PauseMenuView;
import com.TillDawn.Views.WinMenuView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private MonsterController monsterController;
    private OrthographicCamera camera;

    private final Game game;

    GameController(Game game) {
        this.game = game;
    }

    public void setView(GameView view, OrthographicCamera camera) {
        this.view = view;
        this.camera = camera;
        playerController = new PlayerController(game.getPlayer(), camera);
        worldController = new WorldController(playerController, camera);
        weaponController = new WeaponController(game.getWeapon(), game.getBullets(), camera);
        monsterController = new MonsterController(game.getMonsters(), camera);
    }

    public void updateGame(){
        if(view != null){
            if(App.getCurrentGame().getTime() <= 0){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new WinMenuView(new WinMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            worldController.update();
            playerController.update();
            weaponController.update();
            monsterController.update();
            killMonsters();
        }
    }

    public void killMonsters() {
        for (Monster monster : game.getMonsters()) {
            for (Bullet bullet : game.getBullets()) {
                if(monster.getRect().collidesWith(bullet.getRect())){
                    //hala chi??
                    // na mikham bedoonam halla chiiii
                }
            }
        }
    }

    public PlayerController getPlayerController(){
        return this.playerController;
    }

    public WeaponController getWeaponController(){
        return this.weaponController;
    }
}
