package factory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import game.arenas.Arena;


public class ArenaFactory {
	private HashMap<String,Class<?>> arenas = new HashMap<String,Class<?>>();
	
	public ArenaFactory(){
		List<Class<?>> listOfArenas = RacingClassesFinder.getInstance().getArenasList();
		int i=0;
		for (String string : RacingClassesFinder.getInstance().getArenasNamesList()) {
			arenas.put(string, listOfArenas.get(i++));
		}
	}

	public Arena getArena(String arenaType) {
		String arenaT = new String(arenas.get(arenaType).toString().substring(6));
		try {
			return RaceBuilder.getInstance().buildArena(arenaT, 1000, 8);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public Arena getArena(String arenaType, int num, double length) {
		String arenaT = new String(arenas.get(arenaType).toString().substring(6));
		try {
			return RaceBuilder.getInstance().buildArena(arenaT, 1000, 8);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
