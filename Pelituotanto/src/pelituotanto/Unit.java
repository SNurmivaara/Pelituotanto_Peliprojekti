/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pelituotanto;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author s1200481
 */
public class Unit {
    private int id;
    private Dimension loc;
    private int dir = 0;
    
    public Unit(Dimension loc){
        this.loc = loc;
    }

    public Dimension getLoc() {
        return loc;
    }

    public void setLoc(Dimension loc) {
        this.loc = loc;
    }
    
    public int getX(){
        return (int)loc.getWidth();
    }
    
    public int getY(){
        return (int)loc.getHeight();
    }
    
    public void setLoc(int x, int y){
        loc = new Dimension(x,y);
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
    
    public BufferedImage getImage(int i){
        return images.get(i);
    }
    
    public BufferedImage initImage(String path){
        BufferedImage img = null;
        try{
            img = ImageIO.read(getClass().getResource(path));
        }catch(IOException e){
            System.out.println(e);
        }
        return img;
    }
    
    private final List<BufferedImage> images = new ArrayList() {{
        add(initImage("images/Haulikkomies_up.png"));
        add(initImage("images/Haulikkomies_right.png"));
        add(initImage("images/Haulikkomies_down.png"));
        add(initImage("images/Haulikkomies_left.png"));
    }};
}
