package com.TillDawn.Model;

import com.badlogic.gdx.Input;

import java.util.HashMap;

public class Controls {
    private static final HashMap<String, Integer> keys = new HashMap<>();

    static {
        keys.put("up", Input.Keys.W);
        keys.put("down", Input.Keys.S);
        keys.put("left", Input.Keys.A);
        keys.put("right", Input.Keys.D);
    }

    public static int get(String action) {
        return keys.get(action);
    }

    public static void set(String action, int keycode) {
        keys.put(action, keycode);
    }

    public static boolean inUse(Integer keycode) {
        return keys.containsValue(keycode);
    }
}
