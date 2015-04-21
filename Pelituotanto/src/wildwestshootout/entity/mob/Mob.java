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
import wildwestshootout.graphics.Sprite;

/**
 *
 * @author Sami nurmivaara
 */
public abstract class Mob extends Entity {

    protected boolean moving = false;
    protected boolean walking = false;

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    
    protected Direction direction;

    public void move(int xa, int ya) {
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
        if (!collision(xa, ya)) {
            x += xa;
            y += ya;
        }
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    protected void shoot(int x, int y, double direction) {
        Projectile projectile = new BulletProjectile(x, y, direction);
        level.add(projectile);
    }

    private boolean collision(int xa, int ya) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
            int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
            if (level.getTile(xt, yt).solid()) {
                solid = true;
            }
        }
        return solid;
    }

}
