package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
/**
A spinning Fireball launched in a direction that deals 1 fire damage
 */
public class Fireball extends Actor {

	private Vector position;
	private Vector velocity;
	private final double SIZE;
	private Sprite sprite;
	private Actor owner;
	private final int PRIORITY = 666;
	private double cooldown;

	/**
	 * @param position not null position of fireball
	 * @param velocity not null velocity of fireball
	 * @param size size of fireball
	 * @param sprite sprite that represents the fireball
	 */
	public Fireball(Vector position,Vector velocity,double size,Sprite sprite,Actor owner){
		if((position==null) || (velocity==null)){
			throw new NullPointerException();
		}
		else {	
			this.position=position;
			this.SIZE=size;
			this.velocity=velocity;
			this.sprite=sprite;
			this.owner=owner;
		}

	}
	@Override 
	public void update(Input input){
		super.update(input);
		double delta = input.getDeltaTime();
		cooldown -= delta;
		Vector acceleration = getWorld().getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position=position.add(velocity.mul(delta));

	}

	@Override
	public int getPriority(){
		return PRIORITY ;
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
	public void interact(Actor other){
		super.interact(other);
		if((other.getBox()!=null)&&(other!=owner)){

			if((other.getBox().isColliding(getBox()))){
				if((other.hurt(this, Damage.FIRE, 1.0, getPosition()))&&(cooldown<=0)){
					//Si elle inflige un degat a un ennemie elle cree 3 particule smoke
					Particle smokeyellow = new Particle(getWorld().getLoader().getSprite("smoke.yellow.1"));
					smokeyellow.setDuration(0.2);
					smokeyellow.setPosition(getPosition());
					smokeyellow.setSize(0.8);
					Particle smokewhite = new Particle(getWorld().getLoader().getSprite("smoke.white.1"));
					smokewhite.setDuration(0.2);
					smokewhite.setPosition(getPosition());
					smokewhite.setSize(0.4);
					smokewhite.setPriority(1400);
					Particle smokeorange = new Particle(getWorld().getLoader().getSprite("smoke.orange.3"));
					smokeorange.setDuration(0.2);
					smokeorange.setPosition(getPosition());
					smokeorange.setSize(1);
					smokeorange.setPriority(1200);
					getWorld().register(smokeyellow);
					getWorld().register(smokewhite);
					getWorld().register(smokeorange);
					super.getWorld().unregister(this);
					cooldown=7;
					//la fireball a un cooldown qui regit sa creation de particule 
					//afin d'eviter que le monde s'encombre de particule si on lance beaucoup de fireball
				}
			}

			if(other.isSolid()){
				Vector delta = other.getBox().getCollision(position);
				if(delta!=null){
					//lors de collision renvoie dans la direction inverse
					position=position.add(delta);
					velocity=velocity.mirrored(delta);
				}
			}
		}
	}
	@Override
	public void draw(Input input,Output output){
		if(sprite==null){
			super.draw(input, output);
		}
		else{
			//on ajout input.gettime pour la rotation de la fireball
			output.drawSprite(sprite, getBox(),input.getTime());


		}
	}
}
