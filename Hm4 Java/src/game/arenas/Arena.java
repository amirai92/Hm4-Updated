package game.arenas;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import State.Active;
import State.*;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.IRacer;
import game.racers.Racer;
import utilities.EnumContainer;
import utilities.EnumContainer.RacerEvent;
import utilities.Point;

public abstract class Arena implements Observer {

	private final static int MIN_Y_GAP = 100;
	private final double FRICTION;
	private final int MAX_RACERS;
	private double length;
	private long start;
	private final State active = new Active();
	private final State disabled = new Disabled();
	private final State broken = new Broken();
	private final State completed = new Completed();
	private ArrayList<IRacer> activeRacers;
	private ArrayList<IRacer> brokenRacers;
	private ArrayList<IRacer> compleatedRacers;
	private ArrayList<IRacer> disabledRacers;

	public Arena(double length, int maxRacers, double friction) {
		this.setLength(length);
		this.MAX_RACERS = maxRacers;
		this.FRICTION = friction;
		this.setActiveRacers(new ArrayList<IRacer>());
		this.setCompleatedRacers(new ArrayList<IRacer>());
		this.setBrokenRacers(new ArrayList<IRacer>());
		this.setDisabledRacers(new ArrayList<IRacer>());
	}

	public void addRacer(IRacer newRacer) throws RacerLimitException, RacerTypeException {
		((Racer) newRacer).addObserver(this);
		synchronized (activeRacers) {
			if (this.activeRacers.size() == this.MAX_RACERS) {
				throw new RacerLimitException(this.MAX_RACERS, ((Racer) newRacer).getSerialNumber());
			}
			this.activeRacers.add(newRacer);
			newRacer.setArena(this);
			active.setState(newRacer);
		}
		
	}

	@Deprecated
	public void crossFinishLine(IRacer racer) {
		this.compleatedRacers.add(racer);
		this.activeRacers.remove(racer);
		if (this.activeRacers.size() == 0) {

		}
	}

	public ArrayList<IRacer> getActiveRacers() {
		synchronized (activeRacers) {
			return activeRacers;
		}
	}

	public ArrayList<IRacer> getBrokenRacers() {
		synchronized (activeRacers) {
			return brokenRacers;
		}
	}

	public ArrayList<IRacer> getCompleatedRacers() {
		synchronized (activeRacers) {
			return compleatedRacers;
		}
	}

	public ArrayList<IRacer> getDisabledRacers() {
		synchronized (activeRacers) {
			return disabledRacers;
		}
	}

	public double getLength() {
		return length;
	}

	public boolean hasActiveRacers() {
		synchronized (activeRacers) {
			return this.activeRacers.size() > 0;
		}
	}

	public void initRace() {
		int y = 0;
		synchronized (activeRacers) {
			for (IRacer racer : this.activeRacers) {
				Point s = new Point(0, y);
				Point f = new Point(this.length, y);
				racer.initRace(this, s, f, this.FRICTION);
				y += Arena.MIN_Y_GAP;
			}
		}
	}

	@Deprecated
	public void playTurn() {
		for (IRacer racer : this.activeRacers) {
			racer.move();
		}
		for (IRacer r : this.compleatedRacers)
			this.activeRacers.remove(r);
	}

	public void setActiveRacers(ArrayList<IRacer> arrayList) {
		this.activeRacers = arrayList;
	}

	public void setBrokenRacers(ArrayList<IRacer> arrayList) {
		this.brokenRacers = arrayList;
	}

	public void setCompleatedRacers(ArrayList<IRacer> arrayList) {
		this.compleatedRacers = arrayList;
	}

	public void setDisabledRacers(ArrayList<IRacer> arrayList) {
		this.disabledRacers = arrayList;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void showResults() {
		synchronized (activeRacers) {
			int i = 1;
			System.out.println("Compleated");
			for (IRacer r : this.compleatedRacers) {
				String s = "#" + i++ + " -> ";
				s += r.describeRacer();
				System.out.println(s);
			}
			System.out.println("Disabled");
			for (IRacer r : this.disabledRacers) {
				String s = "#" + i++ + " -> ";
				s += r.describeRacer();
				System.out.println(s);
			}
			// TEST verify list is empty
			System.out.println("Broken");
			for (IRacer r : this.brokenRacers) {
				String s = "#" + i++ + " -> ";
				s += r.describeRacer();
				System.out.println(s);
			}
			// TEST verify list is empty
			System.out.println("Active");
			for (IRacer r : this.activeRacers) {
				String s = "#" + i++ + " -> ";
				s += r.describeRacer();
				System.out.println(s);
			}
		}
	}

	
	
	
	
	
	
	public void startRace() throws InterruptedException {
		initRace();
		this.setStart(System.nanoTime());
		ExecutorService e;
		synchronized (activeRacers) {
			System.out.println(this.activeRacers.size());
			e = Executors.newFixedThreadPool(this.activeRacers.size());
			synchronized (this) {
				for (IRacer racer : activeRacers) {
					e.execute((Racer)racer);
				}
			}
		}
		e.shutdown();
		e.awaitTermination(10, TimeUnit.MINUTES);
	}

	@Override
	public void update(Observable o, Object arg) {

		Racer racer = (Racer) o;
		
		
		switch ( (EnumContainer.RacerEvent)arg ) {
		case BROKENDOWN:
			synchronized (this.activeRacers) {
				racer.setState(broken);
				this.activeRacers.remove(racer);
				this.brokenRacers.add(racer);
			}
			break;
		case FINISHED:
			synchronized (this.activeRacers) {
				racer.setState(completed);
				this.activeRacers.remove(racer);
				this.brokenRacers.remove(racer);
				this.compleatedRacers.add(racer);
			}
			break;
		case REPAIRED:
			synchronized (this.activeRacers) {
				racer.setState(active);
				this.brokenRacers.remove(racer);
				this.activeRacers.add(racer);
			}
			break;
		case DISABLED:
			synchronized (this.activeRacers) {
				racer.setState(disabled);
				this.activeRacers.remove(racer);
				this.disabledRacers.add(racer);
			}
			break;
		}
	}

	public long getStart() {
		return start;
	}

	private void setStart(long start) {
		this.start = start;
	}


}
