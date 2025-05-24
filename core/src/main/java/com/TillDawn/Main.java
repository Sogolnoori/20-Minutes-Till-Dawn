package com.TillDawn;

import com.TillDawn.Controllers.ForgetPasswordMenuController;
import com.TillDawn.Controllers.MainMenuController;
import com.TillDawn.Controllers.RegisterMenuController;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Views.ForgetPasswordMenuView;
import com.TillDawn.Views.MainMenuView;
import com.TillDawn.Views.RegisterMenuView;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        //main.setScreen(new ForgetPasswordMenuView(new ForgetPasswordMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        main.setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

        //main.setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }
}
