package State;

import game.racers.IRacer;

public class Disabled implements State{
	
	public Disabled() {
		
	}

	
	@Override
	public void show(IRacer racer) {
		System.out.println(racer.getName() + "Failed");
	}
	
	@Override
	public String toString(){
		return "disabled";
	}
	@Override
	public void setState(IRacer newRacer) {
		newRacer.setState(this);
		
	}
}
