package game.racers.naval;

import java.util.Scanner;

import game.racers.Racer;
import game.racers.air.Airplane;
import utilities.EnumContainer;
import utilities.EnumContainer.BoatType;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Team;

public class SpeedBoat extends Racer implements INavalRacer {
	private static final String CLASS_NAME = "SpeedBoat";

	private static final double DEFAULT_MAX_SPEED = 170;
	private static final double DEFAULT_ACCELERATION = 5;
	private static final Color DEFAULT_color = Color.RED;
	private static final int DEFNUMOFWHEELS = 0;
	private EnumContainer.BoatType type;
	private EnumContainer.Team team;

	public SpeedBoat() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color,DEFNUMOFWHEELS);
	}

	public SpeedBoat(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color,int num) {
		super(name, maxSpeed, acceleration, color,num);
		this.setType(BoatType.SKULLING);
		this.setTeam(Team.SINGLE);
	}

	public EnumContainer.BoatType getType() {
		return type;
	}

	public void setType(EnumContainer.BoatType type) {
		this.type = type;
	}

	public EnumContainer.Team getTeam() {
		return team;
	}

	public void setTeam(EnumContainer.Team team) {
		this.team = team;
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		String s = "";
		s += ", Type: " + this.type;
		s += ", Team: " + this.team;
		return s;
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
}
