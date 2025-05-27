package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Views.*;

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
                view.getVolumeSlider().setValue(1f); // مقدار اولیه روی ماکس
            }
        }
    }
}
