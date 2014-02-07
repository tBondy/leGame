import java.awt.event.*;
import java.util.*;

public class KeyInteraction implements KeyListener
{
//		private static double angle;
//		private static int xChange, yChange, keyCode, tX, tY, thrust;
//		private double theta, length, xVelocity, yVelocity, direction, acceleration;
//		private int targetX, targetY, accelTimer, delay;
		private static int keyCode;
		private static boolean beamFired, shotsFired, rotateRight, rotateLeft, moveForward, moveBackward, accelerate;
		private HashSet<Integer> keysPressed = new HashSet<Integer>();
		public KeyInteraction()
		{
			beamFired = false;
			shotsFired = false;
			rotateRight = false;
			rotateLeft = false;
			moveForward = false;
			moveBackward = false;
			accelerate = false;
//			theta = 270;
//			targetX = Unit.getShipX()+(int)(2000*Math.cos((theta)/57.2957795));
//			targetY = Unit.getShipY()+(int)(2000*Math.sin((theta)/57.2957795));
//			tX = targetX;
//			tY = targetY;
//			acceleration = 1;
//			accelTimer = 100;
//// 			thrust = accelTimer;
//  			delay = 0;
		}
		public synchronized void implementKeysPressed()
		{
//				if(keysPressed.contains(q.VK_A))
//				{
//					acceleration = 2;
//					accelTimer-=10;
//					if(accelTimer<=0)
//					{
//						acceleration = 1;
//					}
//					thrust = accelTimer;
//				}
				if(keysPressed.contains(KeyEvent.VK_A))
				{
					accelerate = true;
				}
				else
				{
					accelerate = false;
				}
				if(keysPressed.contains(KeyEvent.VK_SPACE))
				{
					beamFired = true;
				}
				else 
				{
					beamFired = false;
				}
				if(keysPressed.contains(KeyEvent.VK_F))
				{
					shotsFired = true;
				}
				else
				{
					shotsFired = false;
				}
				if(keysPressed.contains(KeyEvent.VK_RIGHT))
				{
					rotateRight = true;
//					theta+=(acceleration*2);
//					angle = theta;
//					targetX = Unit.getShipX()+(int)(2000*Math.cos((theta)/57.2957795));
//					targetY = Unit.getShipY()+(int)(2000*Math.sin((theta)/57.2957795));
//					tX = targetX;
//					tY = targetY;
				}
				else
				{
					rotateRight = false;
				}
				if(keysPressed.contains(KeyEvent.VK_LEFT))
				{
					rotateLeft = true;
//					theta-=(acceleration*2);
//					angle = theta;
//					targetX = Unit.getShipX()+(int)(2000*Math.cos((theta)/57.2957795));
//					targetY = Unit.getShipY()+(int)(2000*Math.sin((theta)/57.2957795));		
//					tX = targetX;
//					tY = targetY;
				}
				else
				{
					rotateLeft = false;
				}
				if(keysPressed.contains(KeyEvent.VK_UP))
				{
					moveForward = true;
				}
				else
				{
					moveForward = false;
				}
				if(keysPressed.contains(KeyEvent.VK_DOWN))
				{
					moveBackward = true;
				}
				else
				{
					moveBackward = false;
				}
//				if(keysPressed.contains(KeyEvent.VK_UP)||keysPressed.contains(KeyEvent.VK_DOWN))
//				{
//					if(keysPressed.contains(KeyEvent.VK_UP))
//					{
//						direction = (acceleration);
//					}
//					else if(keysPressed.contains(KeyEvent.VK_DOWN)) 
//					{
//						direction = -(acceleration);
//					}
//					targetX = Unit.getShipX()+(int)(2000*Math.cos((theta)/57.2957795));
//					targetY = Unit.getShipY()+(int)(2000*Math.sin((theta)/57.2957795));
//					tX = targetX;
//					tY = targetY;
//					length = (int)squareRoot(square(targetX-Unit.getShipX())+square(targetY-Unit.getShipY()));
//					yVelocity = (targetY-Unit.getShipY())/length;
//					xVelocity = (targetX-Unit.getShipX())/length;
//					yChange = (int)(direction*(yVelocity*8));
//					xChange = (int)(direction*(xVelocity*8));
//				}
//				else
//				{
//					xChange = 0;
//					yChange = 0;
//				}
		}
	  	public synchronized void keyTyped(KeyEvent q)
	  	{
	  		
	  	}
//	  	public void keyPressed(KeyEvent q)
//	  	{
//	  		keyCode = q.getKeyCode();
//	  		if(keyCode==q.VK_W)
//	  		{
//	  			yChange = -5;
//	  		}
//	  		if(keyCode==q.VK_S)
//	  		{
//	  			yChange = 5;
//	  		}
//	  		if(keyCode==q.VK_D)
//	  		{
//	  			xChange = 5;
//	  		}
//	  		if(keyCode==q.VK_A)
//	  		{
//	  			xChange = -5;
//	  		}
//	  		if(keyCode==q.VK_SPACE)
//	  		{
//	  			beamFired = true;
//	  		}
//	  	}
//	  	public void keyReleased(KeyEvent q)
//	  	{
//	  		keyCode = q.getKeyCode();
//	  		if(keyCode==q.VK_W)
//	  		{
//				   yChange = 0;
//	  		}
//	  		if(keyCode==q.VK_S)
//	  		{
//				   yChange = 0;
//	  		}
//	  		if(keyCode==q.VK_D)
//	  		{
//				   xChange = 0;
//	  		}
//	  		if(keyCode==q.VK_A)
//	  		{
//				   xChange = 0;
//	  		}
//	  		if(keyCode==q.VK_SPACE)
//	  		{
//	  			beamFired = false;
//	  		}
//	  	}

