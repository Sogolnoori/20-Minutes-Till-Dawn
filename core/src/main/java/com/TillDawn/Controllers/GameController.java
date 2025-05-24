package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.Game;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Models.Weapon;
import com.TillDawn.Views.GameView;
import com.TillDawn.Views.WinMenuView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;

    private final Game game;

    GameController(Game game) {
        this.game = game;
    }

    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(game.getPlayer());
        worldController = new WorldController(playerController);
        weaponController = new WeaponController(new Weapon());
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
