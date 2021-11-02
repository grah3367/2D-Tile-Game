package project.game.tilegame.entities.creatures;

import project.game.tilegame.Game;
import project.game.tilegame.Handler;
import project.game.tilegame.entities.Entity;
import project.game.tilegame.gfx.*;
import project.game.tilegame.gfx.Dialog;
import project.game.tilegame.input.KeyManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Color.*;

/**
 * Created by Dan on 3/14/2016.
 *  @author Dan
 *  @discription The player with movement, animation, and collisions
 */
public class Player extends Creature {


    //private Game game;
    private Animation player_anim_down;
    private Animation player_anim_up;
    private Animation player_anim_left;
    private Animation player_anim_right;
    Dialog dialog;

    // default player position facing camera
    public BufferedImage lastKey=Assets.hero_down[0];

    //
    public Rectangle useTriggerLeft, useTriggerRight, useTriggerUp, useTriggerDown;
    private int triggerSize = 5;

    private double velX, Vely=0;

    public Player(Handler handler, float x, float y) {
        super(handler,x, y, Creature.default_creature_width, Creature.default_creature_height);

        // collision bounding box
        boundingBox.x = 18;
        boundingBox.y = 35;
        boundingBox.width = 28;
        boundingBox.height = 27;

        // create triggers
        useTriggerLeft = new Rectangle(0,0, width, height);
        useTriggerRight = new Rectangle(0,0, width, height);
        useTriggerUp = new Rectangle(0,0, width, height);
        useTriggerDown = new Rectangle(0,0, width, height);

        // Build the Left Side Trigger
       // e.x + e.boundingBox.x - handler.getGameCamera().getxOffset()

        useTriggerLeft.width = triggerSize ;
        useTriggerLeft.height = boundingBox.height;

        //Build the Right Side Trigger

        useTriggerRight.width = triggerSize;
        useTriggerRight.height = boundingBox.height;

        // Build the Up Side Trigger
        useTriggerUp.width = boundingBox.width;
        useTriggerUp.height = triggerSize;

        // Build the Down Side Trigger
        useTriggerDown.width = boundingBox.width;
        useTriggerDown.height = triggerSize;

        //implement player animations
        player_anim_down = new Animation(200,Assets.hero_down);
        player_anim_up = new Animation(200,Assets.hero_up);
        player_anim_left = new Animation(200,Assets.hero_left);
        player_anim_right = new Animation(200,Assets.hero_right);



    }

    @Override
    public void tick(double delta) {
        updateTriggerPositions();
        getInput(delta);
        move(delta);
        handler.getGameCamera().centerOnEntity(this);


    }

