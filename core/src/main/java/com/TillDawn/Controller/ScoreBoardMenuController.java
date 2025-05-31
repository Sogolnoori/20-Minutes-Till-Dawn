package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.View.MainMenuView;
import com.TillDawn.View.ScoreBoardMenuView;

public class ScoreBoardMenuController {
    private ScoreBoardMenuView view;

    public void setView(ScoreBoardMenuView view) {
        this.view = view;
    }

    public void handleButtons() {
        if(view != null){
            if(view.getMainMenuButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
