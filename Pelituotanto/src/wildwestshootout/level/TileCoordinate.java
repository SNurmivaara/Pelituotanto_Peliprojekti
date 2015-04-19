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
package wildwestshootout.level;

/**
 *
 * @author Sami nurmivaara
 */
public class TileCoordinate {

    private int x, y;
    private final int TILE_SIZE = 16;

    public TileCoordinate(int x, int y) {
        this.x = x * TILE_SIZE;
        this.y = y * TILE_SIZE;
    }
    
    public int x() {
        return x;
    }
    
    public int y() {
        return y;
    }
    
    public int[] xy() {
        int[] result = new int[2];
        result[0] = x;
        result[1] = y;
        return result;
    }
}
