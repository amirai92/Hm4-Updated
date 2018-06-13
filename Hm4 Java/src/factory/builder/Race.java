package factory.builder;

import factory.ArenaFactory;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.IRacer;


public class Race implements RacePlan{
	
	private Arena arena;

	public Race() {
	}


	@Override
	public void setArena(String arena) {
		this.arena = (new ArenaFactory()).getArena(arena);
	}

	@Override
	public void setRacer(IRacer racer) {
		
		try {
			arena.addRacer(racer.clone());
		} catch (RacerLimitException e) {
			e.printStackTrace();
		} catch (RacerTypeException e) {
			e.printStackTrace();
		}
		
	}


	public Arena getArena() {
		return arena;
	}

}
