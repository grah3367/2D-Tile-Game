package project.game.tilegame;

/**
 * Created by Dan on 3/12/2016.
 */
public class Launcher {
    public static void main(String[] args) {

        System.out.println("launching wizard adventures!");

       // System.setProperty("sun.java2d.transaccel", "True");
        // System.setProperty("sun.java2d.trace", "timestamp,log,count");
      //  System.setProperty("sun.java2d.opengl", "True");
       // System.setProperty("sun.java2d.d3d", "True");
      //  System.setProperty("sun.java2d.ddforcevram", "True");

        Game WizardAdventures = new Game("Wizard Adventures!!", 800, 600);
        WizardAdventures.start();

    }
}
