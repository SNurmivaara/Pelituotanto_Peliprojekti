/* 
 * Copyright (C) 2015 Sami Nurmivaara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package wildwestshootout.entity.projectile;

import wildwestshootout.entity.Entity;
import wildwestshootout.graphics.Sprite;

/**
 *
 * @author Sami nurmivaara
 */
public abstract class Projectile extends Entity {
    
    protected final double xOrigin, yOrigin;
    protected double angle;
    public Sprite sprite;
    protected double x, y;
    protected double nx, ny;
    protected double distance;
    protected double speed, range;
    
    public Projectile(double xOrigin, double yOrigin, double direction) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.angle = direction;
        this.x = xOrigin;
        this.y = yOrigin;
    }
    
    public Sprite getSprite(){
        return sprite;
    }
    
    @Override
    public double getX() {
        return this.x;
    }
    
    @Override
    public double getY() {
        return this.y;
    }
    
    public int getSpriteSize() {
        return sprite.SIZE;
    }
    
    protected void move() {
        
    }
}
