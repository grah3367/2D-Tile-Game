package project.game.tilegame.states;

import project.game.tilegame.Handler;

import project.game.tilegame.gfx.Assets;
import project.game.tilegame.Game;

import java.awt.*;

/**
 * Created by Dan on 3/14/2016.
 */
public class mainMenu implements State {

    protected Handler handler;
    public GameState gameState;


    public mainMenu(Handler handler){
        this.handler = handler;

    }

    @Override
    public void tick(double delta) {

        if(handler.getKeyman().enter){

            GameState.setGameState(new mainGame(handler));
        }


    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void OnEnter() {

    }

    @Override
    public void OnExit() {

    }
}
