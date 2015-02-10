/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pelituotanto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author s1200481
 */
public class GamePanel extends JPanel implements KeyListener {
    
    private final static int GRID_WIDTH = 1;
    
    private final Layer layer = new Layer();
    
    Unit unit = new Unit(layer.getUnitLoc());
    
    private int camX, camY;
    
    private final int width, height;
    private final int cellSize;
    
    public GamePanel(int width, int height, int cellSize){
        int iWidth = width * cellSize + GRID_WIDTH;
        int iHeight = height * cellSize + GRID_WIDTH;
        
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        
        camX = (int)layer.getUnitLoc().getWidth() - width / 2;
        camY = (int)layer.getUnitLoc().getHeight() - height / 2;
        setInBounds();
        
        setPreferredSize(new Dimension(iWidth, iHeight));
        setFocusable(true);
        setOpaque(false);
        
        KeyListener kListener = this;
        addKeyListener(kListener);
    }
    
    @Override
    public void paintComponent(Graphics g){
        paintUnits(g);
        paintGrid(g);
    }
    
    private void paintUnits(Graphics g){
        for(int x = 0; x < width;x++)
            for(int y = 0; y < height;y++){
                if(layer.getId(x + camX, y + camY) == 1)
                    g.drawImage(unit.getImage(unit.getDir()), x * cellSize, y * cellSize, this);
                else
                    g.drawImage(images.get(layer.getId(x + camX, y + camY)), x * cellSize, y * cellSize, this);
            }
    }
    
    private void paintGrid(Graphics g){
        int gWidth = getWidth() / cellSize;
        int gHeight = getHeight() / cellSize;
        
        g.setColor(Color.GRAY);
        
        for(int x = 0; x <= gWidth; x++)
            g.fillRect(x * cellSize, 0, GRID_WIDTH, getHeight());
        
        for(int y = 0; y <= gHeight; y++)
            g.fillRect(0, y * cellSize, getWidth(), GRID_WIDTH);
    }
    
    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        boolean moved = false;
        
        int x = unit.getX();
        int y = unit.getY();
        
        switch(key){
            case KeyEvent.VK_UP : 
                y = y - (y > 0 ? 1 : 0);
                if(!collides(x,y))
                    moved = true;
                    unit.setDir(0);
                break;
            case KeyEvent.VK_RIGHT :
                x = x + (x < layer.getSize().getWidth() - 1 ? 1 : 0);
                if(!collides(x,y))
                    moved = true;
                    unit.setDir(1);
                break;
            case KeyEvent.VK_LEFT :
                x = x - (x > 0 ? 1 : 0);
                if(!collides(x,y))
                    moved = true;
                    unit.setDir(3);
                break;
            case KeyEvent.VK_DOWN :
                y = y + (y < layer.getSize().getHeight() - 1 ? 1 : 0);
                if(!collides(x,y))
                    moved = true;
                    unit.setDir(2);
                break;
        }
        
        if(moved){
            layer.setId(unit.getX(), unit.getY(), 0);
            layer.setId(x, y, 1);
            unit.setLoc(x, y);

            camX = x - width / 2;
            camY = y - height / 2;
            setInBounds();

            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
    }
    
    private boolean collides(int x, int y){
        if(layer.getId(x, y) != 0)
            return true;
        return false;
    }
    
    private void setInBounds(){
        camX = Math.max(camX, 0);
        camX = Math.min(camX, (int)layer.getSize().getWidth() - width);
        camY = Math.max(camY, 0);
        camY = Math.min(camY, (int)layer.getSize().getHeight() - height);
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
        add(null);
        add(null);
        add(initImage("images/White_Rook.png"));
    }};
}
