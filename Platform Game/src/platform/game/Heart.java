package platform.game;

import platform.util.*;
/**
 *An object that a player can pick up to regain health
 */
public class Heart extends Actor {
	private double cooldown=0;
	private Vector position;
	private final double SIZE = 0.5;
	private final int PRIORITY=100;
	/**
	 *@param position position of the heart
	 */
	public Heart(Vector position){
		this.position=position;


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
			if(other.getBox().isColliding(getBox())){
				if(other.hurt(this, Damage.HEAL, 1.0, getPosition())){
					cooldown=10;

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

		if(cooldown<=0){
			output.drawSprite(getWorld().getLoader().getSprite("heart.full"), new Box(position,SIZE,SIZE));
		}
	}



}
