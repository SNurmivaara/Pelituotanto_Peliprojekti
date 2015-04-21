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
package wildwestshootout;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import wildwestshootout.entity.mob.Player;
import wildwestshootout.graphics.Screen;
import wildwestshootout.input.Keyboard;
import wildwestshootout.input.Mouse;
import wildwestshootout.level.FirstLevel;
import wildwestshootout.level.Level;
import wildwestshootout.level.TileCoordinate;

/**
 *
 * @author Sami nurmivaara Nurmivaara
 */
public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    
    //Pelin resoluutio (leveys)
    private static int width = 300;
    
    //Pelin resoluutio (korkeus) joka on leveys / aspect ratio (tällä hetkellä aspect ratio 16:9
    private static int height = width / 16 * 9;
    
    //Pelin pikseleiden skaalaus kolminkertaiseksi
    private static int scale = 3;
    public static String title = "Wild West Shootout! T:SAMI";
    
    //Thread, JFrame, Keyboard, Level kutsu
    private Thread thread;
    JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    
    //Tarkistus onko peli käynnissä. True = käynnissä | False = suljettu
    private boolean running = false;
    
    //Screen luokan kutsu
    private Screen screen;
    
    
    //BufferedImage määritys sekä pixels-arrayn määritys. Pixels hakee kuvan pixeleiden määrän rasterin ja databufferin avulla getData() komennolla
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    
    
    public static int getWindowWidth() {
        return width * scale;
    }
    
    public static int getWindowHeight() {
        return height * scale;
    }
    
    
    //Pelin konstruktori
    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        
        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        level = new FirstLevel("/levels/FirstLevel.png");
        TileCoordinate playerSpawn = new TileCoordinate(31, 31);
        player = new Player(playerSpawn.x(), playerSpawn.y(), key);
        level.add(player);
        addKeyListener(key);
        
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
    
    
    //Pelin aloitusmetodi
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }
    
    
    //Pelin lopetusmetodi
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    //Pelin suoritusmetodi. Huom! run toimii sen takia että Game() luokka implements Runnable
    public void run() {
        
        //Kello pelin toimintaa varten
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        
        //Muuttujat Updates-per-second ja frames-per-second varten
        int updates = 0, frames = 0;
        
        requestFocus();
        
        while (running) {
            
            //Timer käynnistys
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            //Sekuntin välein päivitettävä UPS ja FPS laskuri joka näkyy ohjelman ikkunassa
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
            
        }
        stop();
    }
    
    
    public void update() {
        key.update();
        level.update();
    }
    
    
    //Renderöinti-metodi
    public void render() {
        //Luodaan kolminkertainen bufferi (Triple-Buffering)
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        //Tyhjennetään ruutu ja sen jälkeen renderöidään uudestaan
        screen.clear();
        int xScroll = player.getX() - screen.width / 2;
        int yScroll = player.getY() - screen.height / 2;
        level.render(xScroll, yScroll, this.screen);
        //screen.renderSheet(40, 40, SpriteSheet.player_right, false);
        
        //Kopioidaan pixels[] muuttujan arvot screen.pixels[] muuttujaan
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        
        //Piirretään kuva
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        
        game.start();
    }
    
}
