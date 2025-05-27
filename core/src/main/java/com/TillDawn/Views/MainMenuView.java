package com.TillDawn.Views;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.TillDawn.Controllers.MainMenuController;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuView implements Screen {

    private Stage stage;

    private final TextButton settingsButton;
    private final TextButton profileButton;
    private final TextButton preGameMenuButton;
    private final TextButton scoreBoardButton;
    private final TextButton hintMenuButton;

    private final Label usernameLabel;
    private final Label scoreLabel;

    private final TextButton LogOutButton;
    private final TextButton ContinueSavedButton;

    public Table table;
    private final MainMenuController controller;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.settingsButton = new TextButton("SETTING", skin);
        this.profileButton = new TextButton("PROFILE", skin);
        this.preGameMenuButton = new TextButton("PRE GAME MENU", skin);
        this.scoreBoardButton = new TextButton("SCOREBOARD", skin);
        this.hintMenuButton = new TextButton("HINT MENU", skin);

        this.usernameLabel = new Label(App.getCurrentUser().getUsername(), skin);
        this.scoreLabel = new Label("Your Score: " + "100", skin);

        this.LogOutButton = new TextButton("LOGOUT", skin);
        this.ContinueSavedButton = new TextButton("SAVED GAME", skin);

        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        Table leftTable = new Table();
        leftTable.left().center().pad(150, 0, 100, 0);

        leftTable.add(preGameMenuButton).left().pad(10).row();
        leftTable.add(profileButton).left().pad(10).row();
        leftTable.add(scoreBoardButton).left().pad(10).row();
        leftTable.add(hintMenuButton).left().pad(10).row();
        leftTable.add(settingsButton).left().pad(10).row();

        Texture avatarTexture = new Texture(Gdx.files.internal(App.getCurrentUser().getAvatar()));
        Image avatarImage = new Image(avatarTexture);

        Table rightTable = new Table();
        rightTable.top().center();

        rightTable.add(avatarImage).size(250).pad(10).top().row();
        rightTable.add(usernameLabel).pad(5).row();
        rightTable.add(scoreLabel).pad(5).center().row();
        rightTable.add(ContinueSavedButton).pad(5).row();
        rightTable.add(LogOutButton).pad(5).row();

        table.add(leftTable).expand().left().top().pad(30);
        table.add(rightTable).expand().right().top().pad(200);

        stage.addActor(table);
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

    public TextButton getSettingsButton() {
        return settingsButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getPreGameMenuButton() {
        return preGameMenuButton;
    }

    public TextButton getScoreBoardButton() {
        return scoreBoardButton;
    }

    public TextButton getHintMenuButton() {
        return hintMenuButton;
    }

    public TextButton getLogOutButton() {
        return LogOutButton;
    }

    public TextButton getContinueSavedButton() {
        return ContinueSavedButton;
    }
}
