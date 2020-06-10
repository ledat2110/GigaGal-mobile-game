package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.mygdx.game.Level;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Enums;
import com.mygdx.game.util.Utils;

public class Bullet {

    Enums.Direction direction;
    Vector2 position;

    boolean active;

    public Bullet(Vector2 position, Enums.Direction direction) {
        this.position = position;
        this.direction = direction;
        active = true;
    }

    public void update(float delta, Level level) {
        if (direction == Enums.Direction.RIGHT){
            position.x += Constants.BULLET_MOVE_SPEED * delta;
        }
        else
        {
            position.x -= Constants.BULLET_MOVE_SPEED * delta;
        }

        float right = level.wolrdLimit(false);
        float left = level.wolrdLimit(true);
        if (position.x > right || position.x < left)
            active = false;

        for (Enemy enemy: level.getEnemies()){
            if (enemy.hitBullet(position) == true) {
                enemy.minusHealth();
                level.spawnExplosion(position);
                active = false;
            }
        }
    }

    public void render(SpriteBatch batch) {
        Utils.drawTextureRegion(batch, Assets.instance.bulletAssets.bullet, position, Constants.BULLET_CENTER);
    }

    public boolean isActive(){
        return active;
    }
}