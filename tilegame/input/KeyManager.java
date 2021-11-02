package project.game.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Dan on 3/14/2016.
 */
public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right, use, enter;

    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(double delta){
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        use = keys[KeyEvent.VK_E];
        enter = keys[KeyEvent.VK_ENTER];
    }

    @Override
    public void keyTyped(KeyEvent e) {
       // keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

    }
}
