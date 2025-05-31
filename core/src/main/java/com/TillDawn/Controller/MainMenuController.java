package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.GameAssetManager;
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

            if(view.getLogOutButton().isChecked()){
                Main.getMain().getScreen().dispose();
                App.setCurrentUser(null);
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
