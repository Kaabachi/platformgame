package platform.game;
import java.awt.event.KeyEvent;


import platform.util.*;
/**
 *An actor controlled by the user
 */
public class Player extends Actor {
	private Vector position;
	private Vector velocity;
	private final double SIZE=10;//was 0.7
	private final Sprite sprite;
	private boolean colliding;
	private double health;
	private double healthMax;
	private Overlay overlay;	
	

	/**
	 *An actor controlled by the user
	 *@param position vector giving the position of the player
	 *@param velocity vector giving the velocity of the player
	 *@param sprite sprite that represents the player
	 *@param healthMax the maximum health of the player
	*/
	public Player(Vector position , Vector velocity , Sprite sprite,double healthMax){
		if((position==null) || (velocity==null)){
			throw new NullPointerException();
		}else{
		this.position=position;
		this.velocity=velocity;
		this.sprite=sprite;
		this.healthMax=healthMax;
		health=healthMax;
		overlay =new Overlay(this);
		}

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
	public int getPriority(){
		return PLAYER_PRIORITY;
	}
	/**
	 *@return return the health of the player
	 */
	public double getHealth(){
		return health;
	}
	
	/**
	 *@return return the healthMax of the player
	 */
	public double getHealthMax(){
		return healthMax;
	}
	@Override
	public boolean hurt(Actor instigator,Damage type,double amount, Vector location){
		switch(type){
		case AIR:
			if(instigator!=this){
				velocity=getPosition().sub(location).resized(amount);
				return true;
			}
			else{
				return false;
			}
		case VOID:
			die();
			return true;
		case HEAL:

			if(health<healthMax){
				if(health<=healthMax-amount){
					health=health+amount;
					return true;

				}
				else if((health>healthMax-amount)&&(health<healthMax)){
					//cest le cas ou le player a 4.5 health par exemple 
					//et le heart le heal de 1, il aura donc 5 health apres l'avoir pris
					health=healthMax;
					return true;
				}
			}
			else if ( health == healthMax){
				//si le player a tous ses pvs il ne prend pas le heart
				return false;
			}
		case PHYSICAL:
			if(velocity.getY()<-1){
				if((health>amount)){
					health=health-amount;
					return true;
				}
				else{
					health=0;
					return true;
				}
			}
			else{
				return false;
			}
		case FIRE:
			if(health>amount){
				health=health-amount;
				return true;
			}
			else{
				health=0;
				return true;
			}

		default:
			return super.hurt(instigator, type, amount, location);
		}
	}

	@Override
	public void interact(Actor other){
		super.interact(other);
		if(other.isSolid()){
			Vector delta = other.getBox().getCollision(getBox());
			if(delta!=null){
				colliding=true;
				position=position.add(delta);
				if(delta.getX()!=0.0)
					velocity=new Vector(0.0,velocity.getY());
				if(delta.getY()!=0.0)
					velocity=new Vector(velocity.getX(),0.0);
			}
		}
	}
	/**
	 *unregisters the player
	 */
	public void die(){
		getWorld().register(new End());


	}
	@Override
	public void preUpdate(Input input){
		colliding=false;


	}
	@Override
	public void postUpdate(Input input){
		super.postUpdate(input);
		getWorld().setView(getPosition(),8.5);
		if(health==0){
			die();
		}




	}
	@Override
	public void update(Input input){		
		super.update(input);
		getWorld().register(overlay);
		double delta=input.getDeltaTime();
		Vector acceleration= getWorld().getGravity();
		double maxSpeed=4.0;
		if(colliding){
			
			double scale = Math.pow(0.001, input.getDeltaTime());
			velocity=velocity.mul(scale);
		}
		if(input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown()){
			if(velocity.getX()<maxSpeed){
				double increase = 60.0 * input.getDeltaTime();
				double speed = velocity.getX()+increase;
				if(speed>maxSpeed){
					speed=maxSpeed;
				}
				velocity=new Vector(speed, velocity.getY());
			}

		}
		if(input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()){
			if(velocity.getX()>-maxSpeed){
				double increase = 60.0 * input.getDeltaTime();
				double speed = velocity.getX()-increase;
				if(speed<-maxSpeed){
					speed=-maxSpeed;
				}
				velocity=new Vector(speed, velocity.getY());
			}

		}
		if((input.getKeyboardButton(KeyEvent.VK_UP).isPressed())&&colliding)
			velocity = new Vector(velocity.getX(),6.0);

		if(input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed())
		{
			Vector fireballVelocity= velocity.add(velocity.resized(2.0));
			Fireball fire=new Fireball(position,fireballVelocity,0.4,super.getWorld().getLoader().getSprite("fireball"),this);
			super.getWorld().register(fire);
		}
		
		//restarts a level when R is pressed
		if(input.getKeyboardButton(KeyEvent.VK_R).isPressed()){
			getWorld().register(new End()); 
		}
		if(input.getKeyboardButton(KeyEvent.VK_B).isPressed()){
			getWorld().hurt(getBox(), this, Damage.AIR, 1.0, getPosition());
		}

		if(input.getKeyboardButton(KeyEvent.VK_E).isPressed()){
			getWorld().hurt(getBox(), this, Damage.ACTIVATION, 1.0, getPosition());
		}
		velocity=velocity.add(acceleration.mul(delta));
		position=position.add(velocity.mul(delta));



	}



	@Override
	public void draw(Input input, Output output){
		if(sprite==null){
			super.draw(input, output);
		}
		else{
			output.drawSprite(sprite, getBox());
		}
	}



}
