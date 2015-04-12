package wildwestshootout.level;

import java.util.ArrayList;
import java.util.List;
import wildwestshootout.entity.Entity;
import wildwestshootout.entity.Projectile;
import wildwestshootout.graphics.Screen;
import wildwestshootout.level.tile.Tile;

/**
 *
 * @author Sami
 */
public class Level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;

    private List<Entity> entities = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel() {
    }

    protected void loadLevel(String path) {
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
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
                getTile(x, y).render(x, y, screen);
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
        }
    }

    public void add(Entity e) {
        entities.add(e);
    }

    public void addProjectile(Projectile p) {
        p.init(this);
        projectiles.add(p);
    }

    public Tile getTile(int x, int y) {
        //Palautetaan voidTile jos poistutaan kartalta
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) {
            return Tile.voidTile;
        }

        /**
         * Hiekka = 0xFFFFFF00 Betoni = 0xFF808080 Kaktus hiekalla = 0xFFAAD800
         * Kivi hiekalla = 0xFFCEAC00
         */
        if (tiles[x + y * width] == Tile.col_sand) {
            return Tile.sand;
        }
        if (tiles[x + y * width] == Tile.col_concrete) {
            return Tile.concrete;
        }
        if (tiles[x + y * width] == Tile.col_sandCactus) {
            return Tile.sandCactus;
        }
        if (tiles[x + y * width] == Tile.col_sandRock) {
            return Tile.sandRock;
        }

        return Tile.voidTile;
    }

    public boolean tileCollision(double x, double y, double xa, double ya, int size) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = (((int)x + (int)xa) + c % 2 * size / 4 - 5) / 16;
            int yt = (((int)y + (int)ya) + c / 2 * size / 2 + 6) / 16;
            if (getTile(xt, yt).solid()) {
                solid = true;
            }
        }
        return solid;
    }
}
