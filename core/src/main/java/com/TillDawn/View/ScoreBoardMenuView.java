package com.TillDawn.View;

import com.TillDawn.Controller.ScoreBoardMenuController;
import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ScoreBoardMenuView implements Screen {

    private Stage stage;
    private Table table;
    private final ScoreBoardMenuController controller;

    private final Label scoreBoard;

    private final TextButton mainMenuButton;

    private final Table userTable = new Table();
    private final Table scoreTable = new Table();
    private final Table killsTable = new Table();
    private final Table maxTimeAlivesTable = new Table();

    private final Label[] userLabels = new Label[10];
    private final Label[] scores = new Label[10];
    private final Label[] kills = new Label[10];
    private final Label[] maxTimeAlive = new Label[10];

    private final Label userButton;
    private final Label scoreButton;
    private final Label killButton;
    private final Label maxTimeAliveButton;


    public ScoreBoardMenuView(ScoreBoardMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();

        this.scoreBoard = new Label("ScoreBoard", skin);
        this.mainMenuButton = new TextButton("<-", skin);

        for (int i = 0; i < 10; i ++){
            userLabels[i] = new Label("", skin);
            scores[i] = new Label("", skin);
            kills[i] = new Label("", skin);
            maxTimeAlive[i] = new Label("", skin);
        }

        this.userButton = new Label("User", skin);
        userButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.sortUsers(0);
                updateScoreBoard();
            }
        });
        this.scoreButton = new Label("Score", skin);
        scoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.sortUsers(1);
                updateScoreBoard();
            }
        });
        this.killButton = new Label("Kill", skin);
        killButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.sortUsers(2);
                updateScoreBoard();
            }
        });
        this.maxTimeAliveButton = new Label("Max Time Alive", skin);
        maxTimeAliveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.sortUsers(3);
                updateScoreBoard();
            }
        });

        controller.setView(this);
    }

    @Override
    public void show() {
        App.sortUsers(1);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        Table listTable = new Table();

        int padB = 10;

        userTable.add(userButton).padRight(padB).padTop(padB).row();
        scoreTable.add(scoreButton).padRight(padB).padTop(padB).row();
        killsTable.add(killButton).padRight(padB).padTop(padB).row();
        maxTimeAlivesTable.add(maxTimeAliveButton).padRight(padB).padTop(padB).row();

        for (int i = 0; i < Math.min(10, App.users.size()); i++) {
            userLabels[i].setText(App.users.get(i).getUsername());
            scores[i].setText(App.users.get(i).getScore());
            kills[i].setText(App.users.get(i).getKills());
            maxTimeAlive[i].setText(App.users.get(i).getMaxTimeAlive());

            if (i == 0) userLabels[i].setColor(Color.GOLD);
            else if (i == 1) userLabels[i].setColor(Color.LIGHT_GRAY);
            else if (i == 2) userLabels[i].setColor(new Color(0.8f, 0.5f, 0.2f, 1));

            if(App.users.get(i).equals(App.getCurrentUser())) {
                userLabels[i].setText("*" + App.users.get(i).getUsername() + "*");
            }

            int padH = 10;

            userTable.add(userLabels[i]).padRight(padH).padTop(padH).row();
            scoreTable.add(scores[i]).padRight(padH).padTop(padH).row();
            killsTable.add(kills[i]).padRight(padH).padTop(padH).row();
            maxTimeAlivesTable.add(maxTimeAlive[i]).padRight(padH).padTop(padH).row();
        }

        int padV = 100;

        listTable.add(userTable).pad(padV);
        listTable.add(scoreTable).pad(padV);
        listTable.add(killsTable).pad(padV);
        listTable.add(maxTimeAlivesTable).pad(padV);

        table.add(scoreBoard).row();
        table.add(listTable);

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

    public void updateScoreBoard() {
        for (int i = 0; i < Math.min(10, App.users.size()); i++) {
            userLabels[i].setText(App.users.get(i).getUsername());
            scores[i].setText(App.users.get(i).getScore());
            kills[i].setText(App.users.get(i).getKills());
            maxTimeAlive[i].setText(App.users.get(i).getMaxTimeAlive());
        }
    }

    public TextButton getMainMenuButton() {
        return mainMenuButton;
    }
}
