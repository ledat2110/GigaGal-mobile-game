package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entities.Bullet;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.ExitPortal;
import com.mygdx.game.entities.Explosion;
import com.mygdx.game.entities.GigaGal;
import com.mygdx.game.entities.Platform;
import com.mygdx.game.entities.Powerup;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Enums;

public class Level {
    public boolean victory;
    public boolean gameOver;
    GigaGal gigaGal;
    ExitPortal exitPortal;

    Array<Platform> platforms;
    DelayedRemovalArray<Enemy> enemies;
    DelayedRemovalArray<Bullet> bullets;
    DelayedRemovalArray<Explosion> explosions;
    DelayedRemovalArray<Powerup> powerups;

    Viewport viewport;

    public Level(Viewport viewport) {
        this.viewport = viewport;

        //gigaGal = new GigaGal();
        //exitPortal = new ExitPortal(Constants.EXIT_PORTAL_DEFAULT_LOCATION);
        platforms = new Array<Platform>();
        enemies = new DelayedRemovalArray<Enemy>();
        bullets = new DelayedRemovalArray<>();
        explosions = new DelayedRemovalArray<>();
        powerups = new DelayedRemovalArray<>();
        victory = false;
        gameOver = false;

        //addPlatforms();
        //addEnemies();
        //addPowerups();
    }

    public void update(float delta) {


        if (victory == false && gameOver == false) {

            if (gigaGal.getPosition().dst(exitPortal.position) < Constants.EXIT_PORTAL_RADIUS) {
                victory = true;
            }
            gigaGal.update(delta, this);

            enemies.begin();
            for (Enemy enemy: enemies){
                enemy.update(delta);
                if (enemy.isDead() == true){
                    spawnExplosion(enemy.getPosition());
                    enemies.removeValue(enemy, true);
                }
            }
            enemies.end();

            bullets.begin();
            for (Bullet bullet: bullets){
                bullet.update(delta, this);
                if (bullet.isActive() == false){
                    bullets.removeValue(bullet, true); // == comparision of adress of memory, .equals() comparision of content
                }
            }
            bullets.end();

            explosions.begin();
            for (Explosion explosion: explosions){
                if (explosion.isFinished() == true){
                    explosions.removeValue(explosion, true);
                }
            }
            explosions.end();

            powerups.begin();
            for (Powerup powerup: powerups){
                if (powerup.isActive() == false){
                    powerups.removeValue(powerup, true);
                }
            }
            powerups.end();
        }
    }

    public void render(SpriteBatch batch) {

        batch.begin();

        exitPortal.render(batch);

        for (Platform platform : platforms){
            platform.render(batch);
        }

        for (Enemy enemy: enemies){
            enemy.render(batch);
        }

        gigaGal.render(batch);

        for (Bullet bullet : bullets){
            bullet.render(batch);
        }

        for (Explosion explosion: explosions){
            explosion.render(batch);
        }

        for (Powerup powerup: powerups){
            powerup.render(batch);
        }

        batch.end();
    }

    public void addPlatforms(){
        platforms.add(new Platform(0, 0, 100, 20));
        platforms.add(new Platform(90,60,50,20));
        platforms.add(new Platform(120,140,20,20));
        platforms.add(new Platform(180,150,20,100));
        platforms.add(new Platform(290,170,100,100));
        platforms.add(new Platform(240,190,20,20));
        //platforms.add(new Platform(15, 100, 30, 20));
        platforms.add(new Platform(75, 90, 100, 65));
        platforms.add(new Platform(35, 55, 50, 20));
        //platforms.add(new Platform(10, 20, 20, 9));
        platforms.add(new Platform(100, 110, 30, 9));
        platforms.add(new Platform(200, 130, 30, 40));
        platforms.add(new Platform(150, 150, 30, 9));
        platforms.add(new Platform(150, 180, 30, 9));
        platforms.add(new Platform(200, 200, 9, 9));
        platforms.add(new Platform(280, 100, 30, 9));
    }

    public void addEnemies() {
        enemies.clear();
        for (int i = 1; i < platforms.size; i++){
            if (i%5 == 0){
                enemies.add(new Enemy(platforms.get(i)));
            }
        }
    }

    public void addPowerups(){
        powerups.clear();
        for (int i = 1; i < platforms.size; i++){
            if (i%4 == 0){
                float x = MathUtils.random(platforms.get(i).getLeft(), platforms.get(i).getRight());
                Vector2 powerupPosition = new Vector2(x, platforms.get(i).getTop() + Constants.BULLET_CENTER.y);
                powerups.add(new Powerup(powerupPosition));
            }
        }
    }

    public void spawnBullets(Vector2 position, Enums.Direction direction) {
        bullets.add(new Bullet(position, direction));
    }

    public float wolrdLimit(boolean left){
        if (left == true)
            return viewport.getCamera().position.x - viewport.getWorldWidth()/2;
        return viewport.getCamera().position.x + viewport.getWorldWidth()/2;
    }

    public void setGigaGal(GigaGal gigaGal) {
        this.gigaGal = gigaGal;
    }

    public void spawnExplosion(Vector2 position){
        explosions.add(new Explosion(position));
    }

    public void spawnPowerup(Vector2 position){
        powerups.add(new Powerup(position));
    }

    public DelayedRemovalArray<Enemy> getEnemies(){
        return enemies;
    }

    public Array<Platform> getPlatforms() {
        return platforms;
    }

    public DelayedRemovalArray<Powerup> getPowerups() {
        return powerups;
    }

    public void setExitPortal(ExitPortal exitPortal) {
        this.exitPortal = exitPortal;
    }
}
