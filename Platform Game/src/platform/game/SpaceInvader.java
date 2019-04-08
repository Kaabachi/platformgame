package platform.game;
import platform.util.*;
/**
 *An enemy actor that launches flyingProjectiles
 */
public class SpaceInvader extends FlyingEnemy {
	/**
	 *@param position the position of the spaceInvader
	 *@param health of the spaceInvader
	 *@params size size of the spaceInvader
	 */
	public SpaceInvader(Vector position,double health,double size){
		super(position,health,size);
	}

	@Override
	public void draw(Input input,Output output){
		if(getHealth()>0){
			output.drawSprite(getWorld().getLoader().getSprite("enemyBlue3"), getBox());
		}
	}

}
