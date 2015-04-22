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

import wildwestshootout.graphics.AnimatedSprite;
import wildwestshootout.graphics.Screen;
import wildwestshootout.graphics.Sprite;
import wildwestshootout.graphics.SpriteSheet;

/**
 *
 * @author Sami Nurmivaara
 */
public class Civilian extends Mob {

    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.civilian_right, 16, 16, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.civilian_left, 16, 16, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.civilian_up, 16, 16, 3);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.civilian_down, 16, 16, 3);

    private AnimatedSprite animSprite = down;

    private int time = 0;
    private double xa = 0;
    private double ya = 0;

    public Civilian(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.civilian;
        speed = 0.5;
    }

    @Override
    public void update() {
        time++;

        if (time % (random.nextInt(50) + 30) == 0) {
            xa = random.nextInt(2) - speed;
            ya = random.nextInt(2) - speed;
            if (random.nextInt(4) == 0) {
                xa = 0;
                ya = 0;
            }
        }

        if (walking) {
            animSprite.update();
        } else {
            animSprite.setFrame(0);
        }
        if (ya < 0) {
            direction = Direction.UP;
            animSprite = up;
        } else if (ya > 0) {
            direction = Direction.DOWN;
            animSprite = down;
        }
        if (xa < 0) {
            direction = Direction.LEFT;
            animSprite = left;
        } else if (xa > 0) {
            direction = Direction.RIGHT;
            animSprite = right;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
        
        sprite = animSprite;
    }

    @Override
    public void render(Screen screen) {
        sprite = animSprite.getSprite();
        screen.renderMob((int) x,(int) y, sprite);
    }

}
