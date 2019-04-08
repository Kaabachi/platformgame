package platform.game;
import platform.util.*;

/**
 * Actor that draws a non solid background
 */
public class Background extends Actor {
	private final int PRIORITY;
	private Sprite sprite;
	private Box box;
	/**
	 * @param box that the actor will be drawn within
	 * @param sprite , not null, that represents the background
	 * @param PRIORITY with which the background will interact with other actors(drawn under or over him)
	 * 
	 */
	public Background(Box box,Sprite sprite,int PRIORITY ){
		this.sprite=sprite;
		this.PRIORITY=PRIORITY;
		this.box=box;

	}


	@Override
	public int getPriority(){
		return PRIORITY;
	}

	@Override
	public void draw(Input input,Output output)
	{
		output.drawSprite(sprite , box);

	}


}
