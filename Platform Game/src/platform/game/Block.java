package platform.game;
import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;

/**
 * Simple solid actor that does nothing.
 */
public class Block extends Actor {
	private Box box;
	private Sprite sprite ;


	/**
	 * @param box that the actor will be drawn within
	 * @param sprite , not null, that represents the block
	 * 
	 */
	public Block(Box box,Sprite sprite) {
		this.sprite = sprite;
		this.box = box;

	}






	public Box getBox(){
		return box;
	}

	public boolean isSolid(){
		return true;
	}

	public int getPriority(){
		return 0;
	}
	public void draw(Input input,Output output){
		if (sprite==null){
			super.draw(input, output);

		}
		else{

			output.drawSprite(sprite, getBox());
		}
	}


}


