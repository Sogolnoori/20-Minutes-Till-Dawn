package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.Game;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Views.GameView;
import com.TillDawn.Views.PreGameMenuView;
import com.badlogic.gdx.utils.Array;

public class PreGameMenuController {
    private PreGameMenuView view;

    public void setView(PreGameMenuView view) {
        this.view = view;
    }

    public void handlePreGameMenuButtons(){
        if(view != null){
            if(view.getPlayButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameController(setUpNewGame()), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    public Game setUpNewGame(){

        Game game = new Game();
        App.getCurrentUser().setCurrentGame(game);

        //Hero
        for (int i = 0; i < GameAssetManager.getGameAssetManager().getHeroes().size(); i++) {
            if(GameAssetManager.getGameAssetManager().getHeroes().get(i).equals(view.getSelectHero().getSelected())){
                game.getPlayer().setHero(i);
                break;
            }
        }

        //Time
        float[] time = {2, 5, 10, 20};
        Array<String> times = new Array<>();
        times.add("2 minutes");
        times.add("5 minutes");
        times.add("10 minutes");
        times.add("20 minutes");
        for (int i = 0; i < time.length; i++) {
            if(times.get(i).equals(view.getSelectTime().getSelected())){
                game.setTime(500000);
//                game.setTime(time[i] * 60);

                break;
            }
        }

        //Weapon

        for (int i = 0; i < GameAssetManager.getGameAssetManager().getWeapons().size(); i++) {
            if(GameAssetManager.getGameAssetManager().getWeapons().get(i).equals(view.getSelectWeapon().getSelected())){
                game.getWeapon().setWeaponType(i);
                break;
            }
        }

        return game;
    }
}
