package platform.game;


import platform.game.level.Level;
import platform.util.Box;
import platform.util.Loader;
import platform.util.Vector;


/**
 * Represents an environment populated by actors.
 */
public interface World {


	public void register(Actor actor);

	public void unregister(Actor actor);

	//permet de passer au niveau du level :
	public void setNextLevel(Level level);

	//permet d'indiquer que la transition a un autre niveau
	//doit se faire:
	public void nextLevel();

	/**
	 * set viewport location and size
	 * @param center viewport center, not null
	 * @param radius viewport radius, positive 
	 */
	public void setView(Vector center,double radius) ;



	/** @return associated loader, not null */
	public Loader getLoader();

	public Vector getGravity();

	public int hurt(Box area,Actor instigator,Damage type,double amount, Vector location);



}

