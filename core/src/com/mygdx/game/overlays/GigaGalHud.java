package com.mygdx.game.overlays;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.util.Constants;

public class GigaGalHud {

    public Viewport viewport;
    BitmapFont font;

    public GigaGalHud() {
        viewport = new ExtendViewport(Constants.HUD_VIEWPORT_SIZE, Constants.HUD_VIEWPORT_SIZE);
        font = new BitmapFont();
    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        font.setColor(Color.YELLOW);
        font.draw(batch,"HELLO", 0, 0);

        batch.end();
    }
}
