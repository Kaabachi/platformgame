package platform.game;
import platform.util.*;
/**
A block that disappears depending on a Signal
*/
public class Door extends Block implements Signal{
	private Signal signal;
	/**
	 * @param box that the door will be drawn within
	 * @param sprite , not null, that represents the door
	 * @param signal that decides if the door is there or not
	 */
	public Door(Box box,Sprite sprite,Signal signal){
		super(box,sprite);
		this.signal=signal;

	}

	@Override
	public boolean isActive(){
		return !signal.isActive();
	}

	@Override
	public boolean isSolid(){
		return !signal.isActive();
	}
	@Override
	public Box getBox(){

		if(!signal.isActive()){
			return super.getBox();
		}
		else return null;

	}

	@Override
	public void draw(Input input,Output output){
		if(!signal.isActive()){
			super.draw(input, output);
		}
	}




}
