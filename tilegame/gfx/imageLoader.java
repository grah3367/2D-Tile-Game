package project.game.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by Dan on 3/12/2016.
 */
public class imageLoader {

    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(imageLoader.class.getResource(path));
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }


}
