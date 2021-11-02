package project.game.tilegame.gfx;

import project.game.tilegame.Game;
import project.game.tilegame.Handler;
import project.game.tilegame.entities.Entity;
import project.game.tilegame.tiles.Tiles;

/**
 * Created by Dan on 4/5/2016.
 */
public class GameCamera {
    private Handler handler;
    private float xOffset, yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = xOffset;
    }

    public void move(float xAmt, float yAmt){
        xOffset +=xAmt;
        yOffset +=yAmt;
        checkBlankSpace();
    }

    public void checkBlankSpace(){
        if(xOffset < 0) {
            xOffset =0;
        } else if( xOffset > handler.getWorld().getWidth() * Tiles.TILE_WIDTH - handler.getWidth()){
            xOffset =  handler.getWorld().getWidth() * Tiles.TILE_WIDTH - handler.getWidth();
        }
        if(yOffset < 0) {
            yOffset = 0;
        } else if(yOffset > handler.getWorld().getHeight() * Tiles.TILE_HEIGHT - handler.getHeight()){
            yOffset = handler.getWorld().getHeight() * Tiles.TILE_HEIGHT- handler.getHeight();
        }
    }

    public void centerOnEntity(Entity e){
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public float getxOffset() {

        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }
}
