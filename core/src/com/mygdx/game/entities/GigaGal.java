package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Level;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Enums;
import com.mygdx.game.util.Utils;

public class GigaGal {
    public final static String TAG = GigaGal.class.getName();

    public Vector2 position;
    Vector2 velocity;
    Vector2 lastFramePosition;
    Vector2 spawnLocation;
    int ammoCounter;

    Enums.Direction facing;
    Enums.JumpState jumpState;
    Enums.WalkState walkState;

    long jumpStartTime;
    long walkStartTime;


    public GigaGal(Vector2 position) {
        spawnLocation = position;
        this.position = new Vector2();
        velocity = new Vector2();
        lastFramePosition = new Vector2();

        init();
    }

    public void init(){
        velocity.setZero();
        position.set(spawnLocation);
        lastFramePosition.set(position);

        facing = Enums.Direction.RIGHT;
        jumpState = Enums.JumpState.FALLING;
        walkState = Enums.WalkState.NOT_WALKING;
        ammoCounter = Constants.GIGAGAL_AMMO;
    }

    public void update(float delta, Level level) {

        lastFramePosition.set(position);

        velocity.y -= Constants.GRAVITY;
        position.mulAdd(velocity, delta);

        // Land on/fall off platforms
        if (jumpState != Enums.JumpState.JUMPING){
            if (jumpState != Enums.JumpState.RECOILING)
                jumpState = Enums.JumpState.FALLING;

            if (position.y  < Constants.KILL_PLANE){
               init();
            }

            for (Platform platform: level.getPlatforms()){
                if (platform.landedOnPlatform(lastFramePosition, position) == true){
                    jumpState = Enums.JumpState.GROUNDED;
                    velocity.setZero();
                    position.y = platform.top + Constants.GIGAGAL_EYES_HEIGHT;
                }
            }
        }

        Rectangle boundingRect = new Rectangle(position.x - Constants.GIGAGAL_EYES_POSITION.x, position.y - Constants.GIGAGAL_EYES_POSITION.y, Constants.GIGAGAL_STANCE_WIDTH, Constants.GIGAGAL_HEIGHT);
        // Collide with enemies
        for (Enemy enemy: level.getEnemies()){
            int hit = enemy.hitGigaGal(boundingRect);
            if(hit == -1){
                recoilFromEnemy(Enums.Direction.LEFT);
            }else if(hit == 1)
                recoilFromEnemy(Enums.Direction.RIGHT);
        }

        //hit powerup
        for (Powerup powerup: level.getPowerups()){
            if (powerup.isHit(boundingRect))
                ammoCounter += Constants.POWERUP_VALUE;
        }

        // Jump
        if (Gdx.input.isKeyPressed(Input.Keys.Z)){
            if (jumpState == Enums.JumpState.GROUNDED)
                startJump();
            else if (jumpState == Enums.JumpState.JUMPING)
                continueJump();
        }else{
            endJump();
        }

        // Move left/right
        if (jumpState != Enums.JumpState.RECOILING) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) == true)
                moveLeft(delta);
            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) == true)
                moveRight(delta);
            else
                walkState = Enums.WalkState.NOT_WALKING;
        }

        //shoot
        if (Gdx.input.isKeyJustPressed(Input.Keys.X) && ammoCounter > 0){
            ammoCounter --;
            Vector2 bulletPosition = new Vector2(Constants.GIGAGAL_CANNON);
            if (facing == Enums.Direction.LEFT)
                bulletPosition.x = -bulletPosition.x;
            bulletPosition.add(position);
            level.spawnBullets(bulletPosition, facing);
        }
    }


    private void endJump() {
        if (jumpState == Enums.JumpState.JUMPING)
            jumpState = Enums.JumpState.FALLING;
    }

    private void startJump() {
        jumpState = Enums.JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJump();
    }

    private void continueJump() {
        if (jumpState != Enums.JumpState.JUMPING)
            return;
        float duration = (TimeUtils.nanoTime() - jumpStartTime) * MathUtils.nanoToSec;
        if (duration <= Constants.GIGAGAL_JUMP_DURATION){
            velocity.y = Constants.GIGAGAL_JUMP_SPEED;
        }else
            endJump();
    }

    private void moveRight(float delta) {
        if (jumpState == Enums.JumpState.GROUNDED && walkState != Enums.WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        facing = Enums.Direction.RIGHT;
        walkState = Enums.WalkState.WALKING;
        position.x += Constants.GIGAGAL_MOVE_SPEED * delta;
    }

    private void moveLeft(float delta) {
        if (jumpState == Enums.JumpState.GROUNDED && walkState != Enums.WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = Enums.WalkState.WALKING;
        facing = Enums.Direction.LEFT;
        position.x -= Constants.GIGAGAL_MOVE_SPEED * delta;
    }

    private void recoilFromEnemy(Enums.Direction direction) {

        velocity.y = Constants.KNOCK_BACK_SPEED.y;
        jumpState = Enums.JumpState.RECOILING;

        if (direction == Enums.Direction.LEFT){
            velocity.x = -Constants.KNOCK_BACK_SPEED.x;
        }else{
            velocity.x = Constants.KNOCK_BACK_SPEED.x;
        }
    }

    public void render(SpriteBatch batch) {
        TextureRegion state = Assets.instance.gigaGalAssets.standingRight;

        if (facing == Enums.Direction.LEFT)
        {
            if (jumpState != Enums.JumpState.GROUNDED)
                state = Assets.instance.gigaGalAssets.jumpingLeft;
            else if (walkState == Enums.WalkState.WALKING){
                float duration = (TimeUtils.nanoTime() - walkStartTime) * MathUtils.nanoToSec;
                state = (TextureRegion) Assets.instance.gigaGalAssets.walkingLeft.getKeyFrame(duration);
            }
            else
                state = Assets.instance.gigaGalAssets.standingLeft;
        } else if (facing == Enums.Direction.RIGHT){
            if (jumpState != Enums.JumpState.GROUNDED)
                state = Assets.instance.gigaGalAssets.jumpingRight;
            else if (walkState == Enums.WalkState.WALKING){
                float duration = (TimeUtils.nanoTime() - walkStartTime) * MathUtils.nanoToSec;
                state = (TextureRegion) Assets.instance.gigaGalAssets.walkingRight.getKeyFrame(duration);
            }
            else
                state = Assets.instance.gigaGalAssets.standingRight;
        }

        Utils.drawTextureRegion(batch, state, position, Constants.GIGAGAL_EYES_POSITION);
    }

    public Vector2 getPosition() {
        return position;
    }
}
