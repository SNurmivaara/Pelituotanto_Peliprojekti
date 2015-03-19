package wildwestshootout.graphics;

import java.util.Random;

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
    public void render() {
        for (int y = 0; y < height; y++) {
            int yy = y;
            
            if (yy >= height || yy < 0) break;
            
            for (int x = 0; x < width; x++) {
                int xx = x;
                if (xx >= width || xx < 0) break;
                int tileIndex = (xx >> 4 & MAP_SIZE_MASK) + (yy >> 4 & MAP_SIZE_MASK) * MAP_SIZE;
                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }
    
    
}
