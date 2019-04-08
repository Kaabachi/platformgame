package platform.game;
import platform.util.*;
/**
 *An immovable actor that hurts with PHYSICAL damage if the player if falling on it
 */
public class Spike extends Actor {
	private Vector position;
	private final double SIZE =0.7;
	private double cooldown;
	private final int PRIORITY=100;
	/**
	 *@param position the position of the spike
	 */
	public Spike(Vector position){
		if(position == null){
			throw new NullPointerException();
		}
		else{
		this.position=position;
		}
	}

	@Override 
	public int getPriority(){
		return PRIORITY;
	}
	@Override
	public Box getBox(){
		return new Box(position,SIZE,SIZE);

	}

	@Override
	public void interact(Actor other){
		if((other.getBox()!=null)&&(cooldown<=0)){
			if(getBox().isColliding(other.getBox())){
				if(other.hurt(this, Damage.PHYSICAL, 1 , position)){
					cooldown=2;
				}
			}
		}
	}


	@Override
	public void update(Input input){
		cooldown -= input.getDeltaTime();
	}

	@Override
	public void draw(Input input,Output output){

		output.drawSprite(getWorld().getLoader().getSprite("spikes"),getBox());

	}

}
