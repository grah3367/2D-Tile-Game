package project.game.tilegame.worlds;

import project.game.tilegame.Handler;
import project.game.tilegame.entities.EntityManager;
import project.game.tilegame.entities.creatures.Player;
import project.game.tilegame.entities.staticEntities.Tree;
import project.game.tilegame.tiles.Tiles;
import project.game.tilegame.utils.Utils;

import java.awt.*;

/**
 * @author Dan
 * @description handles world creation and loading.
 */
public class World {
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private String worldName = "";

    public EntityManager getEntityManager() {
        return entityManager;
    }

    private EntityManager entityManager;

    public String getWorldName() {
        return worldName;
    }

    public World(Handler handler, String path, String name){
        this.handler = handler;
        this.worldName = name;
        entityManager = new EntityManager(handler, new Player(handler,0,0));



        // create trees at the top of the world.


        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);



    }
    public void tick(){
        entityManager.tick();
    }

    public void render(Graphics g){

        int xCurrentViewStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tiles.TILE_WIDTH),
                xCurrentViewStop = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tiles.TILE_WIDTH+1),
                yCurrentViewStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tiles.TILE_HEIGHT),
                yCurrentViewStop = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getWidth()) / Tiles.TILE_HEIGHT+1);


        for(int y=yCurrentViewStart;y < yCurrentViewStop;y++){
            for(int x= xCurrentViewStart;x < xCurrentViewStop;x++){
                getTile(x,y).render(g,(int) (x * Tiles.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                                            (int)(y * Tiles.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        // Entities
        entityManager.render(g);

    }

    public Tiles getTile(int x, int y){
        if( x < 0 || y < 0 || x >= width || y >= height ){
            return Tiles.grassTile;
        }

        Tiles t = Tiles.tiles[tiles[x][y]];
        if(t == null)
            return Tiles.dirtTile;
        return t;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void loadWorld(String path){

        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }


        }




    }


}
