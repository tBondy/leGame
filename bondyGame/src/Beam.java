import java.awt.*;
import java.util.ArrayList;

class Beam
{
//  for drawing beams
	private int shotX, shotY, outerX, outerY, x1, y1, x2, y2, d, bx, by, tailX, tailY;
	double length, yVelocity, xVelocity;
	ArrayList<Integer> beamX = new ArrayList<Integer>();
	ArrayList<Integer> beamY = new ArrayList<Integer>();
	
	public Beam(int x,int y,int targetX,int targetY,int xEdge,int yEdge)
	{
		// for bouncing, cause that's always fun.
		outerX = xEdge;
		outerY = yEdge;
		shotX = x;
		shotY = y;
		bx = shotX;
		by = shotY;
		//shots are vectors represented by parametric functions they can assume a new vector because their original velocity vector is not reinitialized every time the game is updated
		length = (int)squareRoot(square(targetX-shotX)+square(targetY-shotY));
		yVelocity = (targetY-shotY)/length;
		xVelocity = (targetX-shotX)/length;
		d = 1;
	}
	public void drawShot(Graphics g)
	{
		g.setColor(Color.green);
		x1 = (int)((xVelocity*d)+shotX);
		y1 = (int)((yVelocity*d)+shotY);
		g.drawLine(x1,y1,x1,y1);
		d+=10;
		// for bouncing, cause that's always fun.
// 	   	if(x1>outerX)
//		{
// 		  shotX = outerX;
// 		  xVelocity = -xVelocity;
// 		  shotY = y1;
// 		  d = 0;
//		}
//		if(y1>outerY)
//		{
//			shotY = outerY;
//			yVelocity = -yVelocity;
//			shotX = x1;
//			d = 0;
//		}
//		if(x1<0)
//		{
//			shotX = 0;
//			xVelocity = -xVelocity;
//			shotY = y1;
//			d = 0;
//		}
//		if(y1<0)
//		{
//			shotY = 0;
//			yVelocity = -yVelocity;
//			shotX = x1;
//			d = 0;
//		}
	}
	public void drawBeam(Graphics g)
	{
		g.setColor(Color.cyan);
		for(int z=0; z<50;z++)
		{
			bx = (int)((xVelocity*d)+shotX);
			by = (int)((yVelocity*d)+shotY);
			d++;
			beamX.add(bx);
			beamY.add(by);
			g.drawLine(getTailX(), getTailY(), bx, by);
		}
	}
	public int getTailX()
	{
		tailX = beamX.get(beamX.size()-1);
		return tailX;
	}
	public int getTailY()
	{
		tailY = beamY.get(beamY.size()-1);
		return tailY;
	}
	public int getBeamX(int index)
	{
		return beamX.get(index);
	}
	public int getBeamY(int index)
	{
		return beamY.get(index);
	}
	public int getShotX()
	{
		return x1;
	}
	public int getShotY()
	{
		return y1;
	}
	public int square(int x)
	{
		x = (int)Math.pow(x, 2);
		return x;
	}
	public double squareRoot(int x)
	{
		x = (int)Math.pow(x, .5);
		return x;
	}
}