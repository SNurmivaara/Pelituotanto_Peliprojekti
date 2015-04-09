package wildwestshootout.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import wildwestshootout.level.tile.Tile;

/**
 *
 * @author Sami
 */
public class FirstLevel extends Level {

    private Tile[] tiles;
    private int[] levelPixels;
    
    public FirstLevel(String path) {
        super(path);
    }
    
    @Override
    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(FirstLevel.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            tiles = new Tile[w * h];
            image.getRGB(0, 0, w, h, levelPixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Virhe! Ei pystytty ladata level-tiedostoa!");
        }
    }
        
    
    /** 
     * Hiekka = 0xFFFF00
     * Betoni = 0x808080
     * Kaktus hiekalla = 0xAAD800
     * Kivi hiekalla = 0xCEAC00
     **/
    @Override
    protected void generateLevel() {
        
        for (int i = 0; i < levelPixels.length; i++) {
            if (levelPixels[i] == 0xFFFF00) {
                tiles[i] = Tile.sand;
            }
            if (levelPixels[i] == 0x808080) {
                tiles[i] = Tile.concrete;
            }
            if (levelPixels[i] == 0xAAD800) {
                tiles[i] = Tile.sandCactus;
            }
            if (levelPixels[i] == 0xCEAC00) {
                tiles[i] = Tile.sandRock;
            }
        }
    }
    
}
