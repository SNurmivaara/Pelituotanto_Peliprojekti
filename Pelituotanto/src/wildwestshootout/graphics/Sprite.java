package wildwestshootout.graphics;

/**
 *
 * @author Sami
 */
public class Sprite {

    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    //Sprite -lisääminen! Täyttö: (Koko (pikseleitä X * X), Y koordinaatti, X koordinaatti, Spritesheet joka kyseessä)
    public static Sprite sand = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16, 0);

    //Konstruktori
    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    
    //Vaihtoehtoinen konstruktori
    public Sprite(int size, int color) {
        this.SIZE = size;
        this.pixels = new int[this.SIZE * this.SIZE];
        setColor(color);
    }

    //Värin asettaminen
    private void setColor(int color) {
        for (int i = 0; i < this.SIZE * this.SIZE; i++) {
            this.pixels[i] = color;
        }
    }

    //load() metodi joka lataa tietyt pikselit (tietyn Spriten määritellystä SpriteSheet:istä
    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

}
