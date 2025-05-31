package com.TillDawn.Controller;

import com.TillDawn.Main;
import com.TillDawn.Model.App;
import com.TillDawn.Model.GameAssetManager;
import com.TillDawn.Model.Result;
import com.TillDawn.Model.User;
import com.TillDawn.View.ForgetPasswordMenuView;
import com.TillDawn.View.LoginMenuView;

public class ForgetPasswordMenuController {
    private ForgetPasswordMenuView view;

    public void setView(ForgetPasswordMenuView view) {
        this.view = view;
    }

    public void handleButtons(){
        if(view != null){
            if(view.getChangePasswordButton().isChecked()) {
                Result result = recoverPassword(view.getUsernameField().getText(), view.getSecurityQuestionField().getText(), view.getPasswordField().getText());
                if(!result.getResult()){
                    view.setMessage(result.getMessage());
                    view.getChangePasswordButton().setChecked(false);
                }
                else{
                    view.setMessage("Congratulations! Your password successfully changed!");
                }
            }
            if(view.getLoginButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    public Result recoverPassword(String username, String securityQuestion, String newPassword) {
        User user = getUser(username);
        if(user == null){
            return new Result(false, "User not found");
        }
        if(!securityQuestion.equals(user.getFavoriteSpell())){
            return new Result(false, "Nice try, impostor.");
        }
        if(!(newPassword.matches("(?=.*[@#$%&*)(_].*)(?=.*[A-Z].*)(?=.*[0-9].*).*") && newPassword.length() >= 8)){
            return new Result(false, "Weak password");
        }
        user.setPassword(newPassword);
        return new Result(true, user.getPassword());
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
