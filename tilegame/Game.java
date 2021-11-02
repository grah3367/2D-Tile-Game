package project.game.tilegame;

import project.game.tilegame.gfx.*;
import project.game.tilegame.gfx.Dialog;
import project.game.tilegame.input.KeyManager;
import project.game.tilegame.input.MouseManager;
import project.game.tilegame.states.GameState;
import project.game.tilegame.states.State;
import project.game.tilegame.states.mainGame;
import project.game.tilegame.states.mainMenu;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


/**
 * Created by Dan on 3/12/2016.
 *
 * @author Dan
 * @description This Game class is where everything is wrapped up,includes a game loop with a Frame rate independent of update.
 */
public class Game implements Runnable {

    private BufferStrategy bs;
    private Graphics g;
    String fpsString="";

   // private SpriteSheet hero;
   // private BufferedImage heroImg;


    private Window window;
    private int width, height;
    public String title;
    private Thread thread;
    private Boolean running = false;

    //game states
   // private GameState gameState;
    public GameState gameState;
    public GameState menuState;

    //input
    private KeyManager keyman;
    private MouseManager mouseMan;

    // Camera
    private GameCamera gameCamera;

    // handler
    private Handler handler;

    // test image
    //private BufferedImage testimage;

    // Dialog
    Dialog dialog;

    public MouseManager getMouseMan() {
        return mouseMan;
    }

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyman = new KeyManager();
        mouseMan = new MouseManager();

    }

    private void init(){
        handler = new Handler(this);
        window = new Window(title, width, height);

        window.getFrame().addKeyListener(keyman);


        window.getFrame().addMouseListener(mouseMan);
        window.getFrame().addMouseMotionListener(mouseMan);
        window.getCanvas().addMouseListener(mouseMan);
        window.getCanvas().addMouseMotionListener(mouseMan);

        Assets.init(); // load in resources

        gameCamera = new GameCamera(handler, 0, 0);


       // heroImg = imageLoader.loadImage("/textures/hero-mage.png");
       // hero = new SpriteSheet(heroImg);

        gameState = new GameState(handler);

        State mainGame = new mainGame(handler);

        State menuState = new mainMenu(handler);

        // set the gameState
        gameState.setGameState(menuState);



    }

    // IMPLEMENT A DELTA PASS SO THAT MOVEMENT CAN BE CALCULATED.
    private void tick(double delta){

        keyman.tick(delta);

        if(gameState.getGameState() != null){
            gameState.tick(delta);
        } else {
            //System.out.println("gamestate is null! (tick)");
        }
    }
    private void render(){

       bs = window.getCanvas().getBufferStrategy();

        // is the bufferStrategy set?
        if(bs == null){
            window.getCanvas().createBufferStrategy(3);
            return;
        }

        //g = bs.getDrawGraphics(); // the graphics object
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        // clear screen
        g.clearRect(0,0,width,height);
        // Do drawing
       // g.drawImage(hero.crop(0,640,64,64), 5, 5, null);
       // g.drawImage(Assets.water,x,0, null);
       // g.drawImage(Assets.dirt,64,0, null);
        //g.drawImage(Assets.grass,64*2,0, null);

       // g.drawImage(Assets.westWall,0,64, null);
       // g.drawImage(Assets.southWall,0,64, null);

        if(gameState.getGameState() != null){
            gameState.getGameState().render(g);
            /*
             * Debug info drawn to screen
             */
            g.setFont(new Font("Helvetica", Font.PLAIN, 12));
            g.setColor(new Color(133,133,133,200));
            g.drawRect(4,4,101,51);
            g.setColor(new Color(194,194,194,200));
            g.fillRect(5, 5,100,50);
            g.setColor(new Color(51,51,51,200));
            g.drawString(fpsString + " FPS",7,20);
            g.drawString("Mouse X: " + mouseMan.getMouseX() + " px",7, 35);
            g.drawString("Mouse Y: " + mouseMan.getMouseY()+ " px",7,50);

            // second screen
            g.setColor(new Color(133,133,133,155));
            g.drawRect(4,60,101,51);
            g.setColor(new Color(194,194,194,155));
            g.fillRect(5, 61,100,50);
            g.setColor(new Color(51,51,51,155));
            g.drawString("map:"+ handler.getWorld().getWorldName(),7,75);


        } else {
            System.out.println("gamestate is null! (render)");
        }

        //done drawing
        bs.show();
        g.dispose();


    }
    public void run(){

        init();   //init graphics and get stuff ready

        double tickUpdateRate=60.0;

        double deltaTime = 0;
        long lastTime = System.nanoTime();

        double secondsPerTick = 1 / tickUpdateRate;
        int tickCount = 0;
        int frames = 0;
        double nsPerTick = 1000000000 / tickUpdateRate;

        while(running){

            long now = System.nanoTime(); // get the current time
            long  passedTime = now - lastTime;
            lastTime = now;

            if(passedTime<0) passedTime=0;
            if(passedTime>1000000000) passedTime=1000000000;

            deltaTime += passedTime / 1000000000.0;

            boolean ticked = false;

            while(deltaTime > secondsPerTick){
               // System.out.println(deltaTime);
                handler.setDeltaTime(deltaTime);
                tick(deltaTime);

                deltaTime -= secondsPerTick;
                ticked = true;

                tickCount++;

                // a second
                if (tickCount % tickUpdateRate == 0) {

                    //System.out.println(frames + " fps");
                    fpsString = Integer.toString(frames);
                    lastTime +=1000;
                   // System.out.println(secondsPerTick);
                   // System.out.println(deltaTime);
                    frames=0;
                }

            }

                render();
                frames++;

        }

        stop();

    }

    public KeyManager getKeyman(){
        return keyman;
    }

    public synchronized void start(){

        if(running)
            return;
        running = true;
        thread = new Thread(this); // init thread
        thread.start();


    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth(){return  width;}
    public int getHeight(){return height;}



    public synchronized  void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }

    }

}
