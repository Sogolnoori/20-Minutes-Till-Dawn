package com.TillDawn.Model;

import com.badlogic.gdx.audio.Music;

import java.util.*;

public class App {
    public static final ArrayList<User> users = new ArrayList<>();
    private static User currentUser = null;

    private static Music music = GameAssetManager.getGameAssetManager().getMusics().get(0);
    private static int musicIndex = 0;

    static {
        if(music != null){
            music.play();
        }
    }

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

    public static void nextMusic(){
        music.stop();
        musicIndex = (musicIndex + 1) % GameAssetManager.getGameAssetManager().getMusicNumber();
        music = GameAssetManager.getGameAssetManager().getMusics().get(musicIndex);
        music.play();
    }

    public static Music getMusic() {
        return music;
    }

    public static void sortUsers(int type) {
        // username, score, kill, timeAlive
        switch (type) {
            case 0: // Username
                users.sort(Comparator.comparing(User::getUsername));
                break;
            case 1: // Score
                users.sort(Comparator.comparingInt(User::getScore).reversed());
                break;
            case 2: // Kills
                users.sort(Comparator.comparingInt(User::getKills).reversed());
                break;
            case 3: // MaxTimeAlive
                users.sort(Comparator.comparingInt(User::getMaxTimeAlive).reversed());

        }
    }
}
