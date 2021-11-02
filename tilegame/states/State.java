package project.game.tilegame.states;


import java.awt.Graphics;
/**
 * Created by Dan on 3/14/2016.
 */
public interface State {
    void tick(double delta);
    void render(Graphics g);
    void OnEnter();
    void OnExit();

}

