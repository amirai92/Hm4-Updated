package game.racers.decorator;

import State.State;
import game.arenas.Arena;
import game.racers.IRacer;
import game.racers.Racer;
import utilities.Point;

public abstract class RacerDecorator implements IRacer {

	private IRacer decoratedRacer;

	public RacerDecorator(IRacer racer){
		this.decoratedRacer = racer;
	}
	
	
	
	@Override
	public void addAttribute(String str,Object obj) {
		decoratedRacer.addAttribute(str, obj);
	}
		
	@Override
	public void introduce() {
		decoratedRacer.introduce();		
	}
	

	@Override
	public void run() {
		decoratedRacer.run();
	}
	
	@Override
	public String describeRacer() {
		return decoratedRacer.describeRacer();
	}
	
	
	@Override
	public String className() {
		return decoratedRacer.className();
	}
	
	@Override
	public Racer clone() {
		return decoratedRacer.clone();
	}
	
	@Override
	public void initRace(Arena arena, Point start, Point finish, double friction) {
		decoratedRacer.initRace(arena,start,finish,friction);
	}
	
	
	public void move() {
		decoratedRacer.move();
	}
	
	
	public Point getCurrentLocation() {
		return decoratedRacer.getCurrentLocation();
	}
	
	public void setState(State state) {
		decoratedRacer.setState(state);
	}
	
	public Arena getArena() {
		return decoratedRacer.getArena();
	}

	
	
	public String getName() {
		return decoratedRacer.getName();
	}

	public boolean setArena(Arena arena) {
		return decoratedRacer.setArena(arena);
	}

}
