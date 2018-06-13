package game.racers.air;

import java.util.Scanner;

import game.racers.Racer;
import utilities.EnumContainer.Color;

public class Helicopter extends Racer implements IAerialRacer {

	private static final String CLASS_NAME = "Helicopter";

	private static final double DEFAULT_MAX_SPEED = 400;
	private static final double DEFAULT_ACCELERATION = 50;
	private static final Color DEFAULT_color = Color.BLUE;
	private static final int DEFNUMOFWHEELS =0;

	public Helicopter() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color,DEFNUMOFWHEELS);
	}

	public Helicopter(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color,int num) {
		super(name, maxSpeed, acceleration, color,num);
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		return "";
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
		Helicopter clone=new Helicopter(this.getName(),this.getMaxSpeed(),this.getAcceleration(),col,this.getNumOfWheels());
		return clone;
	}
		
}


