package com.TillDawn.View;

import com.TillDawn.Controller.PauseMenuController;
import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.Enum.Ability;
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

    private final Label cheatLabel;
    private final Label cheatCode1;
    private final Label cheatCode2;
    private final Label cheatCode3;
    private final Label cheatCode4;
    private final Label cheatCode5;

    private final Label abilityLabel;
    private final Label vitality;
    private final Label damager;
    private final Label procrease;
    private final Label amocrease;
    private final Label speedy;

    public PauseMenuView(PauseMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();

        this.resumeButton = new TextButton("Resume", skin);
        this.giveUpButton = new TextButton("Give Up:(", skin);
        this.saveButton = new TextButton("Save and Exit", skin);

        this.cheatLabel = new Label("CHEAT CODES:", skin);
        this.cheatCode1 = new Label("Reduce Time : T", skin);
        this.cheatCode2 = new Label("Level Up : L", skin);
        this.cheatCode3 = new Label("More Hearts : H", skin);
        this.cheatCode4 = new Label("Boss Fight : B", skin);
        this.cheatCode5 = new Label("Add Kills : K", skin);

        this.abilityLabel = new Label("ABILITIES:", skin);
        this.vitality = new Label("Vitality", skin);
        this.damager = new Label("Damager", skin);
        this.procrease = new Label("Procrease", skin);
        this.amocrease = new Label("Amocrese", skin);
        this.speedy = new Label("Speedy", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        Table rightTable = new Table();
        rightTable.row().pad(10, 0, 10, 0);
        rightTable.add(resumeButton).row();
        rightTable.add(giveUpButton).row();
        rightTable.add(saveButton).row();

        Table leftTable = new Table();

        Table cheatTable = new Table();
        cheatTable.row().pad(10, 0, 10, 0);
        cheatTable.add(cheatLabel).row();
        cheatTable.add(cheatCode1).row();
        cheatTable.add(cheatCode1).row();
        cheatTable.add(cheatCode2).row();
        cheatTable.add(cheatCode3).row();
        cheatTable.add(cheatCode4).row();
        cheatTable.add(cheatCode5).row();

        Table abilityTable = new Table();

        abilityTable.row().pad(10, 0, 10, 0);
        abilityTable.add(abilityLabel).padBottom(30).row();
        if(App.getCurrentGame().getAbilities().containsKey(Ability.Vitality)){
            abilityTable.add(vitality).row();
        }
        if(App.getCurrentGame().getAbilities().containsKey(Ability.Damager)){
            abilityTable.add(damager).row();
        }
        if(App.getCurrentGame().getAbilities().containsKey(Ability.Procrease)){
            abilityTable.add(procrease).row();
        }
        if(App.getCurrentGame().getAbilities().containsKey(Ability.Amocrease)){
            abilityTable.add(amocrease).row();
        }
        if(App.getCurrentGame().getAbilities().containsKey(Ability.Speedy)){
            abilityTable.add(speedy).row();
        }

        leftTable.row().pad(10, 0, 10, 0);
        leftTable.add(abilityTable).pad(0, 0, 125, 0).top().row();
        leftTable.add(cheatTable).row();

        table.add(leftTable).left().padRight(300);
        table.add(rightTable).right();

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
