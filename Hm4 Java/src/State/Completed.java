package State;

import game.racers.IRacer;

public class Completed implements State {
	public Completed(){
		
	}


	@Override
	public void show(IRacer racer){
		System.out.println(racer.getName()+ "Completed" );
	}
	
	
	@Override
	public String toString() {
		return "completed";
	}
	

	@Override
	public void setState(IRacer racer) {
		racer.setState(this);
	}
}
