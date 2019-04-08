package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;
/**
 *An enemy that oscillates in a sinusoidal pattern
 */
public class GroundEnemy extends Actor  {
	private final int PRIORITY =100;
	private double health;
	protected Vector position;
	private final double SIZE=1;
	private Vector velocity;
	private double delta;
	private double cooldown;
	private boolean dead;
	private Vector positionInit;
	/**
	 * @param position position not null of the enemy
	 * @param health health of the enemy
	 */
	public	GroundEnemy(Vector position, double health){
		if( position == null ){
			throw new NullPointerException();
		}
		else{
			this.position=position;
			this.health=health;
			positionInit=position;
		}
	}

	@Override
	public Box getBox(){
		return new Box(position,SIZE,SIZE);
	}

	@Override
	public Vector getPosition(){
		return position;
	}



	@Override
	public int getPriority() {
		return PRIORITY ;
	}

	public Vector getPositionInit(){
		return positionInit;
	}
	
	public void setPosition(Vector position){
		if( position != null){
			this.position=position;
		}else {
			throw new NullPointerException();
		}
	}

	public boolean isDead(){
		return dead;
	}

	public double getHealth(){
		return health;
	}

	public Vector getVelocity(){
		return velocity;
	}

	public double getCooldown(){
		return cooldown;
	}

	public void setCooldown(double cooldown){
		this.cooldown=cooldown;
	}

	public void setVelocity(Vector velocity){
		this.velocity=velocity;

	}

	@Override
	public void update(Input input){

		delta = delta+input.getDeltaTime();
		double functionX=Math.sin(delta);
		double functionY=Math.sin(4*delta);
		velocity = new Vector(functionX,functionY); 
		//assigns two sinusoidal functions to the velocity so it can move
		// in a sinusoidal pattern
		position = position.add(velocity.mul(input.getDeltaTime()));

		if(health==0){
			die();
			getWorld().unregister(this);
		}
		cooldown -= input.getDeltaTime();
	}

	@Override
	public boolean hurt(Actor instigator,Damage type,double amount,Vector location){
		switch(type){

		case FIRE:
			health=health-amount;
			return true;

		default :
			return super.hurt(instigator, type, amount, location);
		}
	}

	@Override
	public void interact(Actor other){
		if(((other.getBox()!=null)&&(cooldown<=0))&& getBox().isColliding(other.getBox())){

			if((other.hurt(this, Damage.FIRE, 1, getPosition())))
				cooldown=1;
		}
		super.interact(other);
		if(other.isSolid()){
			//if it collides with a solid mirrors its X composant of velocity
			Vector delta = other.getBox().getCollision(position);
			if(delta!=null){
				position=position.add(delta);
				velocity=(new Vector(velocity.getX(),0).mirrored(delta)).add(new Vector(0,velocity.getY()));
			}
		}
	}

	public void die(){
		dead=true;
	}

	@Override
	public void draw(Input input,Output output){
		if(!isDead()){
			//only draw the sprite when he is alive
			output.drawSprite(getWorld().getLoader().getSprite("spike.rotate"), getBox(), 4*input.getTime());
		}
	}
}




