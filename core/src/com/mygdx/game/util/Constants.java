package com.mygdx.game.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Constants {

    // World/Camera
    public static final Color BACKGROUND_COLOR = Color.SKY;
    public static final String TEXTURE_ATLAS = "images/gigagal.pack.atlas";

    public static final float WORLD_SIZE = 256;
    public static final float GRAVITY = WORLD_SIZE/10;
    public static final float KILL_PLANE = -WORLD_SIZE/2;

    public static final float CHASE_CAM_MOVE_SPEED = 128;

    //gigagal
    public static final String STANDING_RIGHT = "standing-right";
    public static final String STANDING_LEFT = "standing-left";

    public static final String JUMPING_RIGHT = "jumping-right";
    public static final String JUMPING_LEFT = "jumping-left";

    public static final String WALK_1_RIGHT = "walk-1-right";
    public static final String WALK_2_RIGHT = "walk-2-right";
    public static final String WALK_3_RIGHT = "walk-3-right";

    public static final String WALK_1_LEFT = "walk-1-left";
    public static final String WALK_2_LEFT = "walk-2-left";
    public static final String WALK_3_LEFT = "walk-3-left";

    public static final Vector2 GIGAGAL_START_POSITION = new Vector2(WORLD_SIZE/4,WORLD_SIZE);
    public static final Vector2 GIGAGAL_EYES_POSITION = new Vector2(16,24);
    public static final float GIGAGAL_STANCE_WIDTH = 21f;
    public static final float GIGAGAL_EYES_HEIGHT = 15;
    public static final float GIGAGAL_HEIGHT = 23.0f;
    public static final Vector2 GIGAGAL_CANNON = new Vector2(12, -7);

    public static final float GIGAGAL_MOVE_SPEED = WORLD_SIZE/2;
    public static final float WALK_LOOP_DURATION = 0.25f;

    public static final float GIGAGAL_JUMP_SPEED = 1.5f * WORLD_SIZE;
    public static final float GIGAGAL_JUMP_DURATION = (float) 0.15;

    public static final Vector2 KNOCK_BACK_SPEED = new Vector2(200, 200);

    public static final int GIGAGAL_AMMO = 10;

    //platform
    public static final String PLATFORM_SPRITE = "platform";
    public static final int STRETCHABLE_EDGES_SIZE = 8;

    //enemy
    public static final String ENEMY_SPRITE = "enemy";
    public static final Vector2 ENEMY_CENTER = new Vector2(14, 22);
    public static final int HEALTH_COUNTER = 3;

    public static final float ENEMY_MOVE_SPEED = WORLD_SIZE/10;

    public static final float ENEMY_BOB_AMPLITUDE = 2f;
    public static final float ENEMY_BOB_PERIOD = 3f;

    public static final float ENEMY_COLLISION_RADIUS = 15f;

    //bullet
    public static final String BULLET_SPRITE = "bullet";

    public static final float BULLET_MOVE_SPEED = 150f;
    public static final Vector2 BULLET_CENTER = new Vector2(3, 2);

    //explosion
    public static final String EXPLOSION_SMALL = "explosion-small";
    public static final String EXPLOSION_MEDIUM = "explosion-medium";
    public static final String EXPLOSION_LARGE = "explosion-large";

    public static final Vector2 EXPLOSION_CENTER = new Vector2(8, 8);
    public static final float EXPLOSION_DURATION = 0.1f;

    //powerup
    public static final String POWERUP_SPRITE = "powerup";

    public static final int POWERUP_VALUE = 5;
    public static final Vector2 POWERUP_CENTER = new Vector2(8,3);
    public static final float POWERUP_COLLISION_RADIUS = 5f;

    //level loading
    public static final String LEVEL_DIR = "levels";
    public static final String LEVEL_FILE_EXTENSION = "dt";
    public static final String LEVEL_COMPOSITE = "composite";
    public static final String LEVEL_9PATCHES = "sImage9patchs";
    public static final String LEVEL_IMAGES = "sImages";
    public static final String LEVEL_ERROR_MESSAGE = "There was a problem loading the level.";
    public static final String LEVEL_IMAGENAME_KEY = "imageName";
    public static final String LEVEL_X_KEY = "x";
    public static final String LEVEL_Y_KEY = "y";
    public static final String LEVEL_WIDTH_KEY = "width";
    public static final String LEVEL_HEIGHT_KEY = "height";
    public static final String LEVEL_IDENTIFIER_KEY = "itemIdentifier";
    public static final String LEVEL_ENEMY_TAG = "Enemy";

    // Exit Portal
    public static final String EXIT_PORTAL_SPRITE_1 = "exit-portal-1";
    public static final String EXIT_PORTAL_SPRITE_2 = "exit-portal-2";
    public static final String EXIT_PORTAL_SPRITE_3 = "exit-portal-3";
    public static final String EXIT_PORTAL_SPRITE_4 = "exit-portal-4";
    public static final String EXIT_PORTAL_SPRITE_5 = "exit-portal-5";
    public static final String EXIT_PORTAL_SPRITE_6 = "exit-portal-6";

    public static final float EXIT_PORTAL_RADIUS = 28;
    public static final Vector2 EXIT_PORTAL_CENTER = new Vector2(31, 31);
    public static final float EXIT_PORTAL_FRAME_DURATION = 0.1f;
    public static final Vector2 EXIT_PORTAL_DEFAULT_LOCATION = new Vector2(200,200);

    // HUD
    public static final float HUD_VIEWPORT_SIZE = 480;
    public static final float HUD_MARGIN = 20;
    public static final String HUD_AMMO_LABEL = "Ammo: ";
    public static final String HUD_SCORE_LABEL = "Score: ";

    // Victory/Game Over screens
    public static final float LEVEL_END_DURATION = 5;
    public static final String VICTORY_MESSAGE = "You are the Winrar!";
    public static final String GAME_OVER_MESSAGE = "Game Over, Gal";
    public static final int EXPLOSION_COUNT =500;
    public static final int ENEMY_COUNT =200;
    public static final String FONT_FILE = "font/header.fnt";

    // Scoring
    public static final int ENEMY_KILL_SCORE = 100;
    public static final int ENEMY_HIT_SCORE = 25;
    public static final int POWERUP_SCORE = 50;

}
