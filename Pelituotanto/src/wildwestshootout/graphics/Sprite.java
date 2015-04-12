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

    //Pelin spritet. Koko (pikseleitä X * X), Y koordinaatti, X koordinaatti, Spritesheet josta tekstuuri haetaan
    public static Sprite sand = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite concrete = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite sandCactus = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite sandRock = new Sprite(16, 3, 0, SpriteSheet.tiles);
    
    public static Sprite voidSprite = new Sprite(16, 0xFFF770FF);
    
    
    //Pelaaja-spritet
    public static Sprite player_front = new Sprite(32, 0, 5, SpriteSheet.tiles);
    public static Sprite player_back = new Sprite(32, 0, 6, SpriteSheet.tiles);
    public static Sprite player_left = new Sprite(32, 2, 4, SpriteSheet.tiles);
    public static Sprite player_right = new Sprite(32, 5, 7, SpriteSheet.tiles);
    
    public static Sprite player_front_1 = new Sprite(32, 2, 5, SpriteSheet.tiles);
    public static Sprite player_front_2 = new Sprite(32, 6, 5, SpriteSheet.tiles);
    
    public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
    public static Sprite player_back_2 = new Sprite(32, 6, 6, SpriteSheet.tiles);
    
    public static Sprite player_left_1 = new Sprite(32, 1, 4, SpriteSheet.tiles);
    public static Sprite player_left_2 = new Sprite(32, 3, 4, SpriteSheet.tiles);
    
    public static Sprite player_right_1 = new Sprite(32, 4, 7, SpriteSheet.tiles);
    public static Sprite player_right_2 = new Sprite(32, 6, 7, SpriteSheet.tiles);
    
    //Ammus-spritet
    public static Sprite revolverBullet = new Sprite(32, 0, 5, SpriteSheet.tiles); //Tällä hetkellä placeholderina hiekkaa!

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
