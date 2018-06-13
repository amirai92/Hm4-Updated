package State;

import game.racers.IRacer;

public class Active implements State {

	public Active(){
	}

	

	@Override
	public synchronized void show (IRacer racer){
		
		int finished = racer.getArena().getCompleatedRacers().size();
		int rplace = 0;
		for(IRacer r : racer.getArena().getActiveRacers()) {
			if (r.getCurrentLocation().getX() > racer.getCurrentLocation().getX())
				rplace++;
		}
		 
		System.out.println(racer.getName()+"back to active in place #"+ (racerplace+finished) );
		
	}
	@Override
	public String toString() {
		return "active";
	}

	@Override
	public void setState(IRacer racer) {
	}
}
