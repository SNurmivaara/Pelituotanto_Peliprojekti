package wildwestshootout.level;

import wildwestshootout.graphics.Screen;
import wildwestshootout.level.tile.Tile;

/**
 *
 * @author Sami
 */
public class Level {

    protected Tile[] tiles;
    protected int width, height;
    protected int[] tilesInt;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    protected void generateLevel() {
    }

    protected void loadLevel(String path) {
    }

    public void update() {
    }

    private void time() {
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4; // ">> 4" == "/ 16"
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
//              getTile(x, y).render(x, y, screen);
                if (x < 0 || y < 0 || x >= this.width || y >= this.height) {
                    Tile.voidTile.render(x, y, screen);
                } else {
                    tiles[x + y * 16].render(x, y, screen);
                }

            }
        }
    }

    public Tile getTile(int x, int y) {
        //Palautetaan voidTile jos poistutaan kartalta
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) {
            return Tile.voidTile;
        }

        /**
         * Päätellään mikä Tile palautetaan 0 = hiekka 1 = betoni 2 = hiekka
         * jolla on kaktus 3 = hiekka jolla on kivi mikäli numero ei vastaa
         * mitään spriteä palautetaan voidTile
         */
        if (tilesInt[x + y * width] == 0) {
            return Tile.sand;
        }
        if (tilesInt[x + y * width] == 1) {
            return Tile.concrete;
        }
        if (tilesInt[x + y * width] == 2) {
            return Tile.sandCactus;
        }
        if (tilesInt[x + y * width] == 3) {
            return Tile.sandRock;
        }

        return Tile.voidTile;
    }
}
