package platform.game;


import platform.util.*;


/**
 * A block that can be destroyed in 2 hits
 */
public class Destructible extends Block implements Signal {
	private boolean destruct=false;
	private Sprite spriteDamaged;
	private double health=2.0;

	/**
	 * @param box that the block will be drawn within
	 * @param spriteUndamaged  the sprite that represents the block when it is not damaged
	 * @param spriteDamaged the sprite that represents the block after it took one damage
	 */
	public Destructible(Box box, Sprite spriteUndamaged, Sprite spriteDamaged) {
		super(box, spriteUndamaged);
		this.spriteDamaged=spriteDamaged;

	}

	@Override
	public boolean isActive(){
		return destruct;
	}

	@Override
	public boolean hurt(Actor instigator,Damage type,double amount,Vector location){
		switch(type){

		case FIRE:
			health=health-amount;

			if(health==0)
				destruct=true;


			return true;

		default :
			return super.hurt(instigator, type, amount, location);
		}
	}


	@Override
	public boolean isSolid(){
		return !destruct;
	}
	/**
	 * @return returns the current health of the destructible
	 */
	public double getHealth(){
		return health;
	}
	@Override
	public void update(Input input){
		if (destruct)
			getWorld().unregister(this);

	}

	@Override
	public void draw(Input input,Output output){
		if(health==2.0){
			super.draw(input, output);
		}else if(health==1.0){
			if(spriteDamaged ==null ){
				throw new NullPointerException();
			}else{
				//draws a damaged sprite of the block if it has been hit
				output.drawSprite(spriteDamaged, getBox());

			}


		}
	}

}





