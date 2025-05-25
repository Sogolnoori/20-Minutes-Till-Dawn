package com.TillDawn.Views;

import com.TillDawn.Controllers.PreGameMenuController;
import com.TillDawn.Main;
import com.TillDawn.Models.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PreGameMenuView implements Screen {

    private Stage stage;
    private final Label gameTitle;
    private final TextButton playButton;
    private final SelectBox<String> selectHero;
    private final SelectBox<String> selectTime;
    private final SelectBox<String> selectWeapon;
    public Table table;
    private final PreGameMenuController controller;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.gameTitle = new Label("PreGame Menu", skin);
        this.selectHero = new SelectBox<>(skin);
        this.selectWeapon = new SelectBox<>(skin);
        this.selectTime = new SelectBox<>(skin);
        this.playButton = new TextButton("Play", skin);
        this.table = new Table();
        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);


        Table topTable = new Table();

        selectHero.setItems(GameAssetManager.getGameAssetManager().getHeroes().toArray(new String[0]));

        Array<String> weapons = new Array<>();
        weapons.add("REVOLVER");
        weapons.add("SHOTGUN");
        weapons.add("SⅯG");
        selectWeapon.setItems(weapons);

        Array<String> times = new Array<>();
        times.add("2 minutes");
        times.add("5 minutes");
        times.add("10 minutes");
        times.add("20 minutes");
        selectTime.setItems(times);

        Table leftTable = new Table();
        leftTable.add(selectHero).pad(50).row();

        Table middleTable = new Table();
        middleTable.add(selectWeapon).pad(50).row();

        Table rightTable = new Table();
        rightTable.add(selectTime).pad(50).row();

        topTable.add(leftTable).expand().left().pad(10);
        topTable.add(middleTable).expand().center().pad(10);
        topTable.add(rightTable).expand().right().pad(10);

        Table bottomTable = new Table();

        bottomTable.add(playButton);

        table.add(topTable).top().row();
        table.add(bottomTable).bottom();

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handlePreGameMenuButtons();
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

    public TextButton getPlayButton() {
        return playButton;
    }

    public SelectBox<String> getSelectHero() {
        return selectHero;
    }

    public SelectBox<String> getSelectTime() {
        return selectTime;
    }

    public SelectBox<String> getSelectWeapon() {
        return selectWeapon;
    }
}
