package wildwestshootout.graphics;

import java.util.Random;
import wildwestshootout.level.tile.Tile;

/**
 *
 * @author Sami
 */
public class Screen {
    
    //Määritetään muuttujat width, height ja pixels[]
    private int width, height;
    public int[] pixels;
    
    //Kerrotaan pelille kartan koko
    public final int MAP_SIZE = 64;
    
    //Luodaan kartan koosta "maski"
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    
    //Määritellään montako ruutua pelikentässä on
    public int[] tiles = new int[64 * 64];
    
    //Määritellään Random() metodi
    private Random random = new Random();
    
    
    //Piirretään kentälle ruutuja. Tällä hetkellä piirtää vain random luokalla generoituja erivärisiä ruutuja
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        
        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }
    
    //Tyhjennetään ruutu
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
    
    
    //Renderöidään kuva
    public void render(int xOffset, int yOffset) {
        for (int y = 0; y < height; y++) {
            int yp = y + yOffset;
            if (yp < 0 || yp >= height) continue;
            for (int x = 0; x < width; x++) {
                int xp = x + xOffset;
                if (xp < 0 || xp >= width) continue;
                pixels[xp + yp * width] = Sprite.sand.pixels[(x & 15) + (y & 15) * Sprite.sand.SIZE];
            }
        }
    }
    
    
    public void renderTile(int xp, int yp, Tile tile) {
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yAbsolute = y + yp;
        }
    }
    
    
}
