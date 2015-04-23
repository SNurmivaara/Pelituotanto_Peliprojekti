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
package wildwestshootout.entity.particle;

import wildwestshootout.entity.Entity;
import wildwestshootout.graphics.Screen;
import wildwestshootout.graphics.Sprite;

/**
 *
 * @author Sami nurmivaara
 */
public class Particle extends Entity {

    private Sprite sprite;

    private int life;
    private int time = 0;

    protected double xx, yy, zz;
    protected double xa, ya, za;

    public Particle(double x, double y, int life, int type) {
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;
        this.life = life + (random.nextInt(20) - 10);
        if (type == 1) {
            sprite = Sprite.particle_normal;
        } else if (type == 2) {
            sprite = Sprite.particle_blood;
        } else {
            sprite = Sprite.particle_normal;
        }

        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
        this.zz = random.nextFloat() + 2.0;
    }

    @Override
    public void update() {
        time++;

        if (time >= 7400) {
            time = 0;
        }

        if (time > life) {
            remove();
        }

        za -= 0.1;

        if (zz < 0) {
            zz = 0;
            za *= -0.6;
            xa *= 0.4;
            ya *= 0.4;
        }

        move(xx + xa, (yy + ya) + (zz + za));
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
    }

    private void move(double x, double y) {
        if (collision(x, y)) {
            this.xa *= -0.5;
            this.ya *= -0.5;
            this.za *= -0.5;
        }
        this.xx += xa;
        this.yy += ya;
        this.zz += za;
    }

    public boolean collision(double x, double y) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            double xt = (x - c % 2 * 16) / 16;
            double yt = (y - c / 2 * 16) / 16;
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
