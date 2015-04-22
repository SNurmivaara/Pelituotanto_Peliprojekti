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

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import wildwestshootout.entity.projectile.BulletProjectile;
import wildwestshootout.entity.projectile.Projectile;
import wildwestshootout.graphics.AnimatedSprite;
import wildwestshootout.graphics.Screen;
import wildwestshootout.graphics.Sprite;
import wildwestshootout.graphics.SpriteSheet;

/**
 *
 * @author Sami Nurmivaara
 */
public class Chaser extends Mob {

    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.civilian_right, 16, 16, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.civilian_left, 16, 16, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.civilian_up, 16, 16, 3);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.civilian_down, 16, 16, 3);

    private AnimatedSprite animSprite = down;

    private int time = 0;

    private double xa = 0;
    private double ya = 0;

    public Chaser(int x, int y) {
        this.speed = 1;
        this.x = x << 4;
        this.y = y << 4;
        this.sprite = Sprite.civilian;
        this.health = 1;
    }

    private void move() {
        xa = 0;
        ya = 0;

        List<Player> players = level.getPlayers(this, 1000);
        Player player = level.getClientPlayer();

        if (players.size() > 0) {
            if (x < (int) player.getX()) {
                xa += speed;
            }
            if (x > (int) player.getX()) {
                xa -= speed;
            }
            if (y < (int) player.getY()) {
                ya += speed;
            }
            if (y > (int) player.getY()) {
                ya -= speed;
            }
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    @Override
    public void update() {
        time++;
        
        this.gotHit();

        if (this.health <= 0) {
            this.remove();
        }

        move();

        if (walking) {
            animSprite.update();
        } else {
            animSprite.setFrame(0);
        }

        if (ya < 0) {
            animSprite = up;
            direction = Direction.UP;
        } else if (ya > 0) {
            animSprite = down;
            direction = Direction.DOWN;
        }
        if (xa < 0) {
            animSprite = left;
            direction = Direction.LEFT;
        } else if (xa > 0) {
            animSprite = right;
            direction = Direction.RIGHT;
        }

        if (!stuck()) {
            sprite = animSprite;
        } else {
            animSprite.setFrame(0);
        }

    }

    @Override
    public void render(Screen screen) {
        sprite = animSprite.getSprite();
        screen.renderMob((int) x, (int) y, this);
    }

    private boolean stuck() {
        boolean stuck = false;
        for (int c = 0; c < 4; c++) {
            double xt = ((x + xa) - c % 2) / 16;
            double yt = ((y + ya) - c / 2) / 16;
            int ix = (int) Math.ceil(xt);
            int iy = (int) Math.ceil(yt);
            if (c % 2 == 0) {
                ix = (int) Math.floor(xt);
            }
            if (c / 2 == 0) {
                iy = (int) Math.floor(yt);
            }
            if (level.getTile(ix, iy).solid()) {
                stuck = true;
            }
        }
        return stuck;
    }

}
