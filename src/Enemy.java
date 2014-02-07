import java.awt.*;
import java.util.ArrayList;

class Enemy
{
	private int x, y, xChange, yChange, delay, x1, y1, health, indexOfThis, leadX, leadY;
	private double shipAngle, xVelocity, yVelocity, length;
	private static int portX, portY, starboardX, starboardY, index;
	private ArrayList<Beam> shots = new ArrayList<Beam>();
	private ArrayList<Beam> beams = new ArrayList<Beam>();
	private char type;
	private boolean collision, shotsFired, beamFired;
	private Polygon bounds;
	
	public Enemy(int x, int y, char type)
	{
		health = 500;
		xChange = 0;
		yChange = 0;
		this.x = x;
		this.y = y;
		this.type = type;
	}
	public void drawEnemyFighter(Graphics g,int shipX,int shipY)
	{
		//draws Health bar
		g.setColor(Color.red);
		g.fillRect(x,y-25,(health/50),5);
		//enemy ship is always focused on players ship
		double deltaX = (shipX-x);
		double deltaY = (shipY-y);
		double slope = (deltaY/deltaX);
		double theta = (Math.atan(slope)*180)/Math.PI;
		shipAngle = theta;
		if(deltaX==0)
		{
			if(deltaY/Math.abs(deltaY)==1)
			{
				theta = 270;
			}
			else
			{
				theta = 90;
			}
		}
		Polygon starFighter = new Polygon();
		
		if(deltaX/Math.abs(deltaX)==1)
		{
			g.setColor(Color.red);
			g.drawLine(x+(int)(11.05*Math.cos((theta-84.91)/57.2957795)), y+(int)(11.05*Math.sin((theta-84.91)/57.2957795)), x+(int)(13.04*Math.cos((theta-122.47)/57.2957795)), y+(int)(13.04*Math.sin((theta-122.47)/57.2957795)));
			g.drawLine(x+(int)(11.05*Math.cos((theta+180-84.91)/57.2957795)), y+(int)(11.05*Math.sin((theta+84.91)/57.2957795)), x+(int)(13.04*Math.cos((theta+122.47)/57.2957795)), y+(int)(13.04*Math.sin((theta+122.47)/57.2957795)));
			g.drawLine(x+(int)(7.21*Math.cos((theta-56.31)/57.2957795)), y+(int)(7.21*Math.sin((theta-56.31)/57.2957795)), x+(int)(6.32*Math.cos((theta-108.43)/57.2957795)), y+(int)(6.32*Math.sin((theta-108.43)/57.2957795)));
			g.drawLine(x+(int)(7.21*Math.cos((theta+56.31)/57.2957795)), y+(int)(7.21*Math.sin((theta+56.31)/57.2957795)), x+(int)(6.32*Math.cos((theta+108.43)/57.2957795)), y+(int)(6.32*Math.sin((theta+108.43)/57.2957795)));
			g.setColor(Color.lightGray);
			starFighter.addPoint(x+(int)(12*Math.cos((theta)/57.2957795)), y+(int)(12*Math.sin((theta)/57.2957795)));
		
			starFighter.addPoint(x+(int)(3*Math.cos((theta-90)/57.2957795)), y+(int)(3*Math.sin((theta-90)/57.2957795)));
			starFighter.addPoint(x+(int)(15.3*Math.cos((theta-121.6)/57.2957795)), y+(int)(15.3*Math.sin((theta-121.6)/57.2957795)));
			starFighter.addPoint(x+(int)(4.24*Math.cos((theta-135)/57.2957795)), y+(int)(4.24*Math.sin((theta-135)/57.2957795)));

			starFighter.addPoint(x+(int)(4.24*Math.cos((theta-225)/57.2957795)), y+(int)(4.24*Math.sin((theta-225)/57.2957795)));
			starFighter.addPoint(x+(int)(15.3*Math.cos((theta-238.4)/57.2957795)), y+(int)(15.3*Math.sin((theta-238.4)/57.2957795)));
			starFighter.addPoint(x+(int)(3*Math.cos((theta-270)/57.2957795)), y+(int)(3*Math.sin((theta-270)/57.2957795)));
		}
		else
		{
			g.setColor(Color.red);
			g.drawLine(x+(int)(11.05*Math.cos((theta+180-84.91)/57.2957795)), y+(int)(11.05*Math.sin((theta+180-84.91)/57.2957795)), x+(int)(13.04*Math.cos((theta+180-122.47)/57.2957795)), y+(int)(13.04*Math.sin((theta+180-122.47)/57.2957795)));
			g.drawLine(x+(int)(11.05*Math.cos((theta+180+84.91)/57.2957795)), y+(int)(11.05*Math.sin((theta+180+84.91)/57.2957795)), x+(int)(13.04*Math.cos((theta+180+122.47)/57.2957795)), y+(int)(13.04*Math.sin((theta+180+122.47)/57.2957795)));
			g.drawLine(x+(int)(7.21*Math.cos((theta+180-56.31)/57.2957795)), y+(int)(7.21*Math.sin((theta+180-56.31)/57.2957795)), x+(int)(6.32*Math.cos((theta+180-108.43)/57.2957795)), y+(int)(6.32*Math.sin((theta+180-108.43)/57.2957795)));
			g.drawLine(x+(int)(7.21*Math.cos((theta+180+56.31)/57.2957795)), y+(int)(7.21*Math.sin((theta+180+56.31)/57.2957795)), x+(int)(6.32*Math.cos((theta+180+108.43)/57.2957795)), y+(int)(6.32*Math.sin((theta+180+108.43)/57.2957795)));
			g.setColor(Color.lightGray);
			starFighter.addPoint(x+(int)(12*Math.cos((theta+180)/57.2957795)), y+(int)(12*Math.sin((theta+180)/57.2957795)));
			
			starFighter.addPoint(x+(int)(3*Math.cos((theta+180-90)/57.2957795)), y+(int)(3*Math.sin((theta+180-90)/57.2957795)));
			starFighter.addPoint(x+(int)(15.3*Math.cos((theta+180-121.6)/57.2957795)), y+(int)(15.3*Math.sin((theta+180-121.6)/57.2957795)));
			starFighter.addPoint(x+(int)(4.24*Math.cos((theta+180-135)/57.2957795)), y+(int)(4.24*Math.sin((theta+180-135)/57.2957795)));

			starFighter.addPoint(x+(int)(4.24*Math.cos((theta+180-225)/57.2957795)), y+(int)(4.24*Math.sin((theta+180-225)/57.2957795)));
			starFighter.addPoint(x+(int)(15.3*Math.cos((theta+180-238.4)/57.2957795)), y+(int)(15.3*Math.sin((theta+180-238.4)/57.2957795)));
			starFighter.addPoint(x+(int)(3*Math.cos((theta+180-270)/57.2957795)), y+(int)(3*Math.sin((theta+180-270)/57.2957795)));
		}
		bounds = starFighter;
		g.fillPolygon(starFighter);
		g.setColor(Color.black);		
		//cockpit
		Polygon square = new Polygon();
		//top right
		square.addPoint(x+(int)(4*Math.cos((theta-45)/57.2957795)), y+(int)(4*Math.sin((theta-45)/57.2957795)));
		//bottom right
		square.addPoint(x+(int)(4*Math.cos(((theta-45)+90)/57.2957795)), y+(int)(4*Math.sin(((theta-45)+90)/57.2957795)));
		//bottom left
		square.addPoint(x+(int)(4*Math.cos(((theta-45)+180)/57.2957795)), y+(int)(4*Math.sin(((theta-45)+180)/57.2957795)));
		//top left
		square.addPoint(x+(int)(4*Math.cos(((theta-45)+270)/57.2957795)), y+(int)(4*Math.sin(((theta-45)+270)/57.2957795)));
		g.fillPolygon(square);
		x1 = x;
		y1 = y;
		shipAngle = theta;
		portX = x+(int)(100*Math.cos((theta-45)/57.2957795));
		portY = y+(int)(100*Math.cos((theta-45)/57.2957795));
		starboardX = x+(int)(100*Math.cos((theta+225)/57.2957795));
		starboardY = y+(int)(100*Math.cos((theta+225)/57.2957795));
		index = indexOfThis;
		advanceEnemyShip(shipX,shipY);
	}

