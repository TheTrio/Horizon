package sample;


import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Main{
    static SplashScreen mySplash;                   // instantiated by JVM we use it to get graphics
    static Graphics2D splashGraphics;               // graphics context for overlay of the splash image
    static Rectangle2D.Double splashTextArea;       // area where we draw the text
    static Rectangle2D.Double splashProgressArea;   // area where we draw the progress bar
    static Font font;
    static String array[] = {
        "Checking For Space", "Relocating Memory", "Creating Graphics", "Checking DataBase", "Transfering Game to Ram", "Painting Objects", "Clearing Memory", "Starting Game"
    };
    public static void main(String[] args) {

        System.out.println("Hello");
        MakeLoginWindow md = new MakeLoginWindow();

        splashInit();
        appInit();

        try{
            Thread.sleep(2000);
        }catch (Exception e){

        }
        if (mySplash != null) {

            System.out.println("Closing splashscreen...");
            mySplash.close();

            md.makeGo();
        }


    }

    private static void splashInit()
    {
        mySplash = SplashScreen.getSplashScreen();
        if (mySplash != null)
        {   // if there are any problems displaying the splash this will be null
            Dimension ssDim = mySplash.getSize();
            int height = ssDim.height;
            int width = ssDim.width;
            // stake out some area for our status information
            splashTextArea = new Rectangle2D.Double(15., height*0.88, width * .45, 32.);
            splashProgressArea = new Rectangle2D.Double(width * .55, height*.92, width*.4, 12 );

            // create the Graphics environment for drawing status info
            splashGraphics = mySplash.createGraphics();
            font = new Font("Dialog", Font.PLAIN, 14);
            splashGraphics.setFont(font);

            // initialize the status info
            splashText("Starting");
            splashProgress(0);
        }
    }

    public static void splashText(String str)
    {
        if (mySplash != null && mySplash.isVisible())
        {   // important to check here so no other methods need to know if there
            // really is a Splash being displayed

            // erase the last status text
            splashGraphics.setPaint(Color.white);
            splashGraphics.fill(splashTextArea);

            // draw the text
            splashGraphics.setPaint(Color.BLACK);
            splashGraphics.drawString(str, (int)(splashTextArea.getX() + 10),(int)(splashTextArea.getY() + 15));

            // make sure it's displayed
            mySplash.update();
        }
    }

    public static void splashProgress(int pct)
    {
        if (mySplash != null && mySplash.isVisible())
        {

            // Note: 3 colors are used here to demonstrate steps
            // erase the old one
            splashGraphics.setPaint(Color.LIGHT_GRAY);
            splashGraphics.fill(splashProgressArea);

            // draw an outline
            splashGraphics.setPaint(Color.BLUE);
            splashGraphics.draw(splashProgressArea);

            // Calculate the width corresponding to the correct percentage
            int x = (int) splashProgressArea.getMinX();
            int y = (int) splashProgressArea.getMinY();
            int wid = (int) splashProgressArea.getWidth();
            int hgt = (int) splashProgressArea.getHeight();

            int doneWidth = Math.round(pct*wid/100.f);
            doneWidth = Math.max(0, Math.min(doneWidth, wid-1));  // limit 0-width

            // fill the done part one pixel smaller than the outline
            splashGraphics.setPaint(Color.GREEN);
            splashGraphics.fillRect(x, y+1, doneWidth, hgt-1);

            // make sure it's displayed
            mySplash.update();
        }
    }

    private static void appInit()
    {
        for(int i=0;i<8;i++)
        {
            int pctDone = i * 10;
            splashText(array[i]);
            if(i!=7){
                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex)
                {
                    // ignore it
                }
                splashProgress(pctDone);
            }else {
                try
                {
                    Thread.sleep(1500);
                }
                catch (InterruptedException ex)
                {
                    // ignore it
                }
                splashProgress(100);
            }
        }
    }
}
