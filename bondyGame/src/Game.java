import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game 
{
	// for managing what happens in the game...
	ArrayList<Unit> players = new ArrayList<Unit>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	Unit playerOne;
	Enemy turret;
	Enemy fighter;
	private int n;
	private static int index;
	private static boolean collisionDetected;
	private ArrayList<Beam> shots = new ArrayList<Beam>();
	private ArrayList<Beam> beams = new ArrayList<Beam>();
	
	public Game()
	{
		playerOne = new Unit(1000,500);
		players.add(playerOne);
		for(int z=0; z<0; z++)
		{
			turret = new Enemy(200,200+(z*50),'t');
			enemies.add(turret);
		}
		for(int z=0; z<2; z++)
		{
			fighter = new Enemy(100+(z*50),100,'f');
			enemies.add(fighter);
			n++;
			enemies.get(z).setIndex(n);
		}
	}
	
	public void drawGame(Graphics g)
	{
		for(int z=0; z<enemies.size();z++)
		{
			if(enemies.get(z).getHealth()<0)
			{
				enemies.remove(z);
				enemies.get(z).setIndex(enemies.get(z).getIndex()-1);
			}
			else if(enemies.get(z).getType()=='t')
			{
				enemies.get(z).drawEnemyTurret(g, playerOne.getShipX(), playerOne.getShipY());
				enemies.get(z).drawTurretShots(g, playerOne.getShipX(), playerOne.getShipY());
				checkForCollisions(playerOne,enemies.get(z), playerOne.getShots());
				checkForCollisions(playerOne,enemies.get(z), playerOne.getBeams());
				checkForCollisions(enemies.get(z),playerOne, enemies.get(z).getBeams());
			}
			else if(enemies.get(z).getType()=='f')
			{
				if(enemies.get(z).getIndex()==1)
				{
					enemies.get(z).drawEnemyFighter(g, playerOne.getShipX(), playerOne.getShipY());
					enemies.get(z).drawShots(g, playerOne.getShipX(), playerOne.getShipY());
					checkForCollisions(playerOne,enemies.get(z), playerOne.getShots());
					checkForCollisions(playerOne,enemies.get(z), playerOne.getBeams());
					checkForCollisions(enemies.get(z),playerOne, enemies.get(z).getShots());
				}
				// an attempt to get enemies to be a wingman of another... 
				else if(enemies.get(z).getIndex()%2==0)
				{
					enemies.get(z).drawEnemyFighter(g,enemies.get(z).getPortWingX(), enemies.get(z).getPortWingY());
					enemies.get(z).drawShots(g, playerOne.getShipX(), playerOne.getShipY());
					checkForCollisions(playerOne,enemies.get(z), playerOne.getShots());
					checkForCollisions(playerOne,enemies.get(z), playerOne.getBeams());
					checkForCollisions(enemies.get(z),playerOne, enemies.get(z).getShots());
				}
				else
				{
					enemies.get(z).drawEnemyFighter(g,enemies.get(z).getStarboardWingX(), enemies.get(z).getStarboardWingY());
					enemies.get(z).drawShots(g, playerOne.getShipX(), playerOne.getShipY());
					checkForCollisions(playerOne,enemies.get(z), playerOne.getShots());
					checkForCollisions(playerOne,enemies.get(z), playerOne.getBeams());
					checkForCollisions(enemies.get(z),playerOne, enemies.get(z).getShots());
				}
			}
		}
		if(playerOne.getHealth()>0)
		{
//			playerOne.drawFighter(g, Mouse.getX(), Mouse.getY());
//			playerOne.drawShots(g, Mouse.shotsFired(), Mouse.getX(), Mouse.getY());
//			playerOne.drawBeams(g, KeyInteraction.beamFired(), Mouse.getX(), Mouse.getY());
			playerOne.drawFighter(g);
			playerOne.drawShots(g, KeyInteraction.shotsFired());
			playerOne.drawBeams(g, KeyInteraction.beamFired());
		}
	}	
	public void removeShots(Enemy ship, int index)
	{
		ship.removeShots(index);
	}
	public void removeBeams(Enemy ship, int index, int subIndex)
	{
		ship.removeBeams(index, subIndex);
	}
	public void removeShots(Unit ship, int index)
	{
		ship.removeShots(index);
	}
	public void removeBeams(Unit ship, int index, int subIndex)
	{
		ship.removeBeams(index, subIndex);
	}
	public void addShot()
	{
		
	}
	public void addBeam()
	{
		
	}
	public void checkForCollisions(Enemy shooter, Unit ship, ArrayList<Beam> projectiles)
	{
		for(int z=0; z<projectiles.size(); z++)
		{
			if(ship.getBounds().contains(projectiles.get(z).getShotX(), projectiles.get(z).getShotY()))
			{
				ship.decreaseHealth(200);
				removeShots(shooter, z);
			}
			else
			{
				for(int k=0; k<projectiles.get(z).beamX.size();k++)
				{
					if(ship.getBounds().contains(projectiles.get(z).getBeamX(k), projectiles.get(z).getBeamY(k)))
					{
						ship.decreaseHealth(50);
						removeBeams(shooter, z, k);
					}
				}
			}
		}
	}
	public void checkForCollisions(Unit shooter, Enemy ship, ArrayList<Beam> projectiles)
	{
		for(int z=0; z<projectiles.size(); z++)
		{
			if(ship.getBounds().contains(projectiles.get(z).getShotX(), projectiles.get(z).getShotY()))
			{
				ship.decreaseHealth(200);
				removeShots(shooter, z);
			}
			else
			{
				for(int k=0; k<projectiles.get(z).beamX.size();k++)
				{
					if(ship.getBounds().contains(projectiles.get(z).getBeamX(k), projectiles.get(z).getBeamY(k)))
					{
						ship.decreaseHealth(50);
						removeBeams(shooter, z, k);
					}
				}
			}
		}
	}
	public static boolean collisionDetected()
	{
		return collisionDetected;
	}
	public static int getCollisionIndex()
	{
		return index;
	}
}