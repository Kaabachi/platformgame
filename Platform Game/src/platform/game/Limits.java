package platform.game;
import platform.util.*;
/**
 *An actor that inflicts VOID damage to a player if he leaves its borders
 */
public class Limits extends Actor {
	private Box box;
	private final int PRIORITY=1000;
	/**
	 *@param box box that the limit will be drawn in
	 */
	public Limits(Box box){
		this.box=box;	
	}
	@Override
	public int getPriority(){
		return PRIORITY;
	}
	@Override
	public Box getBox(){
		return box;
	}

	@Override
	public void interact(Actor other){
		super.interact(other);
		if(!(getBox().isColliding(other.getBox()))&& other!=null)
			other.hurt(this, Damage.VOID, Double.POSITIVE_INFINITY, Vector.ZERO);
	}


}
