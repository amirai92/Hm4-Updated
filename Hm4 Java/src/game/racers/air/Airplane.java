package game.racers.air;
import java.util.Scanner;
import game.racers.Racer;
import utilities.EnumContainer.Color;

public class Airplane extends Racer implements IAerialRacer {
	private static final String CLASS_NAME = "Airplane";
	
	private static final int DEFAULT_WHEELS = 3;
	private static final double DEFAULT_MAX_SPEED = 885;
	private static final double DEFAULT_ACCELERATION = 100;
	private static final Color DEFAULT_color = Color.BLACK;

	public Airplane() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color,
				DEFAULT_WHEELS);
	}

	public Airplane(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color,
			int numOfWheels) {
		super(name, maxSpeed, acceleration, color,numOfWheels);
		if(this.getNumOfWheels()<DEFAULT_WHEELS)
			this.addAttribute("numOfWheels", DEFAULT_WHEELS);
	}

	@Override
	public String className() {
		return CLASS_NAME;
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
		Airplane clone=new Airplane(this.getName(),this.getMaxSpeed(),this.getAcceleration(),col,this.getNumOfWheels());
		return clone;
		
	}

	@Override
	public String describeSpecific() {
		return null;
	}


}
