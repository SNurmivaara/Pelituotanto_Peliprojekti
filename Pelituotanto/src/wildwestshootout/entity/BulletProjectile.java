package wildwestshootout.entity;

import wildwestshootout.graphics.Screen;
import wildwestshootout.graphics.Sprite;
import wildwestshootout.level.tile.Tile;

/**
 *
 * @author Sami
 */
public class BulletProjectile extends Projectile{

    public BulletProjectile(int xOrigin, int yOrigin, double direction) {
        super(xOrigin, yOrigin, direction);
        range = 200;
        speed = 4;
        damage = 20;
        rateOfFire = 15;
        
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }
    
    @Override
    public void update() {
        move();
    }
    
    protected void move() {
        x += nx;
        y += ny;
    }
    
    @Override
    public void render(Screen screen) {
        screen.renderTile(x, y, Tile.sandRock);
    }
    
    
}
