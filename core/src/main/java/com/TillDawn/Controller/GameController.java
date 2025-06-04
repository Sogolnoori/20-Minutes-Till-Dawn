package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.*;
import com.TillDawn.View.GameView;
import com.TillDawn.View.PauseMenuView;
import com.TillDawn.View.EndMenuView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.Iterator;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private MonsterController monsterController;
    private HeartController heartController;
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
        weaponController = new WeaponController(game.getWeapon(), game.getBullets(), game.getAmmoCounter(), camera);
        monsterController = new MonsterController(game.getMonsters(), game.getMonsterShots(), camera);
        heartController = new HeartController(game.getHearts(), camera);
        for (int i = 0; i < 10; i ++){
            monsterController.newTreeMonster();
        }
    }

    public void updateGame(){
        if(view != null){
            if(App.getCurrentGame().getLeftTime() <= 0){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EndMenuView(new EndMenuController(), GameAssetManager.getGameAssetManager().getSkin(), game, true));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
                App.getCurrentGame().getWeapon().reload();
                App.getCurrentGame().getAmmoCounter().setAmmo(App.getCurrentGame().getWeapon().getAmmo());
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            worldController.update();
            playerController.update();
            weaponController.update();
            monsterController.update();
            heartController.update();
            killMonsters();
            killPlayer();
        }
    }

    public void killMonsters() {
        for (Monster monster : game.getMonsters()) {
            if(monster.isDying()) continue;
            Iterator<Projectile> bulletIterator = game.getBullets().iterator();
            while (bulletIterator.hasNext()) {
                Projectile bullet = bulletIterator.next();

                if (monster.getRect().collidesWith(bullet.getRect())) {
                    monster.reduceHealth();

                    if (monster.getMonsterHealth() <= 0) {
                        monsterController.kill(monster);
                        game.getPlayer().addKills();
                    }

                    bulletIterator.remove();
                    break;
                }
            }
        }
    }

    public void killPlayer() {
        if(game.getPlayer().getPlayerHealth() <= 0){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new EndMenuView(
                new EndMenuController(),
                GameAssetManager.getGameAssetManager().getSkin(),
                game, false));
        }
        if(game.getPlayer().getInvisibleTimeLeft() > 0){
            return;
        }
        for (Monster monster : game.getMonsters()) {
            if(monster.getRect().collidesWith(game.getPlayer().getRect())){
                game.getPlayer().reduceHealth();
                game.getPlayer().setInvisibleTimeLeft(1);
                break;
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
