package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Models.Result;
import com.TillDawn.Models.User;
import com.TillDawn.Views.MainMenuView;
import com.TillDawn.Views.ProfileMenuView;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import javax.swing.*;

public class ProfileMenuController {
    private ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleButtons() {
        if(view != null){
            if(view.getUpdateButton().isChecked()){
                Result result = update(view.getUsernameField().getText(), view.getPasswordField().getText());
                view.setMessage(result.getMessage());
                view.getUpdateButton().setChecked(false);
            }
            if(view.getMainMenuButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getChooseFileButton().isChecked()){
//                if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
//                    new Thread(() -> {
//                        java.awt.FileDialog dialog = new java.awt.FileDialog((java.awt.Frame) null, "انتخاب عکس آواتار");
//                        dialog.setMode(java.awt.FileDialog.LOAD);
//                        dialog.setVisible(true);
//
//                        if (dialog.getFile() != null) {
//                            String filePath = dialog.getDirectory() + dialog.getFile();
//
//                            // حتماً دستورات libGDX در thread خودش اجرا بشه
//                            Gdx.app.postRunnable(() -> {
//                                App.getCurrentUser().setAvatar(filePath);
//                                // اگر نیازه، تصویر جدید رو دوباره load کن و توی UI نشون بده
//                            });
//                        }
//                    }).start();
//                }
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
