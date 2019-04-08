package platform.game;

import platform.util.*;
/**
 *An enemy actor that gives damage to the player
 */
public class Slime extends GroundEnemy {

	private final double SIZE;
	private boolean revive;
	private double delta;
	/**
	 *@param position the position of the slime
	 *@param health the health of the slime
	 *@param SIZE the size of the slime
	 *@param revive if wether or not the slime will be revived in two after its death
	 */
	public Slime(Vector position,double health,double SIZE,boolean revive){
		super(position,health);
		this.revive=revive;
		this.SIZE=SIZE;
	}


	@Override
	public void die(){
		super.die();
		if(revive){
			// creates two smaller slimes after the slimes death if revive is true
			getWorld().register(new Slime(getPosition(),1,0.6,false));
			getWorld().register(new Slime(getPosition().add(new Vector(1,0)),1,0.6,false));
		}
	}

	@Override
	public Box getBox(){
		return new Box(getPosition(),SIZE,SIZE);
	}






	@Override 
	public void update(Input input){
		delta =delta + input.getDeltaTime();
		//pour obtenir un mouvement de va et vient
		double function= Math.sin(delta);
		setVelocity(new Vector(function,0));

		setPosition(getPosition().add(getVelocity().mul(input.getDeltaTime())));

		setCooldown( super.getCooldown()-input.getDeltaTime());
		if(getHealth()==0){
			die();
			getWorld().unregister(this);
		}


	}


	@Override
	public void draw(Input input, Output output){
		if(!isDead()){
			if(getVelocity().getX()<=0){
				output.drawSprite(getWorld().getLoader().getSprite("slime.left.2"), getBox());
			}else if (getVelocity().getX()>0){
				output.drawSprite(getWorld().getLoader().getSprite("slime.right.2"), getBox());
			}

		}
	}













}
