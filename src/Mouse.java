import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener
{
	private static int mouseX,mouseY;
	private static boolean mousePressed;
	public Mouse()
	{
		mousePressed = false;
		mouseX = 0;
		mouseY = 0;
	}
	   public void mouseDragged(MouseEvent q)
	   {
		   mouseX = q.getX();
		   mouseY = q.getY();
	   }
	   public void mouseMoved(MouseEvent q)
	   {
		   mouseX = q.getX();
		   mouseY = q.getY();
	   }
	   public void mouseClicked(MouseEvent q)
	   {
		   
	   }
	   public void mousePressed(MouseEvent q)
	   {
		   //boolean mousePressed is past to Game where it then instantiates another beam
		   mousePressed = true;
		   mouseX = q.getX();
		   mouseY = q.getY();
//		   Beam shot = new Beam();
//		   shot.mouseX = q.getX();
//		   shot.mouseY = q.getY();
//		   shot.shotX = x;
//		   shot.shotY = y;
//		   shot.outerX = xMax;
//		   shot.outerY = yMax;
//		   shot.length = shot.squareRoot(shot.square(shot.mouseX-shot.shotX)+shot.square(shot.mouseY-shot.shotY));
//		   shot.yVelocity = (shot.mouseY-shot.shotY)/shot.length;
//		   shot.xVelocity = (shot.mouseX-shot.shotX)/shot.length;
//		   shots.add(shot);
//		   int d = 1;
//		   
//		   count.add(d);
	   }
	   public void mouseReleased(MouseEvent q)
	   {
		   mousePressed = false;
	   }
	   public void mouseEntered(MouseEvent q)
	   {
		   
	   }
	   public void mouseExited(MouseEvent q)
	   {
		   
	   }
	   public static boolean shotsFired()
	   {
		   return mousePressed;
	   }
		public static int getX()
		{
			return mouseX;
		}
		public static int getY()
		{
			return mouseY;
		}
}
