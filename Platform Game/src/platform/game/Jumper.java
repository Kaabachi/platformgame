package platform.game;
import platform.util.*;
/**
 *A Jumper that inflicts air damage to a player and propulse him into the air
 */
public class Jumper extends Actor {
	private double cooldown;
	private Vector position;
	final double SIZE = 1;
	final int PRIORITY = 100;
	/**
	 *@param position position fo the jumper , not null
	 */
	public Jumper(Vector position ){
		if(position==null){
			throw new NullPointerException();
		}else{
			this.position=position;
		}
	}

	@Override
	public int getPriority(){
		return PRIORITY;
	}


	@Override
	public boolean hurt(Actor instigator,Damage type,double amount,Vector location){
		return super.hurt(instigator, type, amount, location);
	}

	@Override
	public void interact(Actor other){
		super.interact(other);
		if(cooldown<=0 && getBox().isColliding(other.getBox())){
			Vector below = new Vector(position.getX(),position.getY()-1);
			if(other.hurt(this, Damage.AIR, 10.0, below))
				cooldown=0.5;
		}
	}

	@Override
	public void update(Input input){
		super.update(input);
		cooldown -= input.getDeltaTime();

	}

	@Override
	public Box getBox(){
		return new Box (position,SIZE,SIZE);
	}


	@Override
	public void draw(Input input,Output output){
		if(cooldown>0){
			output.drawSprite(super.getWorld().getLoader().getSprite("jumper.extended"),new Box(position,SIZE,SIZE));
		}else{
			output.drawSprite(super.getWorld().getLoader().getSprite("jumper.normal"),new Box(position,SIZE,SIZE));
		}

	}


}
