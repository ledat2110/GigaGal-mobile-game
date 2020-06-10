package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Utils;

import java.sql.Time;

public class ExitPortal {

    public final static String TAG = ExitPortal.class.getName();

    public Vector2 position;

    long startTime;

    public ExitPortal(Vector2 position) {
        this.position = position;
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch) {
        float elapsedTime = Utils.secondsSince(startTime);

        TextureRegion frame = (TextureRegion) Assets.instance.exitPortalAssets.exitPortal.getKeyFrame(elapsedTime, true);

        Utils.drawTextureRegion(batch, frame, position, Constants.EXIT_PORTAL_CENTER);
    }
}