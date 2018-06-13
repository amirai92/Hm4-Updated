package game.racers;

import State.State;
import game.arenas.Arena;
import utilities.Point;

public interface IRacer extends Cloneable {

	public void addAttribute(String str,Object obj);
	public void introduce();
	
	

	public Point getCurrentLocation();
	public void setState(State state);
	public Arena getArena();
	public String getName();
	public void initRace(Arena arena, Point start, Point finish, double friction);
	public void move();
	public boolean setArena(Arena arena);
	public void run();
	public String describeRacer();
	public String className();
	public Racer clone();
}
