package com.TillDawn.View;

import com.TillDawn.Controller.HintMenuController;
import com.TillDawn.Model.Controls;
import com.TillDawn.Model.Enum.Hero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HintMenuView implements Screen {

    private Stage stage;
    private Table rootTable, pageContainer;
    private final HintMenuController controller;

    private final TextButton previousButton;
    private final TextButton nextButton;
    private final TextButton mainButton;

    private final Table[] pages;
    private int currentPage = 0;

    public HintMenuView(HintMenuController controller, Skin skin) {
        this.controller = controller;

        previousButton = new TextButton("<-", skin);
        nextButton = new TextButton("->", skin);
        mainButton = new TextButton("Main", skin);

        pages = new Table[4];

        pages[0] = buildHeroesPage(skin);
        pages[1] = buildKeyBindingsPage(skin);
        pages[2] = buildCheatCodesPage(skin);
        pages[3] = buildAbilitiesPage(skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        pageContainer = new Table();
        rootTable.add(pageContainer).expand().row();

        for (Table page : pages) {
            page.setVisible(false);
            pageContainer.addActor(page);
        }
        pages[0].setVisible(true);

        Table navTable = new Table();
        navTable.add(mainButton);
        navTable.add(previousButton).pad(10);
        navTable.add(nextButton).pad(10);
        rootTable.add(navTable).bottom().padBottom(20);
    }

    public void showPage(int index) {
        pages[currentPage].setVisible(false);
        currentPage = index;
        pages[currentPage].setVisible(true);
    }

    private Table buildHeroesPage(Skin skin) {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        for (Hero hero : Hero.values()) {
            table.add(new Label(hero.toString() + ":", skin)).left().padTop(25).row();
            table.add(new Label("Hp: " + hero.getHp(), skin)).left().padTop(10);
            table.add(new Label("Speed: " + hero.getSpeed(), skin)).left().row();

        }
        return table;
    }

    private Table buildKeyBindingsPage(Skin skin) {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(new TextButton("Up: " + Input.Keys.toString(Controls.get("up")), skin)).padBottom(10).row();

        Table midRow = new Table();
        midRow.add(new TextButton("Left: " + Input.Keys.toString(Controls.get("left")), skin)).padRight(30);
        midRow.add(new TextButton("Right: " + Input.Keys.toString(Controls.get("right")), skin)).padLeft(30);
        table.add(midRow).padBottom(10).row();

        table.add(new TextButton("Down: " + Input.Keys.toString(Controls.get("down")), skin)).padBottom(10).row();
        return table;
    }

    private Table buildCheatCodesPage(Skin skin) {
        Table table = new Table();
        table.add(new Label("CHEAT CODES:", skin)).padBottom(30).row();
        table.add(new Label("Reduce Time: T", skin)).row();
        table.add(new Label("Level Up: L", skin)).row();
        table.add(new Label("More Hearts: H", skin)).row();
        table.add(new Label("Boss Fight: B", skin)).row();
        table.add(new Label("Add Kills: K", skin)).row();
        return table;
    }

    private Table buildAbilitiesPage(Skin skin) {
        Table table = new Table();
        table.add(new Label("ABILITIES:", skin)).padBottom(30).row();
        table.add(new Label("VITALITY:\nIncrease max HP by 1 unit", skin)).padBottom(20).left().row();
        table.add(new Label("DAMAGER:\nBoost weapon damage by 25% for 10 seconds", skin)).padBottom(20).left().row();
        table.add(new Label("PROCREASE:\nIncrease projectile count by 1", skin)).padBottom(20).left().row();
        table.add(new Label("AMOCREASE:\nIncrease max ammo by 5", skin)).padBottom(20).left().row();
        table.add(new Label("SPEEDY:\nDouble movement speed for 10 seconds", skin)).padBottom(20).left().row();
        return table;
    }

    @Override public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
        controller.handleButtons();
    }

    @Override
    public void resize(int width, int height) {

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

    public TextButton getPreviousButton() {
        return previousButton;
    }

    public TextButton getNextButton() {
        return nextButton;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public TextButton getMainButton() {
        return mainButton;
    }
}
