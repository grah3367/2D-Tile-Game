package project.game.tilegame.entities.staticEntities;

import project.game.tilegame.Handler;
import project.game.tilegame.entities.Entity;

import java.awt.*;

/**
 * Created by Dan on 4/8/2016.
 */
public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

    }


}
