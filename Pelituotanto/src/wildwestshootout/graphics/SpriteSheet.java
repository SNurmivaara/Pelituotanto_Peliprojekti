package wildwestshootout.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Sami
 */
public class SpriteSheet {

    //Tekstuurit-kuvan lataaminen
    private String path;

    //kuvan koko ja pikselit sisältävä lista
    public final int SIZE;
    public int[] pixels;

    //Ladataan mahdolliset Sprite -taulukot (sijainti, koko)
    public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);

    //Konstruktori jolle määritellään tiedoston polku, koko sekä pikseli lista vastaamaan koko * koko
    public SpriteSheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        load();
    }

    //Ladataan tekstuurit-kuva
    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int width = image.getWidth();
            int height = image.getHeight();
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException ex) {
            Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
