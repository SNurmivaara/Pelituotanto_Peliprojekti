package wildwestshootout.level.tile;

import wildwestshootout.graphics.Screen;
import wildwestshootout.graphics.Sprite;

/**
 *
 * @author Sami
 */
public class Tile {

    public int x, y;
    public Sprite sprite;

    public static Tile sand = new SandTile(Sprite.sand);
    public static Tile concrete = new ConcreteTile(Sprite.concrete);
    public static Tile sandCactus = new SandCactusTile(Sprite.sandCactus);
    public static Tile sandRock = new SandRockTile(Sprite.sandRock);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
    }

    public boolean solid() {
        return false;
    }
}
