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
package wildwestshootout.entity.mob;

import wildwestshootout.entity.projectile.BulletProjectile;
import wildwestshootout.entity.Entity;
import wildwestshootout.entity.projectile.Projectile;
import wildwestshootout.graphics.Screen;

/**
 *
 * @author Sami nurmivaara
 */
public abstract class Mob extends Entity {

    protected boolean moving = false;
    protected boolean walking = false;
    protected double speed = 1;

    protected enum Direction {

        UP, DOWN, LEFT, RIGHT
    }

    protected Direction direction;

    public void move(double xa, double ya) {
        //Jos liikutaan kahdella akselilla, suoritetaan kaksi move() metodia
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        //vasen
        if (xa > 0) {
            this.direction = Direction.LEFT;
        }
        //oikea
        if (xa < 0) {
            this.direction = Direction.RIGHT;
        }
        //ylös
        if (ya > 0) {
            this.direction = Direction.UP;
        }
        //alas
        if (ya < 0) {
            this.direction = Direction.DOWN;
        }

        //Liikutaan jos hahmo ei osu mihinkään liikkuessaan
        while (xa != 0) {
            if (Math.abs(xa) > 1) {
                if (!collision(abs(xa), ya)) {
                    this.x += abs(xa);
                }
                xa -= abs(xa);
            } else {
                if (!collision(abs(xa), ya)) {
                    this.x += xa;
                }
                xa = 0;
            }
        }
        
        while (ya != 0) {
            if (Math.abs(ya) > 1) {
                if (!collision(xa, abs(ya))) {
                    this.y += abs(ya);
                }
                ya -= abs(ya);
            } else {
                if (!collision(xa, abs(ya))) {
                    this.y += ya;
                }
                ya = 0;
            }
        }
    }

    private int abs(double value) {
        if (value < 0) {
            return -1;
        }
        return 1;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    protected void shoot(double x, double y, double direction) {
        Projectile projectile = new BulletProjectile(x, y, direction);
        level.add(projectile);
    }

    private boolean collision(double xa, double ya) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            double xt = ((x + xa) - c % 2 * 16) / 16;
            double yt = ((y + ya) - c / 2 * 16) / 16;
            int ix = (int) Math.ceil(xt);
            int iy = (int) Math.ceil(yt);
            if (c % 2 == 0) {
                ix = (int) Math.floor(xt);
            }
            if (c / 2 == 0) {
                iy = (int) Math.floor(yt);
            }
            if (level.getTile(ix, iy).solid()) {
                solid = true;
            }
        }
        return solid;
    }

}
