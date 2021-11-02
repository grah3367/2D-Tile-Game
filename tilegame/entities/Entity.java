package project.game.tilegame.entities;

import project.game.tilegame.Game;
import project.game.tilegame.Handler;
import project.game.tilegame.entities.creatures.NPC;
import project.game.tilegame.entities.creatures.Player;

import java.awt.*;

/**
 * @author Dan
 * @description  base entity class with collisions
 */
public abstract class Entity {

    protected Handler handler;
    protected float x,y;
    protected int width, height;
    protected Rectangle boundingBox;

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        boundingBox = new Rectangle(0,0, width, height);


    }
    // todo collision code
    public boolean checkEntityCollisions(double xOffset, double yOffset){
        // get list of entities and loop through
        for( Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this)) continue;

            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(double xOffset, double yOffset){
        return new Rectangle((int)(x + boundingBox.x + xOffset),
                            (int)(y + boundingBox.y + yOffset),
                             boundingBox.width,
                             boundingBox.height);
    }

    public void drawBoundingBox(Graphics g, Entity e, Color color){
        g.setColor(color);
        g.fillRect( (int) (e.x + e.boundingBox.x - handler.getGameCamera().getxOffset()),
                (int) (e.y + e.boundingBox.y - handler.getGameCamera().getyOffset()),
                e.boundingBox.width,
                e.boundingBox.height);

        g.setColor(Color.black);
    }

    public void drawTriggerBox(Graphics g, Player e, Rectangle rec, Color color){
        g.setColor(color);
        g.fillRect( (int) ( rec.x - handler.getGameCamera().getxOffset()),
                (int) ( rec.y - handler.getGameCamera().getyOffset()),
                rec.width,
                rec.height);

        g.setColor(Color.black);
    }



    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getX() {

        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void tick(double delta);
    public abstract void render(Graphics g);
}
