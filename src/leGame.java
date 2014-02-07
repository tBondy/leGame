import java.applet.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class leGame extends Applet implements Runnable
{
	private int t = 10;
	private double i = 0;
	public static int xMax,yMax;
	Thread game = null;
	boolean threadSuspended;
	
	private Image offScreenImage;
    private Graphics offScreenGraphics;
    
    Mouse mouse = new Mouse();
    KeyInteraction key = new KeyInteraction();
    Game eh = new Game();

   public void init() 
   {
	   setBackground(Color.black);
	   xMax = 1800;
	   yMax = 1000;
	   setSize(xMax,yMax);
   }

   // Executed after the applet is created; and also whenever
   // the browser returns to the page containing the applet.
   public void start() 
   {
	   if (game == null) 
      {
		   game = new Thread(this);
		   threadSuspended = false;
		   game.start();
		   setFocusable(true);
		   addKeyListener(key);
		   addMouseListener(mouse);
		   addMouseMotionListener(mouse);
		   
      }
      else 
      {
    	  if (threadSuspended) 
         {
    		  threadSuspended = false;
    		  synchronized(this) 
            {
    			  notify();
            }
         }
      }
   }

   // Executed whenever the browser leaves the page containing the applet.
   public void stop() 
   {
      threadSuspended = true;
   }

   public void run() 
   {
      try 
      {
         while (true) 
         {
        	i++;
            if(i == 1000)
            {
            	i = 0;
            }

            showStatus("i is " + i);

            // Now the thread checks to see if it should suspend itself
            if (threadSuspended) 
            {
               synchronized(this) 
               {
                  while (threadSuspended) 
                  {
                     wait();
                  }
               }
            }
            repaint();
            game.sleep(t);  
         }
      }
      catch (InterruptedException e) { }
   }
   public void paint(Graphics g) 
   {
	   if (offScreenImage == null) 
	   {
           offScreenImage = (BufferedImage) createImage(2000,2000);
       }
       offScreenGraphics = offScreenImage.getGraphics();
//       offScreenGraphics.setColor(Color.black);
       offScreenGraphics.clearRect(0, 0, 2000, 2000);

       eh.drawGame(offScreenGraphics);
       
       g.drawImage(offScreenImage, 0, 0, this);
   }

   public void update(Graphics g) 
   {
       paint(g);
   }
}


