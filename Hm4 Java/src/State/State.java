package State;

import game.racers.IRacer;

public interface State {
	
	public void setState(IRacer newRacer) ;
	void show(IRacer racer);

}