    private void getInput(double dt){
        xMove = 0;
        yMove = 0;
        if(handler.getDialog() == null || !handler.getDialog().dVisible) {
            if (handler.getKeyman().up) {
                yMove = -speed * dt;
                lastKey = Assets.hero_up[0];
                player_anim_up.tick();


            }
            if (handler.getKeyman().down) {
                yMove = speed * dt;
                lastKey = Assets.hero_down[0];
                player_anim_down.tick();

                // reset facingtrigger

            }
            if (handler.getKeyman().left) {
                xMove = -speed * dt;
                player_anim_left.tick();
                lastKey = Assets.hero_left[0];

                if (lastKey == Assets.hero_left[0]) {

                }
            }
            if (handler.getKeyman().right) {
                xMove = speed * dt;
                player_anim_right.tick();
                lastKey = Assets.hero_right[0];
            }

            //interaction
            if (handler.getKeyman().use) {

                Rectangle trigger = null;
                Boolean triggered = false;

                // is the player facing the entity and the entity within range?
                if (lastKey == Assets.hero_right[0] && checkTriggerCollisions(useTriggerRight)) {
                    trigger = useTriggerRight;
                    triggered = true;
                }
                if (lastKey == Assets.hero_left[0] && checkTriggerCollisions(useTriggerLeft)) {
                    trigger = useTriggerLeft;
                    triggered = true;
                }
                if (lastKey == Assets.hero_up[0] && checkTriggerCollisions(useTriggerUp)) {
                    trigger = useTriggerUp;
                    triggered = true;
                }
                if (lastKey == Assets.hero_down[0] && checkTriggerCollisions(useTriggerDown)) {
                    trigger = useTriggerDown;
                    triggered = true;
                }

                if (!(trigger == null) && triggered) {
                    // get the object
                    Entity e = getTriggeredEntity(trigger);
                    // if we have an NPC object
                    if (e instanceof NPC) {
                        // ((NPC) e).interactAction();
                        ((NPC) e).OnTalk(trigger);
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        //updateTriggerPositions();
        /*
        this.drawBoundingBox(g, this, Color.RED);
        this.drawTriggerBox(g, this, useTriggerLeft , new Color(255, 102, 51, 155));
        this.drawTriggerBox(g, this, useTriggerRight, new Color(255, 102, 51, 155));
        this.drawTriggerBox(g, this, useTriggerUp, new Color(204,51,255,155));
        this.drawTriggerBox(g, this, useTriggerDown, new Color(204,51,255,155));
        */
     //   this.drawBoundingBox(g, this, Color.RED);


        g.drawImage(getCurrentAnimationFrame(),
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

      /* g.setColor(Color.orange);
       g.fillRect( (int) (x + boundingBox.x - handler.getGameCamera().getxOffset()),
             (int) (y + boundingBox.y - handler.getGameCamera().getyOffset()),
                boundingBox.width,
               boundingBox.height);

       g.setColor(Color.black);

         // render everything that is triggered. This was used to test if entities are colliding
        for( Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this)) continue;
            if (useTriggerRight.intersects(e.getCollisionBounds(0, 0))) {
                System.out.println("x: " + useTriggerRight.getX() + " y : " + useTriggerRight.getY());
                System.out.println("height: " +  useTriggerRight.getHeight() + " width: " + useTriggerRight.getWidth());
               drawBoundingBox(g, e, Color.CYAN);
            }
        }
        */

    }

    private Entity getTriggeredEntity(Rectangle r){

        for( Entity e : handler.getWorld().getEntityManager().getEntities()){
            //if(e.equals(this)) continue;
            if(r.intersects(e.getCollisionBounds(0,0))){
                //System.out.println(e);
                return e;
            }
        }
        return null;
    }

    private BufferedImage getCurrentAnimationFrame(){

        // todo maybe only tick animation if key is held?

        if(xMove == 0 && yMove == 0){
            return lastKey;
        }
        if(xMove < 0) {
            return player_anim_left.getCurrentFrame();
        } else if(xMove > 0){
            return player_anim_right.getCurrentFrame();
        } else if(yMove < 0){
            return player_anim_up.getCurrentFrame();
        } else {
            return player_anim_down.getCurrentFrame();
        }
    }


    // todo
    public boolean checkTriggerCollisions(Rectangle rec){
        // get list of entities and loop through
        for( Entity e : handler.getWorld().getEntityManager().getEntities()){
            //if(e.equals(this)) continue;
            if(rec.intersects(e.getCollisionBounds(0,0))){
                //System.out.println(e);
                return true;
            }
        }
        return false;
    }

    private void updateTriggerPositions(){
        // left
        useTriggerLeft.x = ( (int)(((this.x + boundingBox.x)  - triggerSize)));
       // useTriggerLeft.x += xMove;
        useTriggerLeft.y = (int)this.y + boundingBox.y;
       // useTriggerLeft.y += yMove;

        // right
        useTriggerRight.x = ( (int) (this.x + boundingBox.x + boundingBox.width ));
       // useTriggerRight.x += xMove;
        useTriggerRight.y = ((int)this.y + boundingBox.y);
       // useTriggerRight.y += yMove;

        // up
        useTriggerUp.x = (int) this.x + boundingBox.x;
       // useTriggerUp.x += xMove;
        useTriggerUp.y = (int) this.y + boundingBox.y  - triggerSize;
      //  useTriggerUp.y += yMove;

        //down
        useTriggerDown.x = (int)this.x + boundingBox.x;
      //  useTriggerDown.x += xMove;
        useTriggerDown.y = (int)this.y + boundingBox.y + boundingBox.height;
      //  useTriggerDown.y += yMove;


    }


    @Override
    public void interactAction(){

    }
}
