package wildwestshootout.entity;

import wildwestshootout.graphics.Sprite;

/**
 *
 * @author Sami
 */
public abstract class Projectile extends Entity {
    
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx, ny;
    protected double speed, rateOfFire, range, damage;
    
    public Projectile(int xOrigin, int yOrigin, double direction) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.angle = direction;
        this.x = xOrigin;
        this.y = yOrigin;
    }
    
    protected void move() {
        
    }
}
