package factory.builder;

import game.racers.Racer;
import game.racers.land.Car;

public class CarRaceBuilder {

	public CarRaceBuilder(int n){
		Race arena = new Race();
		arena.setArena("LandArena");
		Racer car = new Car();
		
		for(int i=0;i<n;i++) {
			arena.setRacer(car);
		}
		
		try {
			arena.getArena().startRace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		arena.getArena().showResults();
	}

}
