package com.TillDawn.Views;

import com.TillDawn.Controllers.SettingMenuController;
import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingMenuView implements Screen {

    private Stage stage;
    private Table table;
    private final SettingMenuController controller;

    private final TextButton nextMusic;
    private Slider volumeSlider;
    private final TextButton mainMenuButton;

    public SettingMenuView(SettingMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        controller.setView(this);

        this.nextMusic = new TextButton("Next Music", skin);
        this.volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        volumeSlider.setValue(1f); // مقدار اولیه روی ماکس
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.getMusic().setVolume(volumeSlider.getValue());
            }
        });

        this.mainMenuButton = new TextButton("<-", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0, 10, 0);
        table.add(nextMusic).row();
        table.add(volumeSlider).width(400).pad(10);

        stage.addActor(table);
        stage.addActor(mainMenuButton);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleMainMenuButtons();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public TextButton getNextMusic() {
        return nextMusic;
    }

    public TextButton getMainMenuButton() {
        return mainMenuButton;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }
}
