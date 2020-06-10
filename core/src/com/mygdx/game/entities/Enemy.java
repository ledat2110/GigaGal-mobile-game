package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Enums;
import com.mygdx.game.util.Utils;

public class Enemy {

    Platform platform;
    Vector2 position;
    Enums.Direction direction;
    int healthCounter;

    private final float randomPhase;

    final long startTime;

    public Enemy(Platform platform) {
        this.platform = platform;
        position = new Vector2(platform.left, platform.top + Constants.ENEMY_CENTER.y);
        direction = Enums.Direction.RIGHT;
        startTime = TimeUtils.nanoTime();
        healthCounter = Constants.HEALTH_COUNTER;
        randomPhase = MathUtils.random();
    }

    public void update(float delta) {
        //on patrol
        if (direction == Enums.Direction.RIGHT){
            moveRight(delta);
        }
        if (direction == Enums.Direction.LEFT){
            moveLeft(delta);
        }

        //bouncy anti-gravity
        float elapsedTime = Utils.secondsSince(startTime);
        final float bobMultiplier = 1 + MathUtils.sin(MathUtils.PI2 * ((elapsedTime / Constants.ENEMY_BOB_PERIOD) + randomPhase));

        position.y = platform.top + Constants.ENEMY_CENTER.y + bobMultiplier * Constants.ENEMY_BOB_AMPLITUDE;
    }

    private void moveLeft(float delta) {
        position.x -= delta * Constants.ENEMY_MOVE_SPEED;
        if (position.x <= platform.left){
            direction = Enums.Direction.RIGHT;
        }
    }

    private void moveRight(float delta) {
        position.x += delta * Constants.ENEMY_MOVE_SPEED;
        if (position.x >= platform.right){
            direction = Enums.Direction.LEFT;
        }
    }

    public void render(SpriteBatch batch) {
        TextureAtlas.AtlasRegion enemy = Assets.instance.enemyAssets.enemy;

        Utils.drawTextureRegion(batch, enemy, position, Constants.ENEMY_CENTER);
    }

    public void minusHealth(){
        healthCounter -= 1;
    }

    public boolean isDead(){
        return healthCounter < 1;
    }

    public boolean hitBullet(Vector2 position){
        return this.position.dst(position) < Constants.ENEMY_COLLISION_RADIUS;
    }

    public int hitGigaGal(Rectangle boundingRect){
        Rectangle boundingEnemy = new Rectangle(this.position.x - Constants.ENEMY_CENTER.x, this.position.y - Constants.ENEMY_CENTER.y, Constants.ENEMY_COLLISION_RADIUS*2, Constants.ENEMY_COLLISION_RADIUS*2);
        if (boundingRect.overlaps(boundingEnemy)){
            if (boundingRect.x < this.position.x) {
                return -1;
            } else {
                return 1;
            }
        }else
            return 0;
    }

    public Vector2 getPosition() {
        return position;
    }
}
