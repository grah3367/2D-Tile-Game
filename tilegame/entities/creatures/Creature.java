package project.game.tilegame.entities.creatures;


import project.game.tilegame.Handler;
import project.game.tilegame.entities.Entity;
import project.game.tilegame.tiles.Tiles;

/**
 * Created by Dan on 3/14/2016.
 * @author Dan
 * @description This is the parent class for all dynamic entities  such as players & enemies etc.
 */
public  abstract class Creature extends Entity {

    public final static int default_health = 100;
    public final static double default_speed = 150.0;
    public final static int default_creature_width = 64,
                            default_creature_height = 64;

    protected int health;
    protected double speed;
    protected double xMove; protected double yMove;
    public boolean colWithEnt = false;


    public Creature(Handler handler, float x, float y, int width, int height) {

        super(handler, x, y, width, height);
        health = default_health;
        speed = default_speed;
        xMove = 0;
        yMove = 0;


    }

    public void move(double dt){

        if(!checkEntityCollisions(xMove, 0f)){
            colWithEnt = false;
            moveX(dt);
        } else { // colliding with an entity
                colWithEnt = true;
        }
        // only move y if x is zero. restricts diagonal movement
        if(xMove == 0) {
            if (!checkEntityCollisions(0f, yMove)) {
                colWithEnt = false;
                moveY(dt);
            } else{ // colliding with an entity
                colWithEnt = true;
            }
        }
    }

    public void moveX(double dt){

        // rudimentary collision detection :/
        if(xMove > 0){ //moving right
            int tx = (int) (x + xMove + boundingBox.x + boundingBox.width) / Tiles.TILE_WIDTH;

            if(!collisionWithTile(tx, (int) (y + boundingBox.y ) / Tiles.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + boundingBox.y + boundingBox.height) / Tiles.TILE_HEIGHT)){
                x += xMove;
            }
        } else if(xMove < 0){ //moving left
            int tx = (int) (x + xMove + boundingBox.x) / Tiles.TILE_WIDTH;

            if(!collisionWithTile(tx, (int) (y + boundingBox.y ) / Tiles.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + boundingBox.y + boundingBox.height) / Tiles.TILE_HEIGHT)){
                x += xMove;
            }
        }
       // x += xMove*dt;
    }
    public void moveY(double dt){ // up
        if(yMove < 0){
            int ty = (int) (y + yMove + boundingBox.y) /  Tiles.TILE_HEIGHT;
            if(!collisionWithTile((int) (x + boundingBox.x) / Tiles.TILE_WIDTH,  ty) &&
            !collisionWithTile((int) (x + boundingBox.x + boundingBox.width) / Tiles.TILE_WIDTH,  ty)){
                y += yMove;
            }


        } else if (yMove > 0){ // down

            int ty = (int) (y + yMove + boundingBox.y + boundingBox.height) /  Tiles.TILE_HEIGHT;
            if(!collisionWithTile((int) (x + boundingBox.x) / Tiles.TILE_WIDTH,  ty) &&
                    !collisionWithTile((int) (x + boundingBox.x + boundingBox.width) / Tiles.TILE_WIDTH,  ty)){
                y += yMove;
            }

        }

    }

    public void interactAction(){
        // this method allows for interaction between entities.

    }

    protected boolean collisionWithTile(int x, int y){

        //System.out.println("x = "+ x + ", y = " + y);
       // System.out.println(handler.getWorld().getWorldName());


        return handler.getWorld().getTile(x,y).isSolid();
    }




    // Getters and setters

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getHealth() {

        return health;
    }

    public double getSpeed() {
        return speed;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public double getxMove() {

        return xMove;
    }

    public double getyMove() {
        return yMove;
    }
}
