package platform.game;

import platform.util.*;
/**
 *Projectile launched by a FlyingEnemy that inflicts 1 FIRE damage
 */
public class FlyingProjectile extends Actor  {
	private final int PRIORITY=666;
	private Sprite sprite;
	private Vector position;
	private Vector velocity;
	private final double SIZE;
	private Actor owner;
	private double cooldown;

	/**
	 * @param position position , non null, of the flying projectile
	 * @param velocity velocity , non null , of the projectile
	 * @param size size of projectile
	 * @param sprite sprite representing the projectile
	 * @param owner of the projectile
	 */
	public FlyingProjectile(Vector position,Vector velocity,double size,Sprite sprite,Actor owner){
		if((position==null) || (velocity==null)){
			throw new NullPointerException();
		}else{
			this.position=position;
			this.SIZE=size;
			this.velocity=velocity;
			this.sprite=sprite;
			this.owner=owner;
		}
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
	public Vector getPosition(){
		return position;
	}

	@Override 
	public void update(Input input){
		super.update(input);
		double delta = input.getDeltaTime();
		Vector acceleration = getWorld().getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position=position.add(velocity.mul(delta));
		cooldown -= delta;



	}

	@Override 
	public void interact(Actor other){
		if((other.getBox()!=null)&&(other!=owner)){

			if((other.getBox().isColliding(getBox()))&&(cooldown<=0)){
				if(other.hurt(this, Damage.FIRE, 1.0, getPosition())){
					
					//lorsqu'elle frappe un joueur elle fait apparaitre des particules
					Particle smoke = new Particle(getWorld().getLoader().getSprite("smoke.white.1"));
					smoke.setDuration(0.2);
					smoke.setPosition(getPosition());
					smoke.setSize(0.8);
					getWorld().register(smoke);
					
					

				}
				//Elle a un cooldown pour qu'il evite d'essayer de se desenregistrer
				//apres avoir touche 2 objets solides en meme temps
				cooldown=2;
				getWorld().unregister(this);
			}




		}

	}

	@Override
	public void draw(Input input , Output output){
		if (sprite==null){
			super.draw(input, output);

		}
		else{

			output.drawSprite(sprite, getBox());
		}
	}

}
