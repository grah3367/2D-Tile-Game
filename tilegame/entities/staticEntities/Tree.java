package project.game.tilegame.entities.staticEntities;

import project.game.tilegame.Handler;
import project.game.tilegame.gfx.Assets;
import project.game.tilegame.tiles.Tiles;

import java.awt.*;

/**
 * Created by Dan on 4/8/2016.
 */
public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, 64, 128);

        // todo set the bounding box for the tree.
        boundingBox.x = 12;
        boundingBox.y = (int) (height /1.5);
        boundingBox.width = width -25;
        boundingBox.height = (int) (height - height /1.2f);


    }



    @Override
    public void tick(double delta) {

    }


    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x -handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //drawBoundingBox(g, this, Color.blue);
    }

}
