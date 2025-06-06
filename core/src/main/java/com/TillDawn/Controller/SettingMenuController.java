package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.View.*;

public class SettingMenuController {
    private SettingMenuView view;

    public void setView(SettingMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons(){
        if(view != null){
            if(view.getMainMenuButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getNextMusic().isChecked()){
                App.nextMusic();
                view.getVolumeSlider().setValue(1f);
            }
        }
    }
}
