package wildwestshootout;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import wildwestshootout.graphics.Screen;
import wildwestshootout.input.Keyboard;

/**
 *
 * @author Sami
 */
public class Game extends Canvas implements Runnable {
    
    
    //Pelin resoluutio (leveys)
    public static int width = 300;
    
    //Pelin resoluutio (korkeus) joka on leveys / aspect ratio (tällä hetkellä aspect ratio 16:9
    public static int height = width / 16 * 9;
    
    //Pelin pikseleiden skaalaus kolminkertaiseksi
    public static int scale = 3;
    public static String title = "Wild West Shootout!";
    
    //Thread, JFrame, Keyboard kutsu
    private Thread thread;
    JFrame frame;
    private Keyboard key;
    
    //Tarkistus onko peli käynnissä. True = käynnissä | False = suljettu
    private boolean running = false;
    
    //Screen luokan kutsu
    private Screen screen;
    
    
    //BufferedImage määritys sekä pixels-arrayn määritys. Pixels hakee kuvan pixeleiden määrän rasterin ja databufferin avulla getData() komennolla
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    
    
    //Pelin konstruktori
    
    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        
        screen = new Screen(width, height);
        
        frame = new JFrame();
        
        key = new Keyboard();
        
        addKeyListener(key);
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
        
        
        while (running) {
            
            //Timer käynnistys
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
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
    
    //Yksinkertainen tick() metodi ja sen muuttujat x ja y
    int x = 0, y = 0;
    
    public void tick() {
        key.update();
        x++;
        y++;
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
        screen.render();
        
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
    //muutos
    //Pelin päämetodi
    /*
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
    */
    
}
