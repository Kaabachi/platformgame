package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;
/**
 *An immovable actor that hurts the player if it touches it
 */
public class TurningSpike extends Actor{

	private double cooldown;
	private double SIZE=1.5;
	private Vector position;
	private final int PRIORITY=100;
	/**
	 *@param position the position of the TurningSpike
	 */
	public TurningSpike(Vector position) {
		if(position==null){
			new NullPointerException();
		}
		else{
			this.position=position;
		}

	}
	@Override
	public Box getBox(){
		return new Box(position,SIZE,SIZE);

	}
	@Override
	public int getPriority() {

		return PRIORITY;
	}
	@Override
	public void interact(Actor other){
		if((other.getBox()!=null)&&(cooldown<=0)){
			if(getBox().isColliding(other.getBox())){
				if(other.hurt(this, Damage.FIRE, 1 , getPosition())){
					cooldown=0.5;
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
		//we added 10*input.getTime so that the turningspikes rotates in a fast manner
		output.drawSprite(getWorld().getLoader().getSprite("spike.rotate"),getBox(),10*input.getTime());

	}


}
