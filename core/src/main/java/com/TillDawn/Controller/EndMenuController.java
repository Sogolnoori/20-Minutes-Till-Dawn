package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.Game;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.Model.User;
import com.TillDawn.View.MainMenuView;
import com.TillDawn.View.PreGameMenuView;
import com.TillDawn.View.EndMenuView;

public class EndMenuController {

    private EndMenuView view;

    public void setView(EndMenuView view) {
        this.view = view;
    }

    public void handleButtons() {
        if(view != null){
            if(view.getRetryButton().isChecked()){
                dljfsljdkj();
                App.getCurrentUser().setCurrentGame(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getQuitButton().isChecked()){
                dljfsljdkj();
                App.getCurrentUser().setCurrentGame(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    private void dljfsljdkj(){
        Game game = App.getCurrentUser().getCurrentGame();
        User user = App.getCurrentUser();
        user.addKills(game.getPlayer().getKills());
        user.addScore((int)(game.getTimeSpent() * game.getPlayer().getKills()));
        user.updateMaxTimeAlive((int)game.getTimeSpent());
    }
}
