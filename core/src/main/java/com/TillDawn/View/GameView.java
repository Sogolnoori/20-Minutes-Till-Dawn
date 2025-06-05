package com.TillDawn.View;

import com.TillDawn.Controller.GameController;
import com.TillDawn.Main;
import com.TillDawn.Model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameView implements Screen,  InputProcessor {

    private final GameController controller;

    private final OrthographicCamera camera;
    private final OrthographicCamera uiCamera;
    private Viewport viewport;

    private final BitmapFont font;
    private final ShapeRenderer shapeRenderer;
    private final Sprite skullSprite;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        this.camera = new OrthographicCamera((float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
        this.uiCamera = new OrthographicCamera();
        this.uiCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.font = new BitmapFont();
        this.shapeRenderer = new ShapeRenderer();
        this.skullSprite = new Sprite(new Texture("Skull.png"));
        this.skullSprite.setPosition(Gdx.graphics.getWidth() - 110, Gdx.graphics.getHeight() - 157);
        this.skullSprite.setSize(27, 35);

        controller.setView(this, camera);
    }

    @Override
    public void show() {
        viewport = new FitViewport(1600, 960, camera);
        viewport.apply();
        camera.position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);
        camera.update();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float v) {
        App.getCurrentGame().updateTimeSpent(v);

        ScreenUtils.clear(0, 0, 0, 1);

        Game game = App.getCurrentGame();
        Player player = game.getPlayer();
        camera.position.set(
            player.getPosX() + player.getPlayerSprite().getWidth() / 2.0F,
            player.getPosY() + player.getPlayerSprite().getHeight() / 2.0F, 0.0F);
        camera.update();

        Main.getBatch().setProjectionMatrix(camera.combined);

        Main.getBatch().begin();

        game.getBackgroundSprite().draw(Main.getBatch());

        controller.updateGame();

        game.getWeapon().getSmgSprite().draw(Main.getBatch());
        player.getPlayerSprite().draw(Main.getBatch());
        for (Monster monster : game.getMonsters()) {
            monster.getMonsterSprite().draw(Main.getBatch());
        }

        for(Projectile monsterShot : game.getMonsterShots()){
            monsterShot.getSprite().draw(Main.getBatch());
        }

        for(Projectile droplet : game.getDroplets()){
            droplet.getSprite().draw(Main.getBatch());
        }

        Main.getBatch().end();


        float progress = (float) player.getXp() / player.getXpNeeded();
        progress = MathUtils.clamp(progress, 0f, 1f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(10, Gdx.graphics.getHeight() - 40, Gdx.graphics.getWidth() - 20, 30);

        shapeRenderer.setColor(Color.valueOf("4A6274"));
        shapeRenderer.rect(10, Gdx.graphics.getHeight() - 40, (Gdx.graphics.getWidth() - 20) * progress, 30);

        shapeRenderer.end();


        Main.getBatch().setProjectionMatrix(uiCamera.combined);
        Main.getBatch().begin();

        for (Heart heart : game.getHearts()) {
            heart.getHeartSprite().draw(Main.getBatch());
        }

        game.getAmmoCounter().getAmmoSprite().draw(Main.getBatch());
        game.getAmmoCounter().getCurrentAmmoSprite().draw(Main.getBatch());
        game.getAmmoCounter().getBackslashSprite().draw(Main.getBatch());
        game.getAmmoCounter().getTotalAmmoSprite().draw(Main.getBatch());

        skullSprite.draw(Main.getBatch());

        String timeText = String.format("%02d:%02d", (int)game.getLeftTime() / 60, (int)game.getLeftTime() % 60);
        font.getData().setScale(4f);
        font.draw(Main.getBatch(), timeText, Gdx.graphics.getWidth() - 160, Gdx.graphics.getHeight() - 60);

        String levelTex = String.format("LEVEL %d", game.getPlayer().getLevel());
        font.getData().setScale(2f);
        font.draw(Main.getBatch(), levelTex, (int)(Gdx.graphics.getWidth() / 2) - 50, Gdx.graphics.getHeight() - 15);

        String killTex = String.format("x%d", game.getPlayer().getKills());
        font.getData().setScale(2f);
        font.draw(Main.getBatch(), killTex, Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 130);


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
