package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Views.MainMenuView;
import com.TillDawn.Views.ScoreBoardMenuView;

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
//            if(view.getScoreButton().isChecked()){
//                App.sortUsers(0);
//            }
//            if(view.getUserButton().isChecked()){
//                App.sortUsers(1);
//            }
//            if(view.getKillButton().isChecked()){
//                App.sortUsers(2);
//            }
//            if(view.getMaxTimeAliveButton().isChecked()){
//                App.sortUsers(3);
//            }
        }
    }
}
