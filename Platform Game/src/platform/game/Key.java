package platform.game;
import platform.util.*;

/**
 * A signal that can be taken by a player
 */
public class Key extends Actor implements Signal {

	private Vector position;
	private boolean taken=false;
	private final double SIZE=1;
	private Sprite sprite;
	private final int PRIORITY=100;


	public Key(Vector position,Sprite sprite){
		this.position=position;
		this.sprite=sprite;
	}

	@Override
	public int getPriority(){
		return PRIORITY;
	}

	@Override 
	public Vector getPosition(){
		return position;
	}

	@Override
	public boolean isActive(){
		return taken;
	}

	@Override
	public Box getBox(){
		return new Box(position,SIZE,SIZE);
	}

	@Override
	public void interact(Actor other){
		if((other.getBox()!=null)){ 
			if(other.getBox().isColliding(getBox())&&(other.getPriority()==PLAYER_PRIORITY)){
				taken=true;
				super.getWorld().unregister(this);
			}
		}
	}

	@Override
	public void draw(Input input,Output output){
		if(sprite == null){
			super.draw(input, output);
		}
		else{
			output.drawSprite(sprite, getBox());	
		}

	}



}
