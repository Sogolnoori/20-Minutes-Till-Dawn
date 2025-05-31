package com.TillDawn.View;

import com.TillDawn.Controller.RegisterMenuController;
import com.TillDawn.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RegisterMenuView implements Screen {

    private Stage stage;
    private Table table;

    private Label message;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextField securityQuestionField;
    private final TextButton registerButton;

    private final TextButton loginMenuButton;


    private final RegisterMenuController controller;

    public RegisterMenuView(RegisterMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();

        this.message = new Label("", skin);
        this.usernameField = new TextField("Sogol", skin);
        this.usernameField.setMessageText("Username");
        this.passwordField = new TextField("Sogol2005)", skin);
        this.passwordField.setMessageText("Password");
        this.securityQuestionField = new TextField("avdacdavra", skin);
        this.securityQuestionField.setMessageText("What's your favorite spell?");
        this.registerButton = new TextButton("Register", skin);
        this.loginMenuButton = new TextButton("Login Menu", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(usernameField).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(passwordField).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(securityQuestionField).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(message);
        table.row().pad(10, 0, 10, 0);
        table.add(registerButton);
        table.row().pad(10, 0, 10, 0);
        stage.addActor(table);
        stage.addActor(loginMenuButton);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleRegisterMenuButtons();
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

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getSecurityQuestionField() {
        return securityQuestionField;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getLoginMenuButton() {
        return loginMenuButton;
    }
}
