package project.game.tilegame.states;
import project.game.tilegame.Handler;
import project.game.tilegame.entities.Entity;
import project.game.tilegame.entities.EntityManager;
import project.game.tilegame.entities.creatures.NPC;
import project.game.tilegame.entities.creatures.Player;
import project.game.tilegame.entities.staticEntities.Tree;
import project.game.tilegame.gfx.Assets;
import project.game.tilegame.Game;
import project.game.tilegame.gfx.Dialog;
import project.game.tilegame.worlds.World;

import java.awt.*;

/**
 * Created by Dan on 3/14/2016.
 *
 * @author Dan
 * @description the main game state, which is how we define the objects and actions when this state is invoked.
 */

public class mainGame implements State {

   // private Player player;
    private World world;

    EntityManager entityManager;
    Handler handler;

    public mainGame(Handler handler) {

        //player = new Player(handler,0,0);
        this.handler = handler;
        world = new World(handler, "res/worlds/world.txt", "Hyrule");
        handler.setWorld(world);

        entityManager = handler.getWorld().getEntityManager();
        String[] strArray = new String[]{"Hail!","Mind your path peasant.","I am searching for a dragon.",
                "Do not make the mistake of getting in my way."};
        NPC npc = new NPC(handler, 600, 400,64,64, "Lady Elizabeth", strArray);

        String[] strArray2 = new String[]{"Hi there good sir. My twin sister can have a bit of a temper." ,"I'm sorry.","The dragon we are searching for destroyed our village..",
                "We will not waver until it has met its end."};
        NPC npc2 = new NPC(handler, 700,300,64,64, "Bobby", strArray2);
        entityManager.addEntity(npc);
        entityManager.addEntity(npc2);
        /*
        int ymoveTree=0;
        for(int i=0;i<10;i++){

            if(i==8 || i == 9){ ymoveTree+=60; continue;}
            entityManager.addEntity(new Tree(handler, ymoveTree,-20));
            entityManager.addEntity(new Tree(handler, ymoveTree+30,-60));
            ymoveTree+=60;
        }*/
        entityManager.addEntity(new Tree(handler, 650, 400-60));


    }

    @Override
    public void tick(double delta) {

        world.tick();
   /*     if(handler.getDialog() != null)
            handler.getDialog().tick();

        if(handler.getMouseManager().getLeftPressed() ){

            entityManager.addEntity(new Tree(handler, handler.getMouseManager().getMouseX()-handler.getGameCamera().getxOffset()-64,
                    handler.getMouseManager().getMouseY()- handler.getGameCamera().getxOffset()-100));
        }
*/

    }

    @Override
    public void render(Graphics g) {

        //player.render(g);

       //g.drawImage(Assets.grass,0,0,null);
        //player.render(g);
        /*for(int x=0;x<600;x+=64){
            for(int i=0;i<800;i+=64) {
                g.drawImage(Assets.grass, i, x, null);
            }
        }
        */


        world.render(g);


        //System.out.println("maingame render being called.!");

        if(handler.getDialog() != null) {
            if (handler.getDialog().dVisible) {
                handler.getDialog().drawDialogBox(g);
            }
        }

    }

    @Override
    public void OnEnter() {

    }

    @Override
    public void OnExit() {

    }
}
