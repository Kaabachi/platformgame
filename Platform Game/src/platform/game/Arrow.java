package platform.game;


import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
/**
 * An Arrow that goes in a straight line, damages a Player
 */
public class Arrow extends Actor{


	private Vector position;
	private final int PRIORITY = 666;
	private Box box;
	private Sprite sprite;
	private Actor owner;
	private double height;
	private double width;
	private double delta;
	
	/**
	 * @param box that the arrow will be drawn within
	 * @param sprite , not null, that represents the arrow
	 * @param owner launching the arrow
	 */
	public Arrow(Box box,Sprite sprite,Actor owner){


		height= box.getHeight();
		width= box.getWidth();
		this.box=box;
		position=owner.getPosition();
		this.sprite=sprite;
		this.owner=owner;


	}
	@Override
	public int getPriority(){
		return PRIORITY ;
	}

	@Override 
	public void update(Input input){
		super.update(input);
		delta += input.getDeltaTime();
		//acceleration et velocity sont horizontaux et ont des valeurs fixes
		//afin de varier les projectiles
		Vector acceleration = new Vector(5,0);
		Vector velocity = new Vector(3,0);


		velocity = velocity.add(acceleration.mul(delta));
		position=position.add(velocity.mul(input.getDeltaTime()));

	}

	@Override
	public Box getBox(){
		return new Box(position,height,width);
	}

	@Override
	public void interact(Actor other){
		super.interact(other);
		if((other!= owner)&&(other.getBox()!=null)){
			if(other.getBox().isColliding(getBox())){
				//inflige un degat de feu puis se desenregistre
				other.hurt(this, Damage.FIRE, 1.0,position);
				super.getWorld().unregister(this);

			}


		}
	}
	@Override
	public void draw(Input input,Output output){
		if (sprite==null){
			super.draw(input, output);
		}
		else{

			output.drawSprite(sprite, getBox());
		}
	}
}


