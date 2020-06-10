package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.overlays.GigaGalHud;
import com.mygdx.game.overlays.VictoryOverlay;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.ChaseCam;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.LevelLoader;
import com.mygdx.game.util.Utils;

import javax.swing.Renderer;

public class GameplayScreen extends ScreenAdapter {
    public final static String TAG = GameplayScreen.class.getName();

    SpriteBatch spriteBatch;

    ExtendViewport extendViewport;

    Level level;

    ChaseCam chaseCam;

    GigaGalHud gigaGalHud;

    VictoryOverlay victoryOverlay;

    long levelEndOverlayStartTime;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        extendViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        Assets.instance.init();
        gigaGalHud = new GigaGalHud();
        victoryOverlay = new VictoryOverlay();

        //level = new Level(extendViewport);
        level = LevelLoader.load("Level1", extendViewport);
        chaseCam = new ChaseCam(extendViewport.getCamera(), level.gigaGal);

        levelEndOverlayStartTime = 0;
    }

    @Override
    public void resize(int width, int height) {
        extendViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        Assets.instance.dispose();
        spriteBatch.dispose();
    }

    @Override
    public void render(float delta) {
        level.update(delta);
        chaseCam.update(delta);

        extendViewport.apply();
        //set background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, Constants.BACKGROUND_COLOR.a);

        spriteBatch.setProjectionMatrix(extendViewport.getCamera().combined);

        level.render(spriteBatch);

        gigaGalHud.render(spriteBatch);

        renderLevelEndOverlays(spriteBatch);
    }

    private void renderLevelEndOverlays(SpriteBatch batch) {

        if (level.victory) {
            if (levelEndOverlayStartTime == 0) {
                levelEndOverlayStartTime = TimeUtils.nanoTime();

                victoryOverlay.init();
            }

            victoryOverlay.render(batch);

            if (Utils.secondsSince(levelEndOverlayStartTime) > Constants.LEVEL_END_DURATION) {
                levelEndOverlayStartTime = 0;
                levelComplete();
            }
        }
    }
    private void startNewLevel() {

        level = LevelLoader.load("level1",extendViewport);

//        String levelName = Constants.LEVELS[MathUtils.random(Constants.LEVELS.length - 1)];
//        level = LevelLoader.load(levelName);

        chaseCam.setCamera(level.viewport.getCamera());
        chaseCam.setTarget(level.gigaGal);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void levelComplete() {
        startNewLevel();
    }
}
