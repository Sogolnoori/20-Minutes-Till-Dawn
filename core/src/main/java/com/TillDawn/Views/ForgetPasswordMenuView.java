package com.TillDawn.Views;

import com.TillDawn.Controllers.ForgetPasswordMenuController;
import com.TillDawn.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ForgetPasswordMenuView implements Screen {

    private Stage stage;
    private Table table;

    private Label message;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextField securityQuestionField;
    private final TextButton changePasswordButton;
    private final TextButton loginButton;

    private final ForgetPasswordMenuController controller;

    public ForgetPasswordMenuView(ForgetPasswordMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.message = new Label("", skin);
        this.usernameField = new TextField("", skin);
        this.usernameField.setMessageText("Username");
        this.passwordField = new TextField("", skin);
        this.passwordField.setMessageText("New Password");
        this.securityQuestionField = new TextField("", skin);
        this.securityQuestionField.setMessageText("Your favorite spell?");
        this.changePasswordButton = new TextButton("Confirm", skin);
        this.loginButton = new TextButton("LoginMenu", skin);
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
        table.add(changePasswordButton);
        table.row().pad(10, 0, 10, 0);
        stage.addActor(table);
        stage.addActor(loginButton);
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

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getSecurityQuestionField() {
        return securityQuestionField;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }
}
