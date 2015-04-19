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
package wildwestshootout.level.tile;

import wildwestshootout.graphics.Screen;
import wildwestshootout.graphics.Sprite;

/**
 *
 * @author Sami nurmivaara
 */
public class Tile {

    public int x, y;
    public Sprite sprite;

    public static Tile sand = new SandTile(Sprite.sand);
    public static Tile concrete = new ConcreteTile(Sprite.concrete);
    public static Tile sandCactus = new SandCactusTile(Sprite.sandCactus);
    public static Tile sandRock = new SandRockTile(Sprite.sandRock);
    public static Tile musketBullet = new SandRockTile(Sprite.musketBullet);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    
    
    /**
     * Hiekka = 0xFFFFFF00 
     * Betoni = 0xFF808080 
     * Kaktus hiekalla = 0xFFAAD800
     * Kivi hiekalla = 0xFFCEAC00
     */
    public static final int col_sand = 0xFFFFFF00;
    public static final int col_concrete = 0xFF808080;
    public static final int col_sandCactus = 0xFFAAD800;
    public static final int col_sandRock = 0xFFCEAC00;
    

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
    }

    public boolean solid() {
        return false;
    }
    
    public boolean breakable() {
        return false;
    }
}
