package com.TillDawn.View;

import com.TillDawn.Controller.EndMenuController;
import com.TillDawn.Model.App;
import com.TillDawn.Model.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndMenuView implements Screen {

    private final Game game;
    private final boolean hasWon;

    private Stage stage;

    private BitmapFont font;

    private final TextButton retryButton;
    private final TextButton quitButton;



    private final EndMenuController controller;

    public EndMenuView(EndMenuController controller, Skin skin, Game game, boolean hasWon) {
        this.controller = controller;

        this.hasWon = hasWon;
        this.game = game;

        this.retryButton = new TextButton("Try Again", skin);
        this.quitButton = new TextButton("Quit to Menu", skin);

        controller.setView(this);
    }

    @Override
    public void show() {

        font = new BitmapFont();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.WHITE);
        Label title = new Label(hasWon ? "You Survived" : "You Died", titleStyle);
        title.setFontScale(3f);
        title.setAlignment(Align.center);

        Label stats = new Label(
            String.format("%s\n Time: %.2f\nKills: %d\nScore: %d", App.getCurrentUser().getUsername(), game.getTimeSpent(), game.getPlayer().getKills(), (int)(game.getTimeSpent() * game.getPlayer().getKills())),
            new Label.LabelStyle(font, Color.LIGHT_GRAY)
        );
        stats.setFontScale(1.5f);
        stats.setAlignment(Align.center);

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(title).padBottom(30).row();
        table.add(stats).padBottom(30).row();
        table.add(retryButton).padBottom(10).row();
        table.add(quitButton);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
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

    public Game getGame() {
        return game;
    }

    public TextButton getRetryButton() {
        return retryButton;
    }

    public TextButton getQuitButton() {
        return quitButton;
    }
}
