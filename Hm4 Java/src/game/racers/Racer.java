package game.racers;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import State.State;
import game.arenas.Arena;
import utilities.EnumContainer;
import utilities.EnumContainer.RacerEvent;
import utilities.Fate;
import utilities.Mishap;
import utilities.Point;

public abstract class Racer extends Observable implements IRacer ,Runnable{
	protected static int lastSerialNumber = 1;

	public static int getLastSerialNumber() {
		return lastSerialNumber++;
	}

	private int serialNumber; // Each racer has an unique number, assigned by arena in addRacer() method
	private String name;
	private Hashtable <String,ArrayList<Object> > atters = new Hashtable<String, ArrayList<Object> >();
	private Point currentLocation;
	private Point finish;
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;
	private double failureProbability; // Chance to break down
	//private EnumContainer.Color color; // (RED,GREEN,BLUE,BLACK,YELLOW)
	private Mishap mishap;
	private double arenaFriction;
	//private RacerState state;
	private Arena arena;
	private Observer a;
	private State state;

	/**
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 */
	public Racer(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) {
		this.setSerialNumber( Racer.getLastSerialNumber());
		this.setName(name);
		this.setMaxSpeed(maxSpeed);
		this.setAcceleration(acceleration);
		atters.put("numOfWheels", new ArrayList<Object>());
		addAttribute("numOfWheels",0);
		atters.put("color", new ArrayList<Object>());
		addAttribute("color", color);
		// DEV init here instead of set method
		this.setFailureProbability(0.2);
		
	}
	
	public Racer(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color,int num) {
		this.setSerialNumber( Racer.getLastSerialNumber());
		this.setName(name);
		this.setMaxSpeed(maxSpeed);
		this.setAcceleration(acceleration);
		atters.put("numOfWheels", new ArrayList<Object>());
		addAttribute("numOfWheels",num);
		atters.put("color", new ArrayList<Object>());
		addAttribute("color", color);
		// DEV init here instead of set method
		this.setFailureProbability(0.2);
		
	}

	public abstract String className();

	public abstract Racer clone();
	
	
	public String describeRacer() {
		String s = "";
		s += "name: " + this.name + ", ";

		// DEV print location
		s += " @" + this.currentLocation + ", ";
		
		s += "SerialNumber: " + this.serialNumber + ", ";
		s += "maxSpeed: " + this.maxSpeed + ", ";
		s += "acceleration: " + this.acceleration + ", ";
		s += "color: " + this.atters.get("color") + ", ";
		if(  getNumOfWheels()>0 )
			s += "Num of wheels: " + getNumOfWheels() + ", ";
		s = s.substring(0, s.length() - 2);
		s += this.describeSpecific();
		return s;
	}

	public abstract String describeSpecific();

	public double getAcceleration() {
		return acceleration;
	}

	public Arena getArena() {
		return arena;
	}

	public double getArenaFriction() {
		return arenaFriction;
	}

	public EnumContainer.Color getColor() {
		return (EnumContainer.Color)this.atters.get("color").get(this.atters.get("color").size()-1);
	}

	public int getNumOfWheels() {
		int sum=0;
		for(Object i: this.atters.get("numOfWheels") ) {
			sum += (int) i;
		}
		return sum;
	}
	
	public String getColors() {
		return this.atters.get("color").toString();
	}
	
	public String getWheels() {
		return this.atters.get("numOfWheels").toString();
	}
	
	
	public Point getCurrentLocation() {
		return currentLocation;
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public double getFailureProbability() {
		return failureProbability;
	}

	public Point getFinish() {
		return finish;
	}

	public Point getLocation() {
		return this.currentLocation;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public Mishap getMishap() {
		return mishap;
	}

	public String getName() {
		return name;
	}

	public int getSerialNumber() {
		return serialNumber;
	}


	
	public void setState(State state) {
		
		
		String msg = (state.toString() );
		RacerEvent evn =null;
		if(msg.equals("broken"))
			evn=RacerEvent.BROKENDOWN;
		
		if(msg.equals("completed"))
			evn=RacerEvent.FINISHED;
		
		if(msg.equals("active"))
			evn=RacerEvent.REPAIRED;
		
		if(msg.equals("disabled"))
			evn=RacerEvent.DISABLED;
		
		//arena.update(this, evn);
	}
	public State getState() {
		return state;
	}
	
	public void addAttribute(String str,Object obj) {	
		atters.get(str).add(obj);
	}
	
	
	@Override
	public void introduce() {
		// Prints a line, obtained from describeRacer(), with its type
		System.out.println("[" + this.className() + "] " + this.describeRacer());
	}
	
	private boolean hasMishap() {
		if (this.mishap != null && this.mishap.getTurnsToFix() == 0) {
			this.setMishap(null);
			this.setChanged();
			this.notifyObservers(RacerEvent.REPAIRED);
		}
		return this.mishap != null;
	}

	public void initRace(Arena arena, Point start, Point finish, double friction) {
		this.setArena (arena);
		a=arena;
		this.setCurrentLocation (new Point(start));
		this.setFinish(new Point(finish));
		this.setArenaFriction(friction);
	}
	

	

	private boolean isDisabled() {
		if (this.mishap != null) {
			return this.mishap.isFixable() == false;
		}
		return false;
	}

	public void move() {
		double reductionFactor = 1;
		if (!(this.hasMishap()) && Fate.breakDown(this.failureProbability)) {
			this.mishap = Fate.generateMishap();
			this.setChanged();
			System.out.println(this.name + " Has a new mishap! (" + this.mishap + ")");
			if (this.isDisabled()) {
				notifyObservers(RacerEvent.DISABLED);
				return;
			} else {
				this.notifyObservers(RacerEvent.BROKENDOWN);
			}
		}

		if (this.hasMishap()) {
			reductionFactor = mishap.getReductionFactor();
			this.mishap.nextTurn();
		}

		if (this.currentSpeed < this.maxSpeed) {
			this.setCurrentSpeed(this.currentSpeed + this.acceleration * this.arenaFriction);
		}

		double newX = (this.currentLocation.getX() + this.currentSpeed) * reductionFactor;
		Point newLocation = new Point(newX, this.currentLocation.getY());
		this.setCurrentLocation(newLocation);
	}

	private boolean raceInPrograss() {
		return this.currentLocation.getX() < this.finish.getX();
	}

	@Override
	public void run() {
		while (this.raceInPrograss() && this.isDisabled() == false) {
			this.move();
			// DEV disable sleep
			// try {
			// Thread.sleep(100);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}
		if (this.isDisabled())
			return;
		setChanged();
		this.notifyObservers(RacerEvent.FINISHED);
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public boolean setArena(Arena arena) {
		this.arena = arena;
		return true;
	}

	private void setArenaFriction(double friction) {
		this.arenaFriction = friction;
	}


	public void setCurrentLocation(Point currentLocation) {
		if (this.finish != null && currentLocation.getX() > finish.getX()) {
			currentLocation.setX(finish.getX());
		}
		this.currentLocation = currentLocation;
	}

	private void setCurrentSpeed(double currentSpeed) {
		if (currentSpeed > this.maxSpeed)
			currentSpeed = this.maxSpeed;
		this.currentSpeed = currentSpeed;
	}

	public void setFailureProbability(double failureProbability) {
		if (failureProbability >= 0 && failureProbability <= 1)
			this.failureProbability = failureProbability;
	}

	public void setFinish(Point finish) {
		this.finish = finish;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setMishap(Mishap mishap) {
		this.mishap = mishap;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}


}
