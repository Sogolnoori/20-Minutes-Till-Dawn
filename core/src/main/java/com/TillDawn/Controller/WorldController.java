package com.TillDawn.Controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class WorldController {

    private final PlayerController playerController;
    private final Texture backgroundTexture;
    private OrthographicCamera camera;

    public WorldController(PlayerController playerController, OrthographicCamera camera) {
        this.camera = camera;
        this.playerController = playerController;
        this.backgroundTexture = new Texture("background.png");
    }

    public void update() {
    }
}
