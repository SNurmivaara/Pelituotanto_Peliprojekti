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

import wildwestshootout.entity.spawner.ParticleSpawner;
import wildwestshootout.graphics.Screen;
import wildwestshootout.graphics.Sprite;

/**
 *
 * @author Sami nurmivaara
 */
public class BulletProjectile extends Projectile {

    public static final int RELOAD_SPEED = 40;
    public static final int DAMAGE = 1;

    public BulletProjectile(double xOrigin, double yOrigin, double direction) {
        super(xOrigin, yOrigin, direction);
        range = 200;
        speed = 4;
        sprite = Sprite.musketBullet;

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    @Override
    public void update() {
        if (level.tileCollision((int) (x + nx), (int) (y + ny), 6, 5, 5)) {
            level.add(new ParticleSpawner((int) x, (int) y, 40, 15, 1, level));
            remove();
        }
        move();
    }

    protected void move() {
        x += nx;
        y += ny;
        if (calculateDistance() > range) {
            remove();
        }
    }

    private double calculateDistance() {
        double distance = 0;
        distance = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
        return distance;
    }

    @Override
    public void render(Screen screen) {
        screen.renderProjectile((int) x, (int) y, this);
    }

}
