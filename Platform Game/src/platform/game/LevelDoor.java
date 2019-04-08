package platform.game;

import platform.game.level.*;
import platform.util.*;
/**
 *An actor that lets you choose a level when you click on it
 */
public class LevelDoor extends Actor{
	private Vector position;
	private Sprite sprite;
	private final int PRIORITY=600;
	private Level level;
	private final double SIZE=1;
	/**
	 *@param position position , not null , of levelDoor
	 *@param level level that leveldoor will transition to
	 *@param sprite sprite that represents the leveldoor
	 */
	public LevelDoor(Vector position,Level level,Sprite sprite) {
		if(position== null){
			throw new NullPointerException();
		}
		else{
		this.position=position;
		this.sprite=sprite;
		this.level=level;
		}
	}

	@Override
	public Box getBox(){
		return new Box(position,SIZE,SIZE);
	}


	@Override
	public void update(Input input){
		Vector mouse=input.getMouseLocation();
		double x = mouse.getX();
		double y = mouse.getY();
		//condition 1 et condition 2 constituent des intervalles ou la souris est proche du LevelDoor
		boolean condition1 = x<position.getX()+0.5 && x>position.getX()-0.5;
		boolean condition2 = y<position.getY()+0.5 && y>position.getY()-0.5;
		boolean condition3= input.getMouseButton(1).isPressed();
		if ((condition1&&condition2)&&condition3){
			getWorld().setNextLevel(level);
			getWorld().register(new End());
			//transitionne vers le niveau choisi
		}

	}

	@Override
	public int getPriority() {

		return PRIORITY;
	}

	@Override
	public void draw(Input input,Output output){

		output.drawSprite(sprite,getBox());

	}


}
