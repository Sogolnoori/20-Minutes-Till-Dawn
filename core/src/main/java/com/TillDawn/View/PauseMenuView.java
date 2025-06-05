package com.TillDawn.View;

import com.TillDawn.Controller.PauseMenuController;
import com.TillDawn.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseMenuView implements Screen {

    private Stage stage;
    private Table table;
    private final PauseMenuController controller;

    private final TextButton resumeButton;
    private final TextButton giveUpButton;
    private final TextButton saveButton;

    public PauseMenuView(PauseMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();

        this.resumeButton = new TextButton("Resume", skin);
        this.giveUpButton = new TextButton("Give Up:(", skin);
        this.saveButton = new TextButton("Save and Exit", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0, 10, 0);
        table.add(resumeButton).row();
        table.add(giveUpButton).row();
        table.add(saveButton).row();

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleButtons();
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

    public TextButton getResumeButton() {
        return resumeButton;
    }

    public TextButton getGiveUpButton() {
        return giveUpButton;
    }

    public TextButton getSaveButton() {
        return saveButton;
    }
}
