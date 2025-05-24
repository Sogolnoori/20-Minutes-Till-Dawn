package com.TillDawn.Controllers;

import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Models.Result;
import com.TillDawn.Models.User;
import com.TillDawn.Views.*;

public class RegisterMenuController {
    private RegisterMenuView view;

    public void setView(RegisterMenuView view) {
        this.view = view;
    }

    public void handleRegisterMenuButtons() {
        if(view != null){
            if(view.getRegisterButton().isChecked()){
                Result result = register(view.getUsernameField().getText(), view.getPasswordField().getText(), view.getSecurityQuestionField().getText());
                if(!result.getResult()){
                    view.setMessage(result.getMessage());
                    view.getRegisterButton().setChecked(false);
                    return;
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(view.getLoginMenuButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    public Result register(String username, String password, String securityQuestion) {
        if(getUser(username) != null){
            return new Result(false, "User already exists");
        }
        if(!(password.matches("(?=.*[@#$%&*)(_].*)(?=.*[A-Z].*)(?=.*[0-9].*).*") && password.length() >= 8)){
            return new Result(false, "Weak password");
        }
        App.users.add(new User(username, password, securityQuestion));
        return new Result(true, "Successfully registered");
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
