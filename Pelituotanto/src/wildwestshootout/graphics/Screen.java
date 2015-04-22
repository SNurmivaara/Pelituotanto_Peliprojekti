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

import java.util.Random;
import wildwestshootout.entity.mob.Chaser;
import wildwestshootout.entity.mob.Mob;
import wildwestshootout.entity.projectile.Projectile;
import wildwestshootout.level.tile.Tile;

/**
 *
 * @author Sami nurmivaara
 */
public class Screen {

    //Määritetään muuttujat width, height ja pixels[]
    public int width, height;
    public int[] pixels;

    //Kerrotaan pelille kartan koko
    public final int MAP_SIZE = 64;

    //Luodaan kartan koosta "maski"
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;

    //Luodaan muuttujat kartan offsetille
    public int xOffset, yOffset;

    //Määritellään montako ruutua pelikentässä on
    public int[] tiles = new int[64 * 64];

    //Määritellään Random() metodi
    private Random random = new Random();

    //Piirretään kentälle ruutuja. Tällä hetkellä piirtää vain random luokalla generoituja erivärisiä ruutuja
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    //Tyhjennetään ruutu
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= this.xOffset;
        yp -= this.yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yAbsolute = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xAbsolute = x + xp;
                if (xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) {
                    break;
                }

                if (xAbsolute < 0) {
                    xAbsolute = 0;
                }

                pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void renderProjectile(int xp, int yp, Projectile p) {
        xp -= this.xOffset;
        yp -= this.yOffset;
        for (int y = 0; y < p.getSpriteSize(); y++) {
            int yAbsolute = y + yp;
            for (int x = 0; x < p.getSpriteSize(); x++) {
                int xAbsolute = x + xp;
                if (xAbsolute < -p.getSpriteSize() || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) {
                    break;
                }

                if (xAbsolute < 0) {
                    xAbsolute = 0;
                }

                int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
                if (col != 0xFFFF00FF) {
                    pixels[xAbsolute + yAbsolute * width] = p.getSprite().pixels[x + y * p.getSpriteSize()];
                }

            }
        }
    }

    public void renderMob(int xp, int yp, Sprite sprite) {
        xp -= this.xOffset;
        yp -= this.yOffset;
        for (int y = 0; y < 16; y++) {
            int yAbsolute = y + yp;
            for (int x = 0; x < 16; x++) {
                int xAbsolute = x + xp;
                if (xAbsolute < -16 || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) {
                    break;
                }

                if (xAbsolute < 0) {
                    xAbsolute = 0;
                }

                int col = sprite.pixels[x + y * 16];
                if (col != 0xFFFF00FF) {
                    pixels[xAbsolute + yAbsolute * width] = col;
                }

            }
        }
    }

    public void renderMob(int xp, int yp, Mob mob) {
        xp -= this.xOffset;
        yp -= this.yOffset;
        for (int y = 0; y < 16; y++) {
            int yAbsolute = y + yp;
            for (int x = 0; x < 16; x++) {
                int xAbsolute = x + xp;
                if (xAbsolute < -16 || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) {
                    break;
                }

                if (xAbsolute < 0) {
                    xAbsolute = 0;
                }

                int col = mob.getSprite().pixels[x + y * 16];
                if ((mob instanceof Chaser) && col == 0xFF307A27) {
                    col = 0xFFFFF496;
                }
                if ((mob instanceof Chaser) && col == 0xFF20511A) {
                    col = 0xFFD8DB2E;
                }
                if ((mob instanceof Chaser) && col == 0xFFC4C4C4) {
                    col = 0xFF000000;
                }
                if (col != 0xFFFF00FF) {
                    pixels[xAbsolute + yAbsolute * width] = col;
                }

            }
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) {
                    continue;
                }
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }

    public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for (int y = 0; y < sheet.HEIGHT; y++) {
            int ya = y + yp;
            for (int x = 0; x < sheet.WIDTH; x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) {
                    continue;
                }
                pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
