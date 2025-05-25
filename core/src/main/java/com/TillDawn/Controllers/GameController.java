package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.Game;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Models.Weapon;
import com.TillDawn.Views.GameView;
import com.TillDawn.Views.WinMenuView;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
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
        weaponController = new WeaponController(game.getWeapon());
    }

    public void updateGame(){
        if(view != null){
            if(App.getCurrentGame().getTime() <= 0){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new WinMenuView(new WinMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            worldController.update();
            playerController.update();
            weaponController.update();
        }
    }

    public PlayerController getPlayerController(){
        return this.playerController;
    }

    public WeaponController getWeaponController(){
        return this.weaponController;
    }
}
