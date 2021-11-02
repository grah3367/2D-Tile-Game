package project.game.tilegame.tiles;

import project.game.tilegame.gfx.Assets;

/**
 * Created by Dan on 4/5/2016.
 */
public class WaterTile extends Tiles {
    public WaterTile(int id) {
        super(Assets.water, id);
    }
    public boolean isSolid(){
        return true;
    }
}
