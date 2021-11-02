package project.game.tilegame.gfx;

import project.game.tilegame.Handler;

import java.awt.*;

/**
 * @author Dan
 * @description creates a dialog instance and handles the rendering of the dialog box.
 */
public class Dialog {

    int width=200;
    int height=100;
    private String[] message;
    private String name;

    int x, y;
    int padding=30;
    public boolean dVisible = false;
    Handler handler;
    Font ale;
    private int timer = 0;


    public int getPadding() {
        return padding;
    }

    public Dialog(Handler handler, int x, int y, int width, int height, String[] message, String name){

        this.handler = handler;
        this.width = width;
        this.height = height;
        this.message = message;
        this.name = name;
        this.x = x;
        this.y = y;




    }

    public void tick(){
        timer++;
    }


    public void render(Graphics g){

    }

    public void drawDialogBox(Graphics g){


        drawShadow(g);


        g.setColor( new Color(115,115,115,155));
        g.drawRect(x-1,y-1, width + 1, height + 1);
        g.setColor(new Color(237, 237, 237,200));
        g.fillRect(x, y, width, height);

        g.setColor(new Color(57,57,57,255) );
        g.setFont(new Font("TimesRoman", Font.BOLD, 16));
        g.drawString(this.name,x+10,y+20);

        g.setColor(new Color(57,57,57,255) );
        g.setFont(new Font("Alegreya", Font.PLAIN, 20));
        g.setColor(new Color(57,57,57,255) );
        drawStrings(message,x+padding,y+padding,g);


        int stringWidth = g.getFontMetrics().stringWidth("Press enter to continue");
        int stringHeight = g.getFontMetrics().getHeight();
        //System.out.println(stringHeight);
       //System.out.println(stringWidth);

        g.setColor(new Color(57,57,57,255) );
        g.setFont(new Font("TimesRoman", Font.ITALIC, 12));
        g.drawString("[Press enter to continue...]",(x+width)-stringWidth+15,(y+height)-stringHeight+15);

        dVisible = true;


    }

    public void drawShadow(Graphics g){

        g.setColor(new Color(57,57,57,50) );
        g.fillRect(x-4, y-4, width+7, height+7);
        g.setColor(new Color(237, 237, 237,50));
        g.fillRect(x-2, y-2, width+4, height+4);
    }

   public void drawStrings(String ln[], int x, int y, Graphics g)
    {
        int h = 25;


        // timer % 60 == 0; // a second has passed

        for (int i=0; i<ln.length; i++) // This is for each sentence.
        {
            g.drawString(ln[i], x, y+(h*i) + h);
           // g.drawString(str, x, y+(h*i) + h);

            /*for(int c = 0;timer % 60 == 0;timer++){
                System.out.println(ln[i]);
                g.drawString(ln[i], x, y+(h*i) + h);
                if(c == ln[i].length()){
                    c++;
                    break;
                }
            }
            */


        }
    }



    public boolean setdVisible(boolean state){
        return this.dVisible = state;
    }
    public  boolean getdVisible(){
        return this.dVisible;
    }

}
