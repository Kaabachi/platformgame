package platform.game;
import platform.util.*;
/**
 *An actor that has two states that implement a signal and can be lit or blown off by he player
 */
public class Torch extends Actor implements Signal {
	private Vector position;
	private final double SIZE = 0.8;
	private boolean lit;
	private double variation=0;
	private final int PRIORITY=34;

	/**
	 *@param position the position of the torch
	 *@param lit the state of the torch
	 */
	public Torch(Vector position,boolean lit){
		this.position=position;
		this.lit=lit;

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
	public boolean isActive(){
		return lit;
	}

	@Override
	public boolean hurt(Actor instigator,Damage type,double amount,Vector location){
		switch(type){
		case AIR:
			if(lit){
				lit=false;
				return true;
			}
			else{
				return false;
			}
		case FIRE:
			if(!lit){
				lit=true;
				return true;
			}

		default:
			return super.hurt(instigator, type, amount, location);
		}


	}
	@Override
	public void update(Input input){
		//to get a subtle animation of the lit torch
		variation -= input.getDeltaTime();
		if(variation <0.0){
			variation = 0.6;
		}
	}


	@Override
	public void draw(Input input, Output output){
		if(lit){
			String name = "torch.lit.1";
			if(variation<0.3)
				name="torch.lit.2";
			output.drawSprite(getWorld().getLoader().getSprite(name), new Box(position,SIZE,SIZE));
		}
		else{
			output.drawSprite(getWorld().getLoader().getSprite("torch"), new Box(position,SIZE,SIZE));
		}
	}







}
