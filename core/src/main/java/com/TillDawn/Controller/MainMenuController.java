package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.Model.SaveManager;
import com.TillDawn.View.*;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons(){
        if(view != null){
            if(view.getProfileButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getPreGameMenuButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getScoreBoardButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ScoreBoardMenuView(new ScoreBoardMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getHintMenuButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new HintMenuView(new HintMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getSettingsButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SettingMenuView(new SettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getContinueSavedButton().isChecked()){
                if(App.getCurrentUser().getCurrentGame() != null){
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new GameView(new GameController(App.getCurrentGame()), GameAssetManager.getGameAssetManager().getSkin()));
                }
                else{
                    view.getMessage().setText("No saved game");
                }
            }
            if(view.getLogOutButton().isChecked()){
                //SaveManager.save(App.users);
                Main.getMain().getScreen().dispose();
                App.setCurrentUser(null);
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
