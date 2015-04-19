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

import java.util.ArrayList;
import java.util.List;
import wildwestshootout.entity.projectile.BulletProjectile;
import wildwestshootout.entity.Entity;
import wildwestshootout.entity.particle.Particle;
import wildwestshootout.entity.projectile.Projectile;
import wildwestshootout.graphics.Sprite;

/**
 *
 * @author Sami nurmivaara
 */
public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int direction = 0;
    protected boolean moving = false;
    protected boolean walking = false;

    public void move(int xa, int ya) {
        //Jos liikutaan kahdella akselilla, suoritetaan kaksi move() metodia
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }
        
        //vasen
        if (xa > 0) {
            this.direction = 1;
        }
        //oikea
        if (xa < 0) {
            this.direction = 3;
        }
        //ylös
        if (ya > 0) {
            this.direction = 2;
        }
        //alas
        if (ya < 0) {
            this.direction = 0;
        }

        //Liikutaan jos hahmo ei osu mihinkään liikkuessaan
        if (!collision(xa, ya)) {
            x += xa;
            y += ya;
        }
    }

    @Override
    public void update() {
        
    }
    
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

    public void render() {
    }

}
