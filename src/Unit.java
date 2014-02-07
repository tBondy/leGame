import java.awt.*;
import java.util.ArrayList;

class Unit
{
	private int x,y,xChange,yChange,xMax,yMax,shotDelay,beamDelay, accelDelay,charge,health, accelEnergy,targetX, targetY;
	private double theta, length, xVelocity, yVelocity, direction, acceleration;
	private static int shipX,shipY;
	private static double shipAngle;
	private boolean collision, shotsFired, beamFired;
	//ships manage their own array of shots instead of the game, not good for shot management on screen
	private ArrayList<Beam> shots = new ArrayList<Beam>();
	private ArrayList<Beam> beams = new ArrayList<Beam>();
	private Polygon bounds;
	
	public Unit(int shipX,int shipY)
	{
		beamDelay = 0;
		shotDelay = 0;
		accelDelay = 0;
		health = 1000;
		charge = 200;
		xMax = 1800;
		yMax = 800;
		x = shipX;
		y = shipY;
		xChange = 0;
		yChange = 0;
		theta = 270;
		accelEnergy = 200;
	}
	public void drawFighter(Graphics g)
	{
		//draws timer for burst acceleration
//		g.setColor(Color.blue);
//		g.fillRect(0, 0, KeyInteraction.getThrust(), 10);
		//draws Health bar
		g.setColor(Color.red);
		g.fillRect(1000,0,(health/5),10);
		//draws energy bar
		if((charge!=200 && charge>0) || (charge!=200 && beamDelay==50))
		{
			charge++;
		}
		else if(charge<=0)
		{
			beamDelay++;
		}
		g.setColor(Color.cyan);
		g.fillRect(600, 0, charge, 10);
		//acceleration energy
		if(KeyInteraction.accelerate()&&accelEnergy>0)
		{
			acceleration = 3;
			accelEnergy-=10;
			accelDelay = 0;
		}
		else
		{
			acceleration = 1;
			accelDelay++;
			if(accelEnergy!=200 && accelDelay>=100)
			{
				accelEnergy++;
			}
		}
		g.setColor(Color.blue);
		g.fillRect(200, 0, accelEnergy, 10);
		
		Polygon starFighter = new Polygon();
		g.setColor(Color.blue);
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
		g.fillPolygon(starFighter);
		g.setColor(Color.darkGray);		
//		//cockpit
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
		shipX = x;
		shipY = y;
		bounds = starFighter;
		advanceStarShip();
		changeAngle();
	}
	public void changeAngle()
	{
		if(KeyInteraction.rotateRight())
		{
			theta+=(acceleration*2);
			shipAngle = theta;
			targetX = Unit.getShipX()+(int)(2000*Math.cos((theta)/57.2957795));
			targetY = Unit.getShipY()+(int)(2000*Math.sin((theta)/57.2957795));
		}
		if(KeyInteraction.rotateLeft())
		{
			theta-=(acceleration*2);
			shipAngle = theta;
			targetX = Unit.getShipX()+(int)(2000*Math.cos((theta)/57.2957795));
			targetY = Unit.getShipY()+(int)(2000*Math.sin((theta)/57.2957795));
		}
	}
	public void advanceStarShip()
	{
		if(KeyInteraction.moveForward()||KeyInteraction.moveBackward())
		{
			if(KeyInteraction.moveForward())
			{
				direction = acceleration;
			}
			else if(KeyInteraction.moveBackward()) 
			{
				direction = -acceleration;
			}
			targetX = Unit.getShipX()+(int)(2000*Math.cos((theta)/57.2957795));
			targetY = Unit.getShipY()+(int)(2000*Math.sin((theta)/57.2957795));
			length = (int)squareRoot(square(targetX-Unit.getShipX())+square(targetY-Unit.getShipY()));
			yVelocity = (targetY-Unit.getShipY())/length;
			xVelocity = (targetX-Unit.getShipX())/length;
			yChange = (int)(direction*(yVelocity*6));
			xChange = (int)(direction*(xVelocity*6));
		}
		else
		{
			xChange = 0;
			yChange = 0;
		}
		x=x+xChange;
    	y=y+yChange;
    	if(x>leGame.xMax)
    	{
    		x=0;
    	}  
    	if(x<0)
    	{
    		x=leGame.xMax;
    	}  
    	if(y>leGame.yMax)
    	{
    		y=0;
    	}
    	if(y<0)
    	{
    		y = leGame.yMax;
    	}
	}
	// objects of the unit class manage their own shots
	public void drawShots(Graphics g,boolean shotsFired)
	{
		this.shotsFired = shotsFired;
		if(shotsFired==true&&charge>0)
        {
			charge-=2;
			if(charge<=0)
			{
				beamDelay = 0;
			}
			//to get the appropriate amount of shots per second
			shotDelay++;
			if(shotDelay==2)
			{
				Beam shot = new Beam(getX(), getY(),targetX,targetY,xMax,yMax);
				shots.add(shot);
			}
			if(shotDelay == 4)
			{
				//so that the shots come out of different sides of the fighter
				Beam shot = new Beam(getAltX(),getAltY(),targetX,targetY,xMax,yMax);
				shots.add(shot);
				shotDelay = 0;
			}
        }
//		collision = Game.collisionDetected();
//		if(collision==true)
//		{
//			collision = false;
//		}
		//draws and manages shots on the screen
		for(int z=0; z<shots.size();z++)
	    {
			if(shots.size()>0)
			{
				shots.get(z).drawShot(g);
			}
	    }
	}
	public void drawBeams(Graphics g,boolean beamFired)
	{
		if(beamFired==true && charge>0)
        {
			this.beamFired = beamFired;
			beamDelay = 0;
			Beam beam = new Beam(x, y,targetX,targetY,leGame.xMax,leGame.yMax);
			beams.add(beam);
			charge = 0;
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
	public static double getTheta()
	{
		return shipAngle;
	}
	public static int getShipX()
	{
		return shipX;
	}
	public static int getShipY()
	{
		return shipY;
	}
	public boolean shotsFired()
	{
		return shotsFired;
	}
	public boolean beamFired()
	{
		return beamFired;
	}
	private int getX()
	{
		//right side of ship
		shipX = shipX+(int)(15.3*Math.cos((shipAngle-121.6)/57.2957795));
		return shipX;
	}
	private int getY()
	{
		shipY = shipY+(int)(15.3*Math.sin((shipAngle-121.6)/57.2957795));
		return shipY;
	}
	private int getAltX()
	{
		//let side of ship
		shipX = shipX+(int)(15.3*Math.cos((shipAngle+180-121.6)/57.2957795));
		return shipX;
	}
	private int getAltY()
	{
		shipY = shipY+(int)(15.3*Math.sin((shipAngle+180-121.6)/57.2957795));
		return shipY;
	}
	public ArrayList getBeams()
	{
		return beams;
	}
	public int getHealth()
	{
		return health;
	}
	public void decreaseHealth(int damage)
	{
		health-=damage;
	}
	public ArrayList getShots()
	{
		return shots;
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