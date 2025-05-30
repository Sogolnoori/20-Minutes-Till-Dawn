package com.TillDawn.View;

import com.TillDawn.Controller.ProfileMenuController;
import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ProfileMenuView implements Screen {

    private Stage stage;
    private final Table table;
    private final Table avatarOptions;
    private final ProfileMenuController controller;

    private final TextField usernameField;
    private final TextField passwordField;
    private final TextButton updateButton;
    private final TextButton mainMenuButton;
    private final TextButton chooseFileButton;
    private final TextButton deleteAccountButton;
    private final Label message;

    private final Image avatarImage;
    private final TextureRegionDrawable avatarDrawable;
    private final Container<Image> avatarContainer;



    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();

        this.usernameField = new TextField(App.getCurrentUser().getUsername(), skin);
        this.passwordField = new TextField(App.getCurrentUser().getPassword(), skin);
        this.updateButton = new TextButton("UPDATE", skin);
        this.mainMenuButton = new TextButton("<-", skin);
        this.deleteAccountButton = new TextButton("DELETE", skin);
        this.message = new Label("", skin);

        this.chooseFileButton = new TextButton("CHOOSE FILE", skin);

        Texture initialTexture = new Texture(Gdx.files.internal(App.getCurrentUser().getAvatar()));
        this.avatarDrawable = new TextureRegionDrawable(new TextureRegion(initialTexture));
        this.avatarImage = new Image(avatarDrawable);
        this.avatarContainer = new Container<>(avatarImage);


        this.avatarOptions = new Table();
        this.avatarOptions.center();

        for (int i = 0; i < App.avatarPaths.size(); i++) {
            final int index = i;
            Texture texture = new Texture(Gdx.files.internal(App.avatarPaths.get(i)));
            Image image = new Image(texture);

            image.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    App.getCurrentUser().setAvatar(App.avatarPaths.get(index));
                    avatarDrawable.getRegion().getTexture().dispose();
                    Texture newTexture = new Texture(Gdx.files.internal(App.avatarPaths.get(index)));
                    avatarDrawable.setRegion(new TextureRegion(newTexture));
                }
            });

            avatarOptions.add(image).size(64).pad(5);
        }


        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.top();

        Table leftTable = new Table();
        leftTable.left();

        leftTable.add(usernameField).width(500).pad(30).row();
        leftTable.add(passwordField).width(500).pad(30).row();
        leftTable.add(updateButton).pad(30).row();
        leftTable.add(message).row();

        Table rightTable = new Table();
        rightTable.top().center();

        rightTable.add(avatarContainer).top().center().size(128).pad(10).row();

        rightTable.add(avatarOptions).top().center().row();

        rightTable.add(chooseFileButton).pad(10).row();
        rightTable.add(deleteAccountButton).pad(10).row();

        table.add(leftTable).expand().left().pad(30);
        table.add(rightTable).expand().right().padTop(150).padRight(200).top();


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

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getUpdateButton() {
        return updateButton;
    }

    public TextButton getMainMenuButton() {
        return mainMenuButton;
    }

    public TextButton getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public TextButton getChooseFileButton() {
        return chooseFileButton;
    }

    public Image getAvatarImage() {
        return avatarImage;
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }
}
