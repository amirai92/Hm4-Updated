package State;

import game.racers.IRacer;

public class Broken implements State {
	public Broken() {
		
	}



	@Override
	public void show(IRacer racer) {
        long time=System.nanoTime()-racer.getArena().getStart();
        System.out.println(racer.getName()+"broken after"+ time+"nano seconds");
		
	}
	
	@Override
	public void setState(IRacer newRacer) {
		newRacer.setState(this);
	}

	@Override
	public String toString(){
		return "broken";
	}

	
}
