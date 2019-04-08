package platform.game;
import platform.util.*;
/**
 *A Mover that travels between two positions according to a Signal
 */
public class Mover extends Block implements Signal {

	private Vector on;
	private Vector off;
	private Signal signal;
	double current=0;
	/**
	 *@param box box that the mover will be drawn in
	 *@param sprite sprite representing the mover
	 *@param off vector that the mover will slowly move to after the signal is active
	 *@param signal signal controlling the mover
	 */
	public Mover(Box box,Sprite sprite,Vector off,Signal signal){
		super(box,sprite);
		this.on=box.getCenter();
		this.off=off;
		this.signal=signal;

	}

	@Override
	public boolean isActive(){
		return signal.isActive();
	}

	@Override
	public void update(Input input){
		super.update(input);
		if(signal.isActive()){
			//on a opte pour 4 * delta time pour que le mover aille plus vite
			current += -2*Math.pow(4*input.getDeltaTime(),3)+3*Math.pow(4*input.getDeltaTime(),2);  
			if(current>1.0){
				current =1.0;
			}
		}else{
			current -= -2*Math.pow(4*input.getDeltaTime(),3)+3*Math.pow(4*input.getDeltaTime(),2);  
			if (current <0.0)
				current=0.0;

		}
	}

	@Override
	public Box getBox(){
		double width = super.getBox().getWidth();
		double height = super.getBox().getHeight();

		//la box ou le mover est dessine change en fonction de current de onjusqu'a atteindre off
		return new Box (on.mul(1-current).add(off.mul(current)),width,height);
	}



}



