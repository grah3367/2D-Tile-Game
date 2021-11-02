package project.game.tilegame;

import project.game.tilegame.gfx.imageLoader;


import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.image.BufferedImage;





/**
 * Created by Dan on 3/12/2016.
 * @author Dan
 * @description The window window management and creation
 */
public class Window extends JFrame {

    private JFrame frame;
    private Canvas gameCanvas; // using a canvas to draw objects
    private JPanel gamePanel;

    private String windowTitle;
    private int windowWidth, windowHeight;

    public Window(String windowTitle, int windowWidth, int windowHeight){

        this.windowTitle = windowTitle;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        Draw();

    }

    private void Draw(){

        frame = new JFrame(windowTitle);
        frame.setSize(windowWidth,windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit jframe when the x is pressed.
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // center window
        frame.setVisible(true); // show the window.

        gameCanvas = new Canvas();
        gameCanvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
        gameCanvas.setMaximumSize(new Dimension(windowWidth, windowHeight));
        gameCanvas.setMinimumSize(new Dimension(windowWidth, windowHeight));
        gameCanvas.setFocusable(false);

/*
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(windowWidth, windowHeight));
        gamePanel.setMaximumSize(new Dimension(windowWidth, windowHeight));
        gamePanel.setMinimumSize(new Dimension(windowWidth, windowHeight));
        gamePanel.setFocusable(false);
        frame.add(gamePanel);
        */
        BufferedImage cursorImg = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        cursorImg = imageLoader.loadImage("/cursors/default.png");
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);
        frame.add(gameCanvas);
        frame.pack(); // see all of the canvas

    }

    public Canvas getCanvas(){
        return gameCanvas;
    }
    public JFrame getFrame(){ return frame;}


}
