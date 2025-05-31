package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.Model.Result;
import com.TillDawn.Model.User;
import com.TillDawn.View.ForgetPasswordMenuView;
import com.TillDawn.View.LoginMenuView;
import com.TillDawn.View.MainMenuView;
import com.TillDawn.View.RegisterMenuView;


public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleButtons(){
        if(view != null){
            if(view.getLoginButton().isChecked()){
                Result result = login(view.getUsernameField().getText(), view.getPasswordField().getText());
                if(!result.getResult()){
                    view.setMessage(result.getMessage());
                    view.getLoginButton().setChecked(false);
                    return;
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getForgetPasswordButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ForgetPasswordMenuView(new ForgetPasswordMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getStartAsGuestButton().isChecked()){
                Main.getMain().getScreen().dispose();
                setGuestUser();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getRegisterMenuButton().isChecked()){
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    public void setGuestUser(){
        User user = new User("Guest", "password", "expeliarGuest");
        App.setCurrentUser(user);
    }

    public Result login(String username, String password){
        User user = getUser(username);
        if(user == null){
            return new Result(false, "User not found");
        }
        if(!user.getPassword().equals(password)){
            return new Result(false, "Wrong password");
        }
        App.setCurrentUser(user);
        return new Result(true, "Login successful");
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