	//only two kinds of enemies ... for now...
	public void drawEnemyTurret(Graphics g,int shipX,int shipY)
	{
		//draws Health bar
		g.setColor(Color.red);
		g.fillRect(x,y-25,(health/50),5);
		double deltaX = (shipX-x);
		double deltaY = (shipY-y);
		double slope = (deltaY/deltaX);
		double theta = (Math.atan(slope)*180)/Math.PI;		
		g.setColor(Color.lightGray);
		Polygon turret = new Polygon();
		turret.addPoint(x+3, y-5);
		turret.addPoint(x+5, y-3);
		turret.addPoint(x+5, y+2);
		turret.addPoint(x+3, y+4);
		
		turret.addPoint(x-2, y+4);
		turret.addPoint(x-4, y+2);
		turret.addPoint(x-4, y-3);
		turret.addPoint(x-2, y-5);
		bounds = turret;
		g.fillPolygon(turret);
		g.setColor(Color.darkGray);
		if(deltaX/Math.abs(deltaX)==1)
		{
			g.drawLine(x,y, x+(int)(10*Math.cos(theta/57.2957795)), y+(int)(10*Math.sin(theta/57.2957795)));
		}
		else
		{
			g.drawLine(x,y, x+(int)(10*Math.cos((theta+180)/57.2957795)), y+(int)(10*Math.sin((theta+180)/57.2957795)));
		}
	}
	public void advanceEnemyShip(int shipX, int shipY)
	{
		length =  squareRoot(square(shipX-x)+square(shipY-y));
		yVelocity = (shipY-y)/length;
		xVelocity = (shipX-x)/length;
		yChange = (int)(yVelocity*4);
		xChange = (int)(xVelocity*4);
		x=x+xChange;
    	y=y+yChange;
	}
	public void drawShots(Graphics g,int shipX,int shipY)
	{
		if(length<600)
		{
		delay++;
		if(delay==20)
		{
			Beam shot = new Beam(getX(), getY(),shipX,shipY,1800,800);
			shots.add(shot);
		}
		if(delay == 40)
		{
			//so that the shots come out of different sides of the fighter
			Beam shot = new Beam(getAltX(),getAltY(),shipX,shipY,1800,800);
			shots.add(shot);
			delay = 0;
		}
		//draws and manages sho
		for(int z=0; z<shots.size();z++)
	    {
			if(shots.size()>0)
			{
				shots.get(z).drawShot(g);
			}
			//so that the array does not stack shots for forever... yet it gives me errors
	 	   	if(shots.get(z).getShotX()>leGame.xMax)
			{
	 	   		shots.remove(z);
			}
	 	   	else if(shots.get(z).getShotX()<0)
	 	   	{
	 	   		shots.remove(z);
	 	   	}
	 	   	else if(shots.get(z).getShotY()>leGame.yMax)
	 	   	{
	 	   		shots.remove(z);
	 	   	}
	 	   	else if(shots.get(z).getShotY()<0)
		 	{
	 	   		shots.remove(z);
		 	}
	    }
		}
	}
	public void drawTurretShots(Graphics g,int shipX,int shipY)
	{
  		length =  squareRoot(square(shipX-x)+square(shipY-y));
		if(length<600)
		{
		delay++;
		if(delay==100)
		{
			Beam beam = new Beam(x, y,shipX,shipY,leGame.xMax,leGame.yMax);
			beams.add(beam);
			delay = 0;
        }
		//draws and manages beams on the screen
		for(int z=0; z<beams.size();z++)
	    {
			if(beams.size()>0)
			{
				beams.get(z).drawBeam(g);
			}
			//so that the array does not stack shots for forever... yet it gives me errors
	 	   	if(beams.get(z).getTailX()>leGame.xMax)
			{
	 	   		beams.remove(z);
			}
	 	   	else
	 	   	{
	 	   		if(beams.get(z).getTailX()<0)
	 	   		{
	 	   			beams.remove(z);
	 	   		}
	 	   		else
	 	   		{
	 	   			if(beams.get(z).getTailY()>leGame.yMax)
	 	   			{
	 	   				beams.remove(z);
	 	   			}
	 	   			else
	 	   			{
		 	   			if(beams.get(z).getTailY()<0)
		 	   			{
		 	   				beams.remove(z);
		 	   			}	
	 	   			}
	 	   		}
	 	   	}
	    }
		}
	}
	public void removeShots(int index)
	{
		shots.remove(index);
	}
	public void removeBeams(int index, int subIndex)
	{
		beams.get(index).beamX.remove(subIndex);
		beams.get(index).beamY.remove(subIndex);
	}
	public Polygon getBounds()
	{
		return bounds;
	}
	public char getType()
	{
		return type;
	}
	private int getX()
	{
		//right side of ship
		x1 = x1+(int)(15.3*Math.cos((shipAngle-121.6)/57.2957795));
		return x1;
	}
	private int getY()
	{
		y1 = y1+(int)(15.3*Math.sin((shipAngle-121.6)/57.2957795));
		return y1;
	}
	private int getAltX()
	{
		//let side of ship
		x1 = x1+(int)(15.3*Math.cos((shipAngle+180-121.6)/57.2957795));
		return x1;
	}
	private int getAltY()
	{
		y1 = y1+(int)(15.3*Math.sin((shipAngle+180-121.6)/57.2957795));
		return y1;
	}
	public int getShipX()
	{
		return x;
	}
	public int getShipY()
	{
		return y;
	}
	public boolean shotsFired()
	{
		return shotsFired;
	}
	public boolean beamFired()
	{
		return beamFired;
	}
	public ArrayList getBeams()
	{
		return beams;
	}
	public ArrayList getShots()
	{
		return shots;
	}	
	public int getHealth()
	{
		return health;
	}
	public static int getPortWingX()
	{
		return portX;
	}
	public static int getPortWingY()
	{
		return portY;
	}
	public static int getStarboardWingX()
	{
		return starboardX;
	}
	public static int getStarboardWingY()
	{
		return starboardY;
	}
	public void setIndex(int n)
	{
		indexOfThis = n;
	}
	public static int getIndex()
	{
		return index;
	}
	public void decreaseHealth(int damage)
	{
		health-=damage;
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




