package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;

public class Platform {
    float left, top, bottom, right;


    public Platform(float left, float top, float width, float height) {
        this.left = left;
        this.top = top;
        this.bottom = top - height;
        this.right = left + width;
    }

    public void render(SpriteBatch batch){
        float width = right - left;
        float height = top - bottom;

        Assets.instance.platFormAssets.ninePatch.draw(batch, left, bottom, width, height);
    }

    public boolean landedOnPlatform(Vector2 lastFramePosition, Vector2 position) {
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;

        if (lastFramePosition.y - Constants.GIGAGAL_EYES_HEIGHT >= this.top && position.y - Constants.GIGAGAL_EYES_HEIGHT < this.top){
            float leftFoot = position.x - Constants.GIGAGAL_STANCE_WIDTH /2;
            float rightFoot = position.x + Constants.GIGAGAL_STANCE_WIDTH /2;

            leftFootIn = (this.left < leftFoot && this.right > leftFoot);
            rightFootIn = (this.left < rightFoot && this.right > rightFoot);

            straddle = (this.left > leftFoot && this.right < rightFoot);
        }

        return leftFootIn || rightFootIn || straddle;
    }

    public float getLeft() {
        return left;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }

    public float getRight() {
        return right;
    }

}
