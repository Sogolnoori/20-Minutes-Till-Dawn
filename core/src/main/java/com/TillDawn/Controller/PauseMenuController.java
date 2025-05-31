package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.View.GameView;
import com.TillDawn.View.MainMenuView;
import com.TillDawn.View.PauseMenuView;

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
            if(view.getGiveUpButton().isChecked()) {
                App.getCurrentUser().setCurrentGame(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
