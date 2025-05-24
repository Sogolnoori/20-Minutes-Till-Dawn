package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Views.GameView;
import com.TillDawn.Views.PauseMenuView;

public class PauseMenuController {
    private PauseMenuView view;

    public void setView(PauseMenuView view) {
        this.view = view;
    }

    public void handleButtons() {
        if(view != null){
            if(view.getResumeButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameController(App.getCurrentGame()), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
