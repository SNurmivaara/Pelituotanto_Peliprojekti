package wildwestshootout.entity.particle;

import wildwestshootout.entity.Entity;
import wildwestshootout.graphics.Screen;
import wildwestshootout.graphics.Sprite;

/**
 *
 * @author Sami
 */
public class Particle extends Entity {

    private Sprite sprite;

    private int life;

    protected double xx, yy, xa, ya;

    public Particle(int x, int y, int life) {
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;
        this.life = life;
        sprite = Sprite.particle_normal;

        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
    }

    @Override
    public void update() {
        this.xx += xa;
        this.yy += ya;
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int) xx, (int) yy, sprite, true);
    }

}
