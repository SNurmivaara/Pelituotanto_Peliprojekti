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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

/**
 *
 * @author s1200478
 */
public class OmaNappi extends JButton {
    
    //Konstruktori
    public OmaNappi(String teksti){
        super(teksti);
    }
    @Override
    //Muuttaa napin väriä kun hiiri on sen yläpuolella
    protected void paintBorder(Graphics g){
        super.paintBorder(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setFont(new Font("Liberation Mono",Font.BOLD,20));
        if(this.getModel().isRollover()){
            g2.setPaint(Color.BLACK);
        }else{
            g2.setPaint(Color.WHITE);
        }
        g2.setStroke(new BasicStroke(2));
        g2.draw(new RoundRectangle2D.Double(2,2,this.getWidth()-4,this.getHeight()-4,1,1));
        g2.dispose();
    }
}
