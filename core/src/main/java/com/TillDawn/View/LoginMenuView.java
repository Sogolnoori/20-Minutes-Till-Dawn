package com.TillDawn.View;

import com.TillDawn.Controller.LoginMenuController;
import com.TillDawn.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoginMenuView implements Screen {

    private Stage stage;
    private Table table;

    private Label message;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextButton loginButton;
    private final TextButton forgetPasswordButton;
    private final TextButton startAsGuestButton;
    private final TextButton registerMenuButton;

    private final LoginMenuController controller;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();

        this.message = new Label("", skin);
        this.usernameField = new TextField("Sogol", skin);
        this.usernameField.setMessageText("Username");
        this.passwordField = new TextField("Sogol2005)", skin);
        this.passwordField.setMessageText("Password");
        this.loginButton = new TextButton("Login", skin);
        this.forgetPasswordButton = new TextButton("Forget Password", skin);
        this.startAsGuestButton = new TextButton("Start as a Guist", skin);
        this.registerMenuButton = new TextButton("R", skin);
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
        table.add(message);
        table.row().pad(10, 0, 10, 0);
        table.add(loginButton);
        table.row().pad(10, 0, 10, 0);
        table.add(forgetPasswordButton);
        table.row().pad(10, 0, 10, 0);
        table.add(startAsGuestButton);

        stage.addActor(table);
        stage.addActor(registerMenuButton);
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

    public Label getMessage() {
        return message;
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

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getForgetPasswordButton() {
        return forgetPasswordButton;
    }

    public TextButton getStartAsGuestButton() {
        return startAsGuestButton;
    }

    public TextButton getRegisterMenuButton() {
        return registerMenuButton;
    }
}
