package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Utils;

public class Powerup {

    Vector2 position;
    boolean active;


    public Powerup(Vector2 position) {
        this.position = position;
        active = true;
    }

    public void render(SpriteBatch batch) {
        Utils.drawTextureRegion(batch, Assets.instance.powerupAssets.powerUp, position, Constants.POWERUP_CENTER);
    }

    public boolean isHit(Rectangle boundingRect){
        Rectangle boundingPowerup = new Rectangle(this.position.x - Constants.POWERUP_CENTER.x, this.position.y - Constants.POWERUP_CENTER.y,
                Assets.instance.powerupAssets.powerUp.getRegionWidth(), Assets.instance.powerupAssets.powerUp.getRegionHeight());
        if (boundingRect.overlaps(boundingPowerup)) {
            active = false;
            return true;
        }
        return false;
    }


    public boolean isActive() {
        return active;
    }
}
