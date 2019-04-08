package platform.game;

import platform.util.*;

/**
 *A Signal that is active or not depending on the activation
 */
public class Lever extends Actor implements Signal{

	private boolean value=false;
	private Vector position;
	private final double SIZE=1;
	private final double duration=Double.MAX_VALUE;
	private double time;
	private final int PRIORITY=100;
	/**
	 *@param position position of the lever
	 */
	public Lever(Vector position){
		if(position == null ){
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
	public Vector getPosition(){
		return position;
	}

	@Override
	public Box getBox(){
		return new Box(position,SIZE,SIZE);
	}

	@Override
	public boolean isActive(){
		return value;
	}

	@Override
	public void update(Input input){
		super.update(input);
		if(value){
			time-=input.getDeltaTime();
		}
		if(time<0){
			value=false;
		}
	}

	@Override
	public boolean hurt(Actor instigator,Damage type,double amount, Vector location){
		switch(type){
		case ACTIVATION :
			if(amount>0){
				value = !value;
				time=duration;
				return true;
			}

			else
				return false;
		default:
			return super.hurt(instigator,type,amount,location);

		}
	}

	@Override
	public void draw(Input input,Output output){
		if(value)
			output.drawSprite(getWorld().getLoader().getSprite("lever.right"), new Box(position,SIZE,SIZE));
		else
			output.drawSprite(getWorld().getLoader().getSprite("lever.left"), new Box(position,SIZE,SIZE));
	}


}
