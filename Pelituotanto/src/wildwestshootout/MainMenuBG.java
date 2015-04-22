/*
 * Copyright (C) 2015 s1200478
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
package wildwestshootout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import wildwestshootout.graphics.SpriteSheet;

/**
 *
 * @author s1200478
 */
public class MainMenuBG extends JPanel {
    private BufferedImage MenunTausta;
    public MainMenuBG(){
        
        MenunTausta = load();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(MenunTausta==null)return;
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(MenunTausta,0,0,this);
    }
    private BufferedImage load() {
        try {
            return ImageIO.read(getClass().getResourceAsStream("Menu_background.png"));
        } catch (Exception ex) {
            Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