	  	public synchronized void keyPressed(KeyEvent q)
	  	{
	  		keyCode = q.getKeyCode();
	  		if(keyCode==q.VK_UP)
	  		{
//	  			deltaX = (targetX-Unit.getShipX());
//	  			deltaY = (targetY-Unit.getShipY());
//	  			slope = (deltaY/deltaX);
//  			theta = (Math.atan(slope)*180)/Math.PI;
	  			keysPressed.add(keyCode);
//	  			targetX = Mouse.getX();
//	  			targetY = Mouse.getY();
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_DOWN)
	  		{
	  			keysPressed.add(keyCode);
	  			implementKeysPressed();
//	  			targetX = Mouse.getX();
//	  			targetY = Mouse.getY();
	  		}
	  		if(keyCode==q.VK_RIGHT)
	  		{
	  			keysPressed.add(keyCode);
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_LEFT)
	  		{
	  			keysPressed.add(keyCode);
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_SPACE)
	  		{
	  			keysPressed.add(keyCode);
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_F)
	  		{
	  			keysPressed.add(keyCode);
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_A)
	  		{
	  			keysPressed.add(keyCode);
	  			implementKeysPressed();
	  		}
	  	}
	  	public synchronized void keyReleased(KeyEvent q)
	  	{
	  		keyCode = q.getKeyCode();
	  		if(keyCode==q.VK_UP)
	  		{
	  			keysPressed.remove(keyCode);
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_DOWN)
	  		{
	  			keysPressed.remove(keyCode);
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_RIGHT)
	  		{
	  			keysPressed.remove(keyCode);
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_LEFT)
	  		{
	  			keysPressed.remove(keyCode);
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_SPACE)
	  		{
	  			keysPressed.remove(keyCode);
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_F)
	  		{
	  			keysPressed.remove(keyCode);
	  			implementKeysPressed();
	  		}
	  		if(keyCode==q.VK_A)
	  		{
	  			keysPressed.remove(keyCode);
	  			implementKeysPressed();
	  		}
	  	}
	  	public static boolean shotsFired()
	  	{
	  		return shotsFired;
	  	}
	  	public static boolean beamFired()
	  	{
	  		return beamFired;
	  	}
//	  	public static double getTheta()
//	  	{
//	  		return angle;
//	  	}
//	  	public static int getTargetX()
//	  	{
//	  		return tX;
//	  	}
//	  	public static int getTargetY()
//	  	{
//	  		return tY;
//	  	}
//	  	public static int getXChange()
//	  	{
//	  		return xChange;
//	  	}
//	  	public static int getYChange()
//	  	{
//	  		return yChange;
//	  	}
	  	public static boolean accelerate()
	  	{
	  		return accelerate;
	  	}
	  	public static boolean moveForward()
	  	{
	  		return moveForward;
	  	}
	  	public static boolean moveBackward()
	  	{
	  		return moveBackward;
	  	}
	  	public static boolean rotateRight()
	  	{
	  		return rotateRight;
	  	}
	  	public static boolean rotateLeft()
	  	{
	  		return rotateLeft;
	  	}
}
