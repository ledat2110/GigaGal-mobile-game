package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG;

public class Assets implements Disposable, AssetErrorListener {
    public static final String TAG = Assets.class.getName();

    public static final Assets instance  = new Assets();

    public GigaGalAssets gigaGalAssets;

    public PlatFormAssets platFormAssets;

    public EnemyAssets enemyAssets;

    public AssetManager assetManager;

    public BulletAssets bulletAssets;

    public PowerupAssets powerupAssets;

    public ExplosionAssets explosionAssets;

    public ExitPortalAssets exitPortalAssets;

    private Assets(){}

    public void init(){
        this.assetManager = new AssetManager();
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);

        gigaGalAssets = new GigaGalAssets(atlas);
        platFormAssets = new PlatFormAssets(atlas);
        enemyAssets = new EnemyAssets(atlas);
        bulletAssets = new BulletAssets(atlas);
        powerupAssets = new PowerupAssets(atlas);
        explosionAssets = new ExplosionAssets(atlas);
        exitPortalAssets = new ExitPortalAssets(atlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.log(TAG, "could't load assets", throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public class GigaGalAssets {
        public final TextureAtlas.AtlasRegion standingRight;
        public final TextureAtlas.AtlasRegion standingLeft;
        public final TextureAtlas.AtlasRegion jumpingRight;
        public final TextureAtlas.AtlasRegion jumpingLeft;
        public final Animation walkingRight;
        public final Animation walkingLeft;

        public GigaGalAssets(TextureAtlas atlas) {
            standingRight = atlas.findRegion(Constants.STANDING_RIGHT);
            standingLeft = atlas.findRegion(Constants.STANDING_LEFT);
            jumpingRight = atlas.findRegion(Constants.JUMPING_RIGHT);
            jumpingLeft = atlas.findRegion(Constants.JUMPING_LEFT);

            Array<TextureAtlas.AtlasRegion> walkingRightFrames = new Array<>();
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_1_RIGHT));
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_2_RIGHT));
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_3_RIGHT));
            walkingRight = new Animation(Constants.WALK_LOOP_DURATION, walkingRightFrames, LOOP_PINGPONG);

            Array<TextureAtlas.AtlasRegion> walkingLeftFrames = new Array<>();
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_1_LEFT));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_2_LEFT));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_3_LEFT));
            walkingLeft = new Animation(Constants.WALK_LOOP_DURATION, walkingLeftFrames, LOOP_PINGPONG);
        }
    }

    public class PlatFormAssets{
        public final NinePatch ninePatch;

        public PlatFormAssets(TextureAtlas atlas){
            TextureRegion platform = atlas.findRegion(Constants.PLATFORM_SPRITE);
            int size = Constants.STRETCHABLE_EDGES_SIZE;
            ninePatch = new NinePatch(platform, size, size, size, size);
        }
    }

    public class EnemyAssets {
        public final TextureAtlas.AtlasRegion enemy;

        public EnemyAssets(TextureAtlas atlas) {
            enemy = atlas.findRegion(Constants.ENEMY_SPRITE);

        }
    }

    public class BulletAssets{
        public final TextureAtlas.AtlasRegion bullet;

        public BulletAssets(TextureAtlas atlas){
            bullet = atlas.findRegion(Constants.BULLET_SPRITE);
        }
    }

    public class PowerupAssets{
        public final TextureAtlas.AtlasRegion powerUp;

        public PowerupAssets(TextureAtlas atlas){
            powerUp = atlas.findRegion(Constants.POWERUP_SPRITE);
        }
    }

    public class ExplosionAssets{
        public final Animation explosion;
        public final TextureAtlas.AtlasRegion explosionSmall;
        public final TextureAtlas.AtlasRegion explosionMedium;
        public final TextureAtlas.AtlasRegion explosionLarge;

        public ExplosionAssets(TextureAtlas atlas){
            explosionSmall = atlas.findRegion(Constants.EXPLOSION_SMALL);
            explosionMedium = atlas.findRegion(Constants.EXPLOSION_MEDIUM);
            explosionLarge = atlas.findRegion(Constants.EXPLOSION_LARGE);

            Array<TextureAtlas.AtlasRegion> explosionFrames = new Array<>();
            explosionFrames.add(atlas.findRegion(Constants.EXPLOSION_SMALL));
            explosionFrames.add(atlas.findRegion(Constants.EXPLOSION_MEDIUM));
            explosionFrames.add(atlas.findRegion(Constants.EXPLOSION_LARGE));
            explosion = new Animation(Constants.EXPLOSION_DURATION, explosionFrames, Animation.PlayMode.NORMAL);
        }
    }

    public class ExitPortalAssets {

        public final Animation exitPortal;


        public ExitPortalAssets(TextureAtlas atlas) {
            final TextureAtlas.AtlasRegion exitPortal1 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_1);
            final TextureAtlas.AtlasRegion exitPortal2 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_2);
            final TextureAtlas.AtlasRegion exitPortal3 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_3);
            final TextureAtlas.AtlasRegion exitPortal4 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_4);
            final TextureAtlas.AtlasRegion exitPortal5 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_5);
            final TextureAtlas.AtlasRegion exitPortal6 = atlas.findRegion(Constants.EXIT_PORTAL_SPRITE_6);

            Array<TextureAtlas.AtlasRegion> exitPortalFrames = new Array<TextureAtlas.AtlasRegion>();
            exitPortalFrames.add(exitPortal1);
            exitPortalFrames.add(exitPortal2);
            exitPortalFrames.add(exitPortal3);
            exitPortalFrames.add(exitPortal4);
            exitPortalFrames.add(exitPortal5);
            exitPortalFrames.add(exitPortal6);

            exitPortal = new Animation(Constants.EXIT_PORTAL_FRAME_DURATION, exitPortalFrames);
        }
    }
}
