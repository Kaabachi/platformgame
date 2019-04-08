package platform.game;
import platform.util.*;
/**
 *An actor that simulates a particle 
 */
public class Particle extends Actor{
	private double size;
	private double time;
	private double duration;
	private int priority=1337;
	private Sprite sprite;
	private Vector position;
	/**
	 @param sprite sprite representing the particle
	 */
	public Particle(Sprite sprite){
		this.sprite=sprite;
	}

	public void setPosition(Vector position){
		if(position == null ){
			throw new NullPointerException();
		}
		else{
			this.position=position;
		}
	}



	public void setSize(double size){
		this.size=size;
	}

	public void setDuration(double duration){
		this.duration=duration;
	}


	public void setPriority(int priority){
		this.priority=priority;

	}
	@Override
	public int getPriority(){
		return priority;
	}

	@Override
	public Box getBox(){
		return new Box(position,size,size);

	}



	@Override
	public void update(Input input){
		super.update(input);
		time+= input.getDeltaTime();
		if(time>=duration)
			//desenregistre particle apres que time depasse duration
			getWorld().unregister(this);
	}

	@Override
	public void draw(Input input,Output output){
		if(sprite==null){
			super.draw(input, output);
		}
		else{
			// on a donne a drawsprite input.getTime pour que le sprite du particule tourne sur lui meme
			output.drawSprite(sprite, getBox(),input.getTime());
		}

	}





}
