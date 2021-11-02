package project.game.tilegame;

import project.game.tilegame.gfx.Dialog;
import project.game.tilegame.gfx.GameCamera;
import project.game.tilegame.input.KeyManager;
import project.game.tilegame.input.MouseManager;
import project.game.tilegame.states.GameState;
import project.game.tilegame.states.State;
import project.game.tilegame.worlds.World;

/**
 * Created by Dan on 4/6/2016.
 */
public class Handler {

    private Game game;
    private World world;
    private double deltaTime;
    private Dialog dialog;

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {

        this.dialog = dialog;
    }

    /*
        public State getGameState(){
            return GameState.getGameState();
        }
    */
    public Game getGame() {
        return game;
    }

    public int getWidth(){ return game.getWidth();}
    public int getHeight(){ return game.getHeight();}

    public KeyManager getKeyman(){
        return game.getKeyman();
    }

    public GameCamera getGameCamera(){ return game.getGameCamera();}

    public World getWorld() {
        return world;
    }

    public void setGame(Game game) {

        this.game = game;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Handler(Game game){
        this.game = game;

    }

    public MouseManager getMouseManager(){
        return game.getMouseMan();
    }

    public double getDeltaTime(){
        return deltaTime;
    }

    public void  setDeltaTime(double dt){
        deltaTime = dt;
    }

}
