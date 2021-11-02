package project.game.tilegame.tiles;

import project.game.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Dan on 4/5/2016.
 */
public class RockTile extends Tiles {
    public RockTile(int id) {
        super(Assets.southWall, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
