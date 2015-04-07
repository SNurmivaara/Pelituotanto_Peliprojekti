package wildwestshootout.entity.mob;

import wildwestshootout.entity.Entity;
import wildwestshootout.graphics.Sprite;

/**
 *
 * @author Sami
 */
public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int direction = 0;
    protected boolean moving = false;

    public void move(int xa, int ya) {
        if (xa > 0) {
            this.direction = 1;
        }
        if (xa < 0) {
            this.direction = 3;
        }
        if (ya > 0) {
            this.direction = 2;
        }
        if (ya < 0) {
            this.direction = 0;
        }

        if (!collision()) {
            x += xa;
            y += ya;
        }
    }

    @Override
    public void update() {

    }

    private boolean collision() {
        return false;
    }

    public void render() {
    }

}
