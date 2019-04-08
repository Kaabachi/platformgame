package platform.game;




import platform.util.*;


import platform.util.Sprite;


/**
 * Base class of all simulated actors, attached to a world.
 */
public abstract class Actor implements Comparable<Actor> {
	private World world;
	//attribut disponible a tous les autres acteurs
	//et qui leur permet de tester si ils interagissent avec un player
	protected final int PLAYER_PRIORITY=42;
	
	
	
	/**
	 * @param instigator the actor that will be hurt
	 * @param type type of damage that hurts the actor
	 * @param amount amount of damage hurting the actor
	 * @param position position where the damage takes place
	 */
	public boolean hurt(Actor instigator,Damage type,double amount, Vector location){
		return false;
	}
	/**
	 * Update the state of an actor.
	 * @param input useful for actor such as time, mouse and keyboard
	 */
	public void preUpdate(Input input){}


	/**
	 * Update the state of an actor.
	 * @param input useful for actor such as time, mouse and keyboard
	 */
	public void update(Input input){} 


	/**
	 * Update the state of an actor.
	 * @param input useful for actor such as time, mouse and keyboard
	 */
	public void postUpdate(Input input){}


	/**
	 * Draw an actor.
	 * @param input useful for actor such as time, mouse and keyboard
	 * @param output , provides a rendering context, with its own coordinate systems
	 */
	public void draw(Input input, Output output){}


	/**
	 * @return Return the priority of an actor.
	 */
	public abstract int getPriority();


	/**
	 * Interactions between actors, such as collisions/hurting ...
	 * @param actor that the interaction is happening with
	 */
	public void interact(Actor actor){}

	/**
	 * @return Return a boolean that informs if an actor is solid or not.
	 */
	public boolean isSolid(){
		return false ;
	}
	/**
	 * @return Returns the box of the actor, which may be used to draw/find collisions.
	 */
	public Box getBox(){
		return null;
	}
	
	/**
	 * @return Return the position of an actor as a Vector.
	 */
	public Vector getPosition(){
		Box box =getBox();
		if(box == null){
			return null;
		}
		return box.getCenter() ;
	}

	/**
	 * Compares the priority of the original actor with other registered actors 
	 * @param other  that the comparision is happening with
	 * @return returns 1,-1 or 0 depending on which priority is higher.
	 */
	@Override 
	public int compareTo(Actor other){
		if (this.getPriority()<other.getPriority()) {
			return 1;
		}else if(this.getPriority()>other.getPriority()){
			return -1;
		}else 
			return 0;
	}
	/**
	 * registers an environment to later be populated by actors
	 * @param world - an environment populated by actors
	 */
	public void register(World world){
		this.world=world;

	}
	/**
	 * unregisters an environment
	 */
	public void unregister(){
		world=null;
	}


	/**
	 * @param name - path to a sprite
	 * @return returns a sprite, an immutable RGBA image
	 */
	protected Sprite getSprite(String name){

		if(world==null){
			return null;
		}
		else{

			Sprite sprite = world.getLoader().getSprite(name);
			return sprite;
		}
	}
	protected World getWorld(){
		return world;
	}




}