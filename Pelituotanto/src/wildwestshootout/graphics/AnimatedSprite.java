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
package wildwestshootout.graphics;

/**
 *
 * @author Sami nurmivaara
 */
public class AnimatedSprite extends Sprite {

    private int frame = 0;
    private Sprite sprite;
    private int rate = 6;
    private int time = 0;
    private int length = -1;

    public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
        super(sheet, width, height);
        this.length = length;
        sprite = sheet.getSprites()[0];
        if (length > sheet.getSprites().length) {
            System.err.println("Error! Animaation pituus liian pitkä!");
        }
    }

    public void update() {
        time++;
        if (time % rate == 0) {
            if (frame >= length) {
                frame = 0;
            } else {
                sprite = sheet.getSprites()[frame];
                frame++;
            }
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setFrameRate(int frames) {
        rate = frames;
    }

    public void setFrame(int index) {
        if (index > sheet.getSprites().length - 1) {
            System.err.println("Index out of bounds in " + this);
            return;
        }
        sprite = sheet.getSprites()[index];
    }

}
