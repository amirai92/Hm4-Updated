package game.racers;


public class CloneFactory {
	
	public CloneFactory(){
	}
	
	public Racer getClone(IRacer racer) {
		return racer.clone();
	}

}
