/* 
 * Copyright (C) 2015 Sami Nurmivaara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package wildwestshootout.level;

import java.util.ArrayList;
import java.util.List;
import wildwestshootout.entity.Entity;
import wildwestshootout.entity.mob.Player;
import wildwestshootout.entity.particle.Particle;
import wildwestshootout.entity.projectile.Projectile;
import wildwestshootout.graphics.Screen;
import wildwestshootout.level.tile.Tile;

/**
 *
 * @author Sami nurmivaara
 */
public class Level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    private int time;

    private List<Entity> entities = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Particle> particles = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

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
        if (time >= Integer.MAX_VALUE - 10) {
            time = 0;
        } else {
            time++;
        }
        
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).update();
        }

        remove();
    }

    private void remove() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) {
                entities.remove(i);
            }
        }
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) {
                projectiles.remove(i);
            }
        }
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) {
                particles.remove(i);
            }
        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isRemoved()) {
                players.remove(i);
            }
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
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).render(screen);
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).render(screen);
        }
    }

    public void add(Entity e) {
        e.init(this);
        if (e instanceof Particle) {
            particles.add((Particle) e);
        } else if (e instanceof Projectile) {
            projectiles.add((Projectile) e);
        } else if (e instanceof Player) {
            players.add((Player) e);
        } else {
            entities.add(e);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerAt(int index) {
        return players.get(index);
    }

    public Player getClientPlayer() {
        return players.get(0);
    }

    public List<Entity> getEntities(Entity e, int radius) {
        List<Entity> result = new ArrayList<>();
        double ex = e.getX();
        double ey = e.getY();

        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            double x = entity.getX();
            double y = entity.getY();

            double dx = Math.abs(x - ex);
            double dy = Math.abs(y - ey);

            double distance = Math.sqrt((dx * dx) + (dy * dy));

            if (distance <= radius) {
                result.add(entity);
            }
        }

        return result;
    }

    public List<Player> getPlayers(Entity e, int radius) {
        List<Player> result = new ArrayList<>();
        double ex = e.getX();
        double ey = e.getY();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            double x = player.getX();
            double y = player.getY();

            double dx = Math.abs(x - ex);
            double dy = Math.abs(y - ey);

            double distance = Math.sqrt((dx * dx) + (dy * dy));

            if (distance <= radius) {
                result.add(player);
            }
        }
        return result;
    }

    public List<Projectile> getProjectiles(Entity e, int radius) {
        List<Projectile> result = new ArrayList<>();
        double ex = e.getX();
        double ey = e.getY();

        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            double x = projectile.getX();
            double y = projectile.getY();

            double dx = Math.abs(x - ex);
            double dy = Math.abs(y - ey);

            double distance = Math.sqrt((dx * dx) + (dy * dy));

            if (distance <= radius) {
                result.add(projectile);
            }
        }
        return result;
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
        if (tiles[x + y * width] == Tile.col_fenceVertical) {
            return Tile.fenceVertical;
        }
        if (tiles[x + y * width] == Tile.col_fenceHorizontal) {
            return Tile.fenceHorizontal;
        }
        if (tiles[x + y * width] == Tile.col_fenceSE) {
            return Tile.fenceSE;
        }
        if (tiles[x + y * width] == Tile.col_fenceSW) {
            return Tile.fenceSW;
        }
        if (tiles[x + y * width] == Tile.col_fenceNW) {
            return Tile.fenceNW;
        }
        if (tiles[x + y * width] == Tile.col_fenceNE) {
            return Tile.fenceNE;
        }
        /*
         fenceVertical = new Sprite(16, 4, 0, SpriteSheet.tiles);
         public static Sprite fenceHorizontal = new Sprite(16, 5, 0, SpriteSheet.tiles);
         public static Sprite fenceSE = new Sprite(16, 6, 0, SpriteSheet.tiles);
         public static Sprite fenceSW = new Sprite(16, 7, 0, SpriteSheet.tiles);
         public static Sprite fenceNW = new Sprite(16, 8, 0, SpriteSheet.tiles);
         public static Sprite fenceNE*/

        return Tile.voidTile;
    }

    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = (x - c % 2 * size + xOffset) >> 4;
            int yt = (y - c / 2 * size + yOffset) >> 4;
            if (getTile(xt, yt).solid()) {
                solid = true;
            }
        }
        return solid;
    }
}
