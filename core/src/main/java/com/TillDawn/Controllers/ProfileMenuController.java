package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Models.Result;
import com.TillDawn.Models.User;
import com.TillDawn.Views.MainMenuView;
import com.TillDawn.Views.ProfileMenuView;
import com.TillDawn.Views.RegisterMenuView;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import javax.swing.*;

public class ProfileMenuController {
    private ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;

        view.getChooseFileButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onChooseFileClicked();
            }
        });
    }

    public void onChooseFileClicked() {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            SwingUtilities.invokeLater(() -> {
                java.awt.FileDialog dialog = new java.awt.FileDialog((java.awt.Frame) null, "wooo");
                dialog.setMode(java.awt.FileDialog.LOAD);
                dialog.setVisible(true);

                String selectedFile = dialog.getFile();
                if (selectedFile != null) {
                    String filePath = dialog.getDirectory() + selectedFile;

                    Gdx.app.postRunnable(() -> {
                        App.getCurrentUser().setAvatar(filePath);

                        Texture newTexture = new Texture(Gdx.files.internal(filePath));
                        view.getAvatarImage().setDrawable(new TextureRegionDrawable(new TextureRegion(newTexture)));
                    });
                }
            });
        }

    }

    private void updateAvatarImage(String path) {
        // dispose کردن تکسچر قبلی برای جلوگیری از نشت حافظه
        Drawable drawable = view.getAvatarImage().getDrawable();
        if (drawable instanceof TextureRegionDrawable) {
            ((TextureRegionDrawable) drawable).getRegion().getTexture().dispose();
        }

        Texture newTexture = new Texture(Gdx.files.internal(path));
        view.getAvatarImage().setDrawable(new TextureRegionDrawable(new TextureRegion(newTexture)));
    }

    public void handleButtons() {
        if(view != null){
            if(view.getUpdateButton().isChecked()){
                Result result = update(view.getUsernameField().getText(), view.getPasswordField().getText());
                view.setMessage(result.getMessage());
                view.getUpdateButton().setChecked(false);
            }
            if(view.getDeleteAccountButton().isChecked()){
                App.users.remove(App.getCurrentUser());
                App.setCurrentUser(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getMainMenuButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    public Result update(String username, String password){
        User user = App.getCurrentUser();
        if(!user.getUsername().equals(username)){
            if(getUser(username) != null){
                return new Result(false, "This username is already in use");
            }
        }
        if(!user.getPassword().equals(password)){
            if(!(password.matches("(?=.*[@#$%&*)(_].*)(?=.*[A-Z].*)(?=.*[0-9].*).*") && password.length() >= 8)){
                return new Result(false, "Weak password");
            }
        }
        user.setUsername(username);
        user.setPassword(password);
        return new Result(true, "Successfully updated");
    }

    public User getUser(String username) {
        for (User user : App.users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
