package platform.game;


import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

/**
 An enemy that launches a FlyingProjectiles if a Player enters its field of view
 */
public class FlyingEnemy extends Actor {
	private final int PRIORITY =100;
	private double health;
	private Vector position;
	private final double SIZE;
	private Vector velocity;
	private double delta=0.0;
	private double cooldown;
	private boolean dead;

	/**
	 * @param position position,not null, of the flying enemy
	 * @param health health of the flying enemy
	 * @param SIZE size of the flying enemy
	 */
	public	FlyingEnemy(Vector position, double health , double SIZE){
		if(position == null ){
			throw new NullPointerException();
		}
		this.position=position;
		this.health=health;
		this.SIZE=SIZE;
	}

	@Override
	public Box getBox(){
		return new Box(position,SIZE,SIZE);
	}

	@Override
	public int getPriority() {

		return PRIORITY ;
	}
	public void die(){
		dead=true;
	}

	public double getHealth(){
		return health;
	}


	@Override
	public void update(Input input){

		delta = delta+input.getDeltaTime();
		//on a choisi la fonction sin(delta) pour simuler un va et vient
		double functionX=Math.sin(delta);
		velocity = new Vector(functionX,0); 
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
		if((other.getBox()!=null)&&(cooldown<=0)){
			boolean condition = (other.getPosition().getX()<=getPosition().getX()+1)&&(other.getPosition().getX()>=getPosition().getX()-1);
			if ((other.getPriority()==PLAYER_PRIORITY)&&(condition)){
				//si un Player est dans son champs de vision elle lance un projectile
				//et reinitialise son cooldown a 1
				FlyingProjectile projectile = new FlyingProjectile(position,new Vector(0,-2),0.4,getWorld().getLoader().getSprite("redprojectile"),this);
				getWorld().register(projectile);
				cooldown=1;
			}
		}
	}

	@Override
	public void draw(Input input,Output output){
		if(!dead){
			if(velocity.getX()<=0)
				//dessine l'acteur allant vers la gauche
				output.drawSprite(getWorld().getLoader().getSprite("fly.left.1"), getBox());
			else if(velocity.getX()>0)
				//dessine l'acteur allant vers la droite
				output.drawSprite(getWorld().getLoader().getSprite("fly.right.1"), getBox());
		}
	}
}
