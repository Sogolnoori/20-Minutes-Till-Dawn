package com.TillDawn.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SaveManager {
    private static final String SAVE_FILE = "save.json";
    private static final Gson gson = new Gson();


    public static void save(ArrayList<User> users) {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> load() {
        try (FileReader reader = new FileReader(SAVE_FILE)) {
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
