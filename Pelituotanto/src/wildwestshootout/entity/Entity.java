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
package wildwestshootout.entity;

import java.util.Random;
import wildwestshootout.graphics.Screen;
import wildwestshootout.level.Level;

/**
 *
 * @author Sami nurmivaara Nurmivaara
 */
public abstract class Entity {
    
    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    
    public void update() {
    }
    
    public void render(Screen screen) {
    }
    
    public void remove(){
        //Poistetaan yksikkö levelistä
        removed = true;
    }
    
    public boolean isRemoved() {
        return removed;
    }
    
    public void init(Level level) {
        this.level = level;
    }
    
}
