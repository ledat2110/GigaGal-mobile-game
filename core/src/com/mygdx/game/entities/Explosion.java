package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Utils;

public class Explosion {

    Vector2 position;
    public float offset;
    long startTime;

    public Explosion(Vector2 position) {
        this.position = position;
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch) {
        float stateTime = Utils.secondsSince(startTime);
        Utils.drawTextureRegion(batch, (TextureRegion) Assets.instance.explosionAssets.explosion.getKeyFrame(stateTime), position, Constants.EXPLOSION_CENTER);
    }

    public boolean yetToStart(){
        return Utils.secondsSince(startTime) - offset < 0;
    }

    public boolean isFinished() {
        float stateTime = Utils.secondsSince(startTime) - offset;
        return Assets.instance.explosionAssets.explosion.isAnimationFinished(stateTime);
    }
}