package platform.game;


import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;

/**
 *A block that spawns a heart when after it receives 2 damage
 */
public class HeartBlock extends Destructible {
	
	/**
	 * @param box that the block will be drawn within
	 * @param spriteUndamaged  the sprite that represents the box when it is not damaged
	 * @param spriteDamaged the sprite that represents the box after it took one damage
	 */
	public HeartBlock(Box box, Sprite spriteUndamaged, Sprite spriteDamaged){
		super(box,spriteUndamaged,spriteDamaged);

	}



	@Override
	public void update(Input input){
		if (getHealth()==0){
			
			//spawn a heart then disappear
			Heart heart = new Heart(getBox().getCenter());
			getWorld().register(heart);
			getWorld().unregister(this);
		}
	}
}
