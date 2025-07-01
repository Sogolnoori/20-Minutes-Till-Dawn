package com.TillDawn.View;

import com.TillDawn.Controller.SettingMenuController;
import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.Controls;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingMenuView implements Screen {

    private Stage stage;
    private final Table table;
    private final SettingMenuController controller;

    private final TextButton nextMusic;
    private final Slider volumeSlider;
    private final TextButton sfxButton;
    private final TextButton autoReloadButton;
    private final TextButton mainMenuButton;

    private final TextButton upKeyButton;
    private final TextButton downKeyButton;
    private final TextButton leftKeyButton;
    private final TextButton rightKeyButton;

    public SettingMenuView(SettingMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        controller.setView(this);

        this.nextMusic = new TextButton("Next Music", skin);
        this.volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        this.sfxButton = new TextButton("SFX : " + (App.hasSfx() ? "ON" : "OFF"), skin);
        sfxButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean newState = !App.hasSfx();
                App.setSfx(newState);
                sfxButton.setText("SFX : " + (newState ? "ON" : "OFF"));
            }
        });
        this.autoReloadButton = new TextButton("AUTO-RELOAD : " + (App.getCurrentGame() == null || App.getCurrentGame().getWeapon().isAutoReload() ? "ON" : "OFF"), skin);
        autoReloadButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(App.getCurrentGame() != null){
                    boolean newState = !App.getCurrentGame().getWeapon().isAutoReload();
                    App.getCurrentGame().getWeapon().setAutoReload(newState);
                    autoReloadButton.setText("AUTO-RELOAD : " + (newState ? "ON" : "OFF"));
                }
            }
        });
        volumeSlider.setValue(1f);
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                App.getMusic().setVolume(volumeSlider.getValue());
            }
        });

        this.upKeyButton = new TextButton("Up: " + Input.Keys.toString(Controls.get("up")), skin);
        this.downKeyButton = new TextButton("Down: " + Input.Keys.toString(Controls.get("down")), skin);
        this.leftKeyButton = new TextButton("Left: " + Input.Keys.toString(Controls.get("left")), skin);
        this.rightKeyButton = new TextButton("Right: " + Input.Keys.toString(Controls.get("right")), skin);

        setUpControllers(skin);


        this.mainMenuButton = new TextButton("<-", skin);
    }

    public void setUpControllers(Skin skin) {
        upKeyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        if(!Controls.inUse(keycode)) {
                            Controls.set("up", keycode);
                            upKeyButton.setText("Up: " + Input.Keys.toString(keycode));
                            Gdx.input.setInputProcessor(stage);
                        }
                        return true;
                    }
                });
            }
        });

        downKeyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        if(!Controls.inUse(keycode)) {
                            Controls.set("down", keycode);
                            downKeyButton.setText("Down: " + Input.Keys.toString(keycode));
                            Gdx.input.setInputProcessor(stage);
                        }
                        return true;
                    }
                });
            }
        });

        rightKeyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        if(!Controls.inUse(keycode)) {
                            Controls.set("right", keycode);
                            rightKeyButton.setText("Right: " + Input.Keys.toString(keycode));
                            Gdx.input.setInputProcessor(stage);
                        }
                        return true;
                    }
                });
            }
        });

        leftKeyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        if(!Controls.inUse(keycode)) {
                            Controls.set("left", keycode);
                            leftKeyButton.setText("Left: " + Input.Keys.toString(keycode));
                            Gdx.input.setInputProcessor(stage);
                        }
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0, 10, 0);

        Table leftTable = new Table();
        leftTable.add(nextMusic).row();
        leftTable.add(volumeSlider).width(400).pad(10).row();
        leftTable.add(sfxButton).row();
        leftTable.add(autoReloadButton).row();

        Table rightTable = new Table();

        rightTable.add(upKeyButton).padLeft(300).row();
        rightTable.add(leftKeyButton).padLeft(50);
        rightTable.add(rightKeyButton).padLeft(-100).row();
        rightTable.add(downKeyButton).padLeft(300).row();

        table.add(leftTable).left().padRight(100);
        table.add(rightTable).right();

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

    public TextButton getSfxButton() {
        return sfxButton;
    }
}
