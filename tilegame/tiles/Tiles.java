package project.game.tilegame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Dan on 4/5/2016.
 */
public class Tiles {

    public static Tiles[] tiles = new Tiles[265];
    public  static Tiles grassTile = new GrassTile(0);
    public  static Tiles dirtTile = new DirtTile(1);
    public  static Tiles rockTile = new RockTile(2);
    public static Tiles waterTile = new WaterTile(3);
    //public static Tiles treeTile = new TreeTile(4);




    public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tiles(BufferedImage texture, int id){

        this.texture = texture;
        this.id = id;

        tiles[id] = this;

    }

    public int getId(){ return id; }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null );
    }

    public boolean isSolid(){
        return false;
    }


}
