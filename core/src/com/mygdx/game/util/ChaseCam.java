package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.mygdx.game.entities.GigaGal;

public class ChaseCam {
    private Camera camera;
    private GigaGal target;

    private boolean followingFlag;

    public ChaseCam(Camera camera, GigaGal target){
        this.camera = camera;
        this.target = target;
        followingFlag = true;
    }

    public  void update(float delta){

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            followingFlag = !followingFlag;

        }

        if (followingFlag == true){

            camera.position.x = target.position.x;
            camera.position.y = target.position.y;
        }
        else{
            if (Gdx.input.isKeyPressed(Input.Keys.A)){
                camera.position.x -= delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)){
                camera.position.x += delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)){
                camera.position.y -= delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)){
                camera.position.y += delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setTarget(GigaGal target) {
        this.target = target;
    }

}
