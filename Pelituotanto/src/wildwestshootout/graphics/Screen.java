package wildwestshootout.graphics;

import java.util.Random;
import wildwestshootout.level.tile.Tile;

/**
 *
 * @author Sami
 */
public class Screen {

    //Määritetään muuttujat width, height ja pixels[]
    public int width, height;
    public int[] pixels;

    //Kerrotaan pelille kartan koko
    public final int MAP_SIZE = 64;

    //Luodaan kartan koosta "maski"
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;

    //Luodaan muuttujat kartan offsetille
    public int xOffset, yOffset;

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

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= this.xOffset;
        yp -= this.yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yAbsolute = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xAbsolute = x + xp;
                if (xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) {
                    break;
                }
                
                if (xAbsolute < 0) {
                    xAbsolute = 0;
                }
                
                pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
