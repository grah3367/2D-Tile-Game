package project.game.tilegame.gfx;

import java.awt.image.BufferedImage;

/**
 * Created by Dan on 4/8/2016.
 *
 * todo only play animation when button is pressed.
 *
 */
public class Animation {

    private int speed, index;
    private BufferedImage[] frames;
    long lastTime, timer;

    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer=1;
            if(index >= frames.length)
                index=1;
        }
    }


    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

}
