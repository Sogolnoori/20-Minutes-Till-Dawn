package com.TillDawn.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static final ArrayList<User> users = new ArrayList<>();
    private static User currentUser = null;

    public static List<String> avatarPaths = Arrays.asList("avatars/avatar0.png", "avatars/avatar1.png");


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static Game getCurrentGame() {
        return currentUser.getCurrentGame();
    }
}
