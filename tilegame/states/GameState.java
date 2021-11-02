package project.game.tilegame.states;
import project.game.tilegame.Game;
import project.game.tilegame.Handler;

import java.awt.Graphics;
/**
 * Created by Dan on 3/14/2016.
 */
public class GameState implements State {

    private static State currentState=null;

    State mainGame;
    State mainMenu;
   // State optionsMenu;
  //  State paused;

    //constructor

    protected Handler handler;

    public GameState(Handler handler){

        this.handler = handler;

       //mainGame = new mainGame();
      //  mainMenu = new mainMenu(this);
       // optionsMenu = new optionsMenu(this);
      //  paused = new paused(this);

       // currentState = mainGame;
    }

    public static void setGameState(State newState){
        currentState = newState;
    }


    public static State getGameState(){
        return currentState;
    }

    public void OnEnter(){
        currentState.OnEnter();
    }
    public void OnExit(){
        currentState.OnExit();
    }
    public void render(Graphics g){
      //  System.out.println("rendering from" + currentState);
       currentState.render(g);
    }
    public void tick(double delta){
        currentState.tick(delta);
    }
}
