package game.racers.land;

import java.util.Scanner;

import game.racers.Racer;
import game.racers.air.Airplane;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Engine;

public class Car extends Racer implements ILandRacer {

	private static final String CLASS_NAME = "Car";

	private static final int DEFAULT_WHEELS = 4;
	private static final double DEFAULT_MAX_SPEED = 400;
	private static final double DEFAULT_ACCELERATION = 20;
	private static final Color DEFAULT_color = Color.RED;

	private Engine engine;

	public Car() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color,
				DEFAULT_WHEELS);
	}

	public Car(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color,
			int numOfWheels) {
		super(name, maxSpeed, acceleration, color,numOfWheels);
		if(this.getNumOfWheels()<DEFAULT_WHEELS)
			this.addAttribute("numOfWheels", DEFAULT_WHEELS);
		this.setEngine(Engine.MOUNTAIN);
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		String s = "";
		s += ", Engine Type: " + this.engine;
		return s;
	}

	public Engine getEngine() {
		return engine;
	}


	public void setEngine(Engine engine) {
		this.engine = engine;
	}


	@Override
	public Racer clone() {
		Scanner scan=new Scanner(System.in);
		Scanner scan=new Scanner(System.in);
		System.out.println("Please choose a color :\n"+ "1:RED, 2:GREEN, 3:BLUE, 4:BLACK, 5:YELLOW");
		int j =scan.nextInt();
		Color col=null;
		if(j==1) {
			col=Color.RED;
		}
		if(j==2) {
			col=Color.GREEN;
		}
		if(j==3) {
			col=Color.BLUE;
		}
		if(j==4) {
			col=Color.BLACK;
			
		}
		if( j==5) {
			col=Color.YELLOW;
		}
		Car clone=new Car(this.getName(),this.getMaxSpeed(),this.getAcceleration(),col,this.getNumOfWheels());
		return clone;
	}
		
}
