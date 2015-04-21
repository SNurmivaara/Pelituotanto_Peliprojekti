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

import wildwestshootout.Game;
import wildwestshootout.entity.projectile.BulletProjectile;
import wildwestshootout.entity.projectile.Projectile;
import wildwestshootout.graphics.AnimatedSprite;
import wildwestshootout.graphics.Screen;
import wildwestshootout.graphics.Sprite;
import wildwestshootout.graphics.SpriteSheet;
import wildwestshootout.input.Keyboard;
import wildwestshootout.input.Mouse;

/**
 *
 * @author Sami nurmivaara
 */
public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private boolean walking = false;
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 8);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 8);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 8);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 8);
    
    private AnimatedSprite animSprite = down;

    Projectile p;
    private int reloadSpeed = 0;

    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_front;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        reloadSpeed = BulletProjectile.RELOAD_SPEED;
    }

    @Override
    public void update() {
        if (walking) {
            animSprite.update();
        } else {
            animSprite.setFrame(0);
        }
        if (reloadSpeed > 0) {
            reloadSpeed--;
        }
        int xa = 0, ya = 0;

        if (input.up) {
            ya--;
            animSprite = up;
        } else if (input.down) {
            ya++;
            animSprite = down;
        }
        if (input.left) {
            xa--;
            animSprite = left;
        } else if (input.right) {
            xa++;
            animSprite = right;
        }
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
        clear();
        updateShooting();
    }

    @Override
    public void render(Screen screen) {
        sprite = animSprite.getSprite();
        screen.renderMob(x - 16, y - 16, sprite);
    }

    private void updateShooting() {
        if (Mouse.getButton() == 1 && reloadSpeed <= 0) {
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            reloadSpeed = BulletProjectile.RELOAD_SPEED;
        }
    }

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()) {
                level.getProjectiles().remove(i);
            }
        }
    }

}
