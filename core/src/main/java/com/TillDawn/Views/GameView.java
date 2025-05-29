package com.TillDawn.Views;

import com.TillDawn.Controllers.GameController;
import com.TillDawn.Main;
import com.TillDawn.Models.App;
import com.TillDawn.Models.Game;
import com.TillDawn.Models.GameAssetManager;
import com.TillDawn.Models.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameView implements Screen,  InputProcessor {
    private Stage stage;
    private final GameController controller;

    private final Texture backgroundTexture;

    private final OrthographicCamera camera;
    private Viewport viewport;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        this.camera = new OrthographicCamera((float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
        this.backgroundTexture = new Texture("background.png");
        controller.setView(this, camera);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        viewport = new FitViewport(1600, 960, camera);
        viewport.apply();
        camera.position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);
        camera.update();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float v) {
        App.getCurrentGame().reduceTime(v);

        ScreenUtils.clear(0, 0, 0, 1);

        Game game = App.getCurrentGame();
        Player player = game.getPlayer();
        camera.position.set(
            player.getPosX() + player.getPlayerSprite().getWidth() / 2.0F,
            player.getPosY() + player.getPlayerSprite().getHeight() / 2.0F, 0.0F);
        camera.update();

        Main.getBatch().setProjectionMatrix(camera.combined);

        Main.getBatch().begin();

        Main.getBatch().draw(backgroundTexture, 0, 0, backgroundTexture.getWidth() * 0.25f, backgroundTexture.getHeight() * 0.25f);

        controller.updateGame();

        game.getWeapon().getSmgSprite().draw(Main.getBatch());
        player.getPlayerSprite().draw(Main.getBatch());
        game.getMonsters().get(0).getMonsterSprite().draw(Main.getBatch());

//        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        stage.draw();
        Main.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
