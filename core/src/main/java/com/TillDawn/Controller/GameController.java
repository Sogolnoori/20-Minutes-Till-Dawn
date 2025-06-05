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
        monsterController = new MonsterController(game.getMonsters(), game.getMonsterShots(), game.getDroplets(), camera);
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
            if(Gdx.input.isKeyJustPressed(Input.Keys.T)){
                game.updateTimeSpent(60);
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
                game.getPlayer().addXp(game.getPlayer().getXpNeeded() - game.getPlayer().getXp());
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.H)){
                if(game.getPlayer().getPlayerHealth() < game.getPlayer().getHeroEnum().getHp()) {
                    App.getCurrentGame().getPlayer().addHealth();
                }
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
                for (int i = 0; i < 10000; i ++){
                    App.getCurrentGame().getPlayer().addKills();
                }
            }
            worldController.update();
            playerController.update();
            weaponController.update();
            monsterController.update();
            heartController.update();
            killMonsters();
            killPlayer();
            getDroplets();
        }
    }

    public void killMonsters() {
        for (Monster monster : game.getMonsters()) {
            if(monster.isDying()) continue;
            Iterator<Projectile> bulletIterator = game.getBullets().iterator();
            while (bulletIterator.hasNext()) {
                Projectile bullet = bulletIterator.next();

                if (monster.getRect().collidesWith(bullet.getRect())) {
                    monster.reduceHealth(App.getCurrentGame().getWeapon().getWeaponEnum().getDamage());

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
            if(monster.isDying()) continue;
            if(monster.getRect().collidesWith(game.getPlayer().getRect())){
                game.getPlayer().reduceHealth();
                game.getPlayer().setInvisibleTimeLeft(1);
                break;
            }
        }
        for (Projectile shot : game.getMonsterShots()) {
            if(shot.getRect().collidesWith(game.getPlayer().getRect())){
                game.getPlayer().reduceHealth();
                game.getPlayer().setInvisibleTimeLeft(1);
                game.getMonsterShots().remove(shot);
                break;
            }
        }
    }

    public void getDroplets() {
        Iterator<Projectile> iter = game.getDroplets().iterator();
        while (iter.hasNext()) {
            Projectile droplet = iter.next();
            if (droplet.getRect().collidesWith(game.getPlayer().getRect())) {
                game.getPlayer().addXp(3);
                iter.remove();
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
