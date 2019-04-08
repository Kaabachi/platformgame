package platform.game;
import platform.util.*;
/**
an Actor that smoothes transitions between levels
 */
public class End extends Actor {
	private final int PRIORITY=700;
	private final double TIME = 1;
	private double duration;

	@Override
	public int getPriority(){
		return PRIORITY;
	}

	@Override
	public void update(Input input){
		duration = duration+ input.getDeltaTime();
		if(TIME<duration){
			//passe a un autre niveau des que duration depasse TIME=1
			getWorld().nextLevel();
		}

	}

	@Override
	public void draw(Input input,Output output){
		

		Sprite sprite = getSprite("pixel.black");
		//la transparence augmente en fonction de la difference entre TIME et duration
		double transparency=Math.max(0.0, 1-(TIME - duration));
		output.drawSprite(sprite, output.getBox(),0.0,transparency);


	}

}
