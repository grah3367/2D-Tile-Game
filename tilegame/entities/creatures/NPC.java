package project.game.tilegame.entities.creatures;


import project.game.tilegame.Handler;
import project.game.tilegame.gfx.*;
import project.game.tilegame.gfx.Dialog;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Dan on 4/17/2016.
 * @author Dan
 * @description Non-Player Character with basic movement logic. includes the ability to be talked to and animation
 */
public class NPC extends Creature {

    private Animation npc_anim_down;
    private Animation npc_anim_up;
    private Animation npc_anim_left;
    private Animation npc_anim_right;

    private  int timer = 0;

    BufferedImage facingDirection=Assets.npc_left[0];

    String[] strArray;
    String npcName = "";

    Dialog dialog;

    Random random = new Random();

    public NPC(Handler handler, float x, float y, int width, int height,String name, String[] strArray) {
        super(handler, x, y, width, height);
        boundingBox.x = 18;
        boundingBox.y = 35;
        boundingBox.width = 25;
        boundingBox.height = 27;


        //implement player animations
        npc_anim_down = new Animation(200, Assets.npc_down);
        npc_anim_up = new Animation(200,Assets.npc_up);
        npc_anim_left = new Animation(200,Assets.npc_left);
        npc_anim_right = new Animation(200,Assets.npc_right);

        // NPC specifics
        this.strArray = strArray;
        this.npcName = name;
        this.speed = 100;
        xMove=speed*handler.getDeltaTime();

    }

    @Override
    public void tick(double delta) {
        timer++;
        if(dialog == null || !dialog.dVisible) {
            if (timer % (random.nextInt(20) + 20) == 0) {
                moveRandomly();

            }
        }
        npc_anim_down.tick();
        npc_anim_up.tick();
        npc_anim_left.tick();
        npc_anim_right.tick();
        getInput(delta);
        move(delta);


    }

    private void getInput(double dt){


        if(yMove < 0){
            yMove = -speed * dt;
            facingDirection =  Assets.npc_up[0];
        }
        if(yMove > 0) {
            yMove = speed * dt;
            facingDirection =  Assets.npc_down[0];
        }
        if(xMove < 0) {
            xMove = -speed * dt;
            facingDirection =  Assets.npc_left[0];
        }
        if(xMove > 0) {
            xMove = speed * dt;
            facingDirection =  Assets.npc_right[0];
        }



    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getAnimFrame(),
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

      /* g.setColor(Color.orange);
       g.fillRect( (int) (x + boundingBox.x - handler.getGameCamera().getxOffset()),
             (int) (y + boundingBox.y - handler.getGameCamera().getyOffset()),
                boundingBox.width,
               boundingBox.height);

       g.setColor(Color.black);
       */
       // this.drawBoundingBox(g, this, Color.BLUE);
        if(dialog != null) {
            if(dialog.getdVisible()){
                dialog.render(g);
                if(handler.getKeyman().enter){
                    dialog.setdVisible(false);
                }

            }


        }

    }

    private BufferedImage getAnimFrame(){

        if(xMove == 0 && yMove == 0){ // we're not moving
            return facingDirection; // set the facing direction as the current frame.
        }
        if(xMove < 0) {
            return npc_anim_left.getCurrentFrame();
        } else if(xMove > 0){
            return npc_anim_right.getCurrentFrame();
        } else if(yMove < 0){
            return npc_anim_up.getCurrentFrame();
        } else {
            return npc_anim_down.getCurrentFrame();
        }
    }

    public void OnTalk(Rectangle trigger){

        yMove=0;
        xMove=0;
       // System.out.println("called");

        // based on the trigger, face that direction.
        if(trigger == handler.getWorld().getEntityManager().getPlayer().useTriggerUp) {
            facingDirection = Assets.npc_down[0];
        } else if(trigger == handler.getWorld().getEntityManager().getPlayer().useTriggerDown) {
            facingDirection = Assets.npc_up[0];
        } else if(trigger == handler.getWorld().getEntityManager().getPlayer().useTriggerRight) {
            facingDirection = Assets.npc_left[0];
        } else if(trigger == handler.getWorld().getEntityManager().getPlayer().useTriggerLeft) {
            facingDirection = Assets.npc_right[0];
        }

        int width=600; int height=150;
        dialog = new Dialog (handler,((handler.getWidth()/2)/4),
                ((handler.getHeight()/6)*4),
                width,
                height,
                this.strArray, this.npcName);

        dialog.setdVisible(true);
        handler.setDialog(dialog);

    }

    private void moveRandomly(){

        xMove = random.nextInt(3) - 1; // -1, 0, 1
        yMove = random.nextInt(3) - 1;

        // 50% chance to stand still.
        if (random.nextInt(2) == 0) {
            yMove = 0;
            xMove = 0;
        }
    }

}
