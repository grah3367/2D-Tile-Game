package project.game.tilegame.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dan on 3/13/2016.
 */
public class Assets {

    private static final int width=64, height=64;

    //public static BufferedImage hero;

    public static BufferedImage[] hero_down, hero_up, hero_left, hero_right;
    public static BufferedImage[] npc_down, npc_up, npc_left,npc_right;


    public static BufferedImage dirt, grass, water, westWall, southWall, tree;

    // load once everything in the game.
    public static void init(){
        SpriteSheet heroSheet = new SpriteSheet(imageLoader.loadImage("/textures/hero-mage.png"));
        SpriteSheet npcSheet = new SpriteSheet(imageLoader.loadImage("/textures/npc.png"));
        SpriteSheet envSheet = new SpriteSheet(imageLoader.loadImage("/textures/basic-tiles.png"));
        SpriteSheet treeSheet = new SpriteSheet(imageLoader.loadImage("/textures/tree.png"));

       // hero = heroSheet.crop(0,height*6, width, height);
        hero_down = new BufferedImage[9];
        hero_up = new BufferedImage[9];
        hero_left = new BufferedImage[9];
        hero_right = new BufferedImage[9];

        npc_down = new BufferedImage[9];
        npc_up = new BufferedImage[9];
        npc_left = new BufferedImage[9];
        npc_right = new BufferedImage[9];


        // Generate hero walking down, up, left, right animation array
       for(int i=0;i<hero_down.length;i++){
            hero_down[i] = heroSheet.crop(i*width, height*10, width, height);
            hero_up[i] = heroSheet.crop(i*width, height*8, width, height);
            hero_left[i] = heroSheet.crop(i*width, height*9, width, height);
            hero_right[i] = heroSheet.crop(i*width, height*11, width, height);
        }
        // Generate NPC walking down, up, left, right animation array
        for(int i=0;i<npc_down.length;i++){
            npc_down[i] = npcSheet.crop(i*width, height*10, width, height);
            npc_up[i] = npcSheet.crop(i*width, height*8, width, height);
            npc_left[i] = npcSheet.crop(i*width, height*9, width, height);
            npc_right[i] = npcSheet.crop(i*width, height*11, width, height);
        }

        tree = imageLoader.loadImage("/textures/tree.png");
        dirt = envSheet.crop(width*2,0,width, height);
        grass = envSheet.crop(0,0,width,height);
        water = envSheet.crop(width,0,width,height);
        westWall = envSheet.crop(0,height,width,height);
        southWall = envSheet.crop(width,height,width,height);

        // fonts

        try {
            Font ale = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Alegreya-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(ale);
        } catch (IOException |FontFormatException e) {
            System.out.println("font did not load: " + e);
        }
    }


}
