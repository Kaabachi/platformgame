package platform.game;

import platform.game.level.Level;

import java.util.ArrayList;
import java.util.List;
import platform.util.*;

/**
 * Basic implementation of world, managing a complete collection of actors.
 */
public class Simulator implements World {

	private Loader loader;
	private Vector currentCenter;
	private double currentRadius;
	private Vector expectedCenter=Vector.ZERO;
	private double expectedRadius=10.0;
	private List<Actor> registered =new ArrayList<Actor>();
	private List<Actor> unregistered =new ArrayList<Actor>();
	private SortedCollection<Actor> actors = new SortedCollection<Actor>() ;
	private Level next=null;
	private boolean transition=true;





	/**
	 * Create a new simulator.
	 * @param loader associated loader, not null
	 * @param args level arguments, not null
	 */
	public Simulator(Loader loader, String[] args) {
		if (loader == null)
			throw new NullPointerException();
		this.loader = loader;
		currentCenter = Vector.ZERO;
		currentRadius=10;
		registered.clear();
		unregistered.clear();



	}
	@Override
	public void register(Actor actor){
		actor.register(this);
		registered.add(actor);


	}
	@Override
	public void unregister(Actor actor){
		actor.unregister();
		unregistered.add(actor);

	}



	@Override 
	public void setView(Vector center, double radius){
		if (center==null){
			throw new NullPointerException() ;
		}
		if (radius<=0.0){
			throw new IllegalArgumentException("radius must be positive")	;
		}

		expectedCenter= center;
		expectedRadius= radius;


	}





	/**
	 * Simulate a single step of the simulation.
	 * @param input input object to use, not null
	 * @param output output object to use, not null
	 */
	public void update(Input input, Output output) {

		//set the view
		double factor = 0.007;
		currentCenter = currentCenter.mul(1.0-factor).add(expectedCenter.mul(factor));
		currentRadius=currentRadius *(1.0 - factor )+ expectedRadius * factor ;
		View view = new View(input, output);
		view.setTarget(currentCenter, currentRadius);


		//Apply preUpdate
		for (Actor a : actors){
			a.preUpdate(view);

		}

		//Apply interactions
		for (Actor actor : actors) 
			for (Actor other : actors)
				if (actor.getPriority() > other.getPriority()) 
					actor.interact(other);


		//Add registered actor 

		for (int i=0; i<registered.size();++i){
			Actor actor =registered.get(i);
			if (!actors.contains(actor)){
				actors.add(actor);
			}
		}
		registered.clear();

		//Remove unregistered actors
		for(int i=0;i<unregistered.size();++i){
			Actor actor=unregistered.get(i);

			actors.remove(actor);
		}
		unregistered.clear(); 

		//Apply update
		for (Actor a : actors){
			a.update(view);
		}
		//Apply postUpdate
		for (Actor a : actors){
			a.postUpdate(view);
		}

		//Si un acteur a mis transition a true pour demander le passage a un autre niveau
		if(transition){
			if(next==null){
				next=Level.createDefaultLevel();
			}
			//si un acteur a appele setNextLevel, next ne sera pas nul:
			Level level = next;
			transition=false;
			next=null;
			actors.clear();
			registered.clear();
			//tous les anciens acteurs sont desenregistres
			// y compris le level precedent:
			unregistered.clear();
			register(level);
			currentCenter=new Vector(0,0);
		}




		//Draw everything
		for(Actor a :actors.descending())
		{
			a.draw(view, view);
		}







	}
	@Override
	public void setNextLevel(Level level){
		next=level;
	}
	@Override
	public void nextLevel(){
		transition=true;
	}



	@Override
	public Loader getLoader() {
		return loader;
	}

	@Override
	public Vector getGravity(){
		Vector gravity = new Vector(0.0,-9.81);
		return gravity ;
	}

	@Override
	public int hurt(Box area,Actor instigator,Damage type,double amount, Vector location){
		int victims = 0;
		for(Actor actor:actors)
			if(area.isColliding(actor.getBox()))
				if(actor.hurt(instigator, type, amount, location))
					++victims;
		return victims;
	}




}
