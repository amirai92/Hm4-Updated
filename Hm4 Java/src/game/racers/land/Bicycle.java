package game.racers.land;

import java.util.Scanner;

import game.racers.Racer;
import utilities.EnumContainer;
import utilities.EnumContainer.BicycleType;
import utilities.EnumContainer.Color;

public class Bicycle extends Racer implements ILandRacer {

	private static final String CLASS_NAME = "Bicycle";
	private static final String DEFUALT_NAME = CLASS_NAME + " #" + lastSerialNumber;

	private static final int DEFAULT_WHEELS = 2;
	private static final double DEFAULT_MAX_SPEED = 270;
	private static final double DEFAULT_ACCELERATION = 10;
	private static final Color DEFAULT_color = Color.GREEN;

	private EnumContainer.BicycleType type;

	public Bicycle() {
		this(DEFUALT_NAME, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color, DEFAULT_WHEELS);
	}

	public Bicycle(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color,
			int numOfWheels) {
		super(name, maxSpeed, acceleration, color,numOfWheels);
		if(this.getNumOfWheels()<DEFAULT_WHEELS)
			this.addAttribute("numOfWheels", DEFAULT_WHEELS);
		this.setType(BicycleType.MOUNTAIN);
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		String s = "";
		s += ", Bicycle Type: " + this.type;
		return s;
	}

	public EnumContainer.BicycleType getType() {
		return type;
	}



	public void setType(EnumContainer.BicycleType type) {
		this.type = type;
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
		Bicycle clone=new Bicycle(this.getName(),this.getMaxSpeed(),this.getAcceleration(),col,this.getNumOfWheels());
		return clone;
	}
		
}
