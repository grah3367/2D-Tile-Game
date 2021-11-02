package project.game.tilegame.tiles;

import project.game.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Dan on 4/5/2016.
 */
public class DirtTile extends Tiles {
    public DirtTile( int id) {
        super(Assets.dirt, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
