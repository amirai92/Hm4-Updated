package game.racers.decorator;

import game.racers.IRacer;

public class WheeledRacer extends RacerDecorator {
	public String ATTRIBUTENAME ="numOfWheels";

	public WheeledRacer(IRacer racer ,int num) {
		super(racer);
		if(num<0)
			num=0;
		addAttribute(ATTRIBUTENAME, num);
	}
	

}
