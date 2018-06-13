package game.racers.land;

import java.util.Scanner;

import game.racers.Racer;
import game.racers.air.Airplane;
import utilities.EnumContainer;
import utilities.EnumContainer.Breed;
import utilities.EnumContainer.Color;

public class Horse extends Racer implements ILandRacer {

	private static final String CLASS_NAME = "Horse";

	private static final double DEFAULT_MAX_SPEED = 50;
	private static final double DEFAULT_ACCELERATION = 3;
	private static final Color DEFAULT_color = Color.BLACK;

	private static final int DEFAULT_WHEELS = 0;

	private EnumContainer.Breed breed;

	public Horse() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color,0);
	}

	public Horse(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color,int num) {
		super(name, maxSpeed, acceleration, color,num);
		
		if(this.getNumOfWheels()<DEFAULT_WHEELS)
			this.addAttribute("numOfWheels", DEFAULT_WHEELS);
		
		this.setBreed(Breed.THOROUGHBRED);
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		String s = "";
		s += ", Breed: " + this.breed;
		return s;
	}

	public EnumContainer.Breed getBreed() {
		return breed;
	}

	public void setBreed(EnumContainer.Breed breed) {
		this.breed = breed;
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
		Horse clone=new Horse(this.getName(),this.getMaxSpeed(),this.getAcceleration(),col,this.getNumOfWheels());
		return clone;
	}

}
