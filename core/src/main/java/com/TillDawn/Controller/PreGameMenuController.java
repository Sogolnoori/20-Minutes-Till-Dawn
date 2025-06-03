package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.Game;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.View.GameView;
import com.TillDawn.View.PreGameMenuView;
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

        //Hero
        int hero = 0;
        for (int i = 0; i < GameAssetManager.getGameAssetManager().getHeroes().size(); i++) {
            if(GameAssetManager.getGameAssetManager().getHeroes().get(i).equals(view.getSelectHero().getSelected())){
                hero = i;
                break;
            }
        }

        //Time
        float time = 0;
        float[] times = {2, 5, 10, 20};
        Array<String> timeNames = new Array<>();
        timeNames.add("2 minutes");
        timeNames.add("5 minutes");
        timeNames.add("10 minutes");
        timeNames.add("20 minutes");
        for (int i = 0; i < times.length; i++) {
            if(timeNames.get(i).equals(view.getSelectTime().getSelected())){
                time = 500000;
//                time = time[i] * 60;

                break;
            }
        }

        //Weapon
        int weapon = 0;
        for (int i = 0; i < GameAssetManager.getGameAssetManager().getWeapons().size(); i++) {
            if(GameAssetManager.getGameAssetManager().getWeapons().get(i).equals(view.getSelectWeapon().getSelected())){
                weapon = i;
                break;
            }
        }

        Game game = new Game(time, hero, weapon);
        App.getCurrentUser().setCurrentGame(game);

        return game;
    }
}
