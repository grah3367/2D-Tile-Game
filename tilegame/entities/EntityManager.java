package project.game.tilegame.entities;

import project.game.tilegame.Handler;
import project.game.tilegame.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;


/**
 * @author Dan
 * @description Entities adding and removing, also drawing them in a particular order.
 */
public class EntityManager {

    private Player player;
    private Handler handler;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderLayers = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public void tick(){
        for(int i=0;i < entities.size();i++){
            Entity e = entities.get(i);
            e.tick(handler.getDeltaTime());
        }
        entities.sort(renderLayers);
       // player.tick(handler.getDeltaTime());
    }

    public void render(Graphics g){
        for(Entity e : entities){
            e.render(g);
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    /*
     * Getters and setters
     */

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Player getPlayer() {

        return player;
    }

    public Handler getHandler() {
        return handler;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }


}
