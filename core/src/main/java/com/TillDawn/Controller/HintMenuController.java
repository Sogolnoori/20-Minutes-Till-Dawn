package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.View.HintMenuView;
import com.TillDawn.View.MainMenuView;

public class HintMenuController {
    private HintMenuView view;

    public void setView(HintMenuView view) {
        this.view = view;
    }

    public void handleButtons(){
        if(view != null){
            if(view.getPreviousButton().isChecked()){
                view.showPage((view.getCurrentPage() - 1 + 4) % 4);
                view.getPreviousButton().setChecked(false);

            }
            if(view.getNextButton().isChecked()){
                view.showPage((view.getCurrentPage() + 1) % 4);
                view.getNextButton().setChecked(false);
            }
            if(view.getMainButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
