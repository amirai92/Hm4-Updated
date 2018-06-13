package game.racers.decorator;

import game.racers.IRacer;
import utilities.EnumContainer.Color;


public class ColoredRacer extends RacerDecorator {

	public String ATTRIBUTENAME ="color";

	public ColoredRacer(IRacer racer ,Color color) {
		super(racer);
		super.addAttribute(ATTRIBUTENAME, color);
	}
}
