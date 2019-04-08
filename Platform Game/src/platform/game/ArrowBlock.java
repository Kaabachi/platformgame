package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
/**
 * Solid block that launches an arrow when a Player goes in his range
 */
public class ArrowBlock extends Block {
	final int PRIORITY = 666;


	private double cooldown;
	/**
	 * @param box that the block will be drawn within
	 * @param sprite , not null , that represents the arrowblock
	 */
	public ArrowBlock(Box box,Sprite sprite) {
		super(box,sprite);
	}


	@Override
	public void interact(Actor other){
		if((other.getBox()!=null)&&(cooldown<=0)){
			//on verifie que le Player est dans le champ de tir de arrowBlock ( cad devant lui et dans un intervalle de 2 sur les Y )
			boolean condition = (other.getPosition().getY()<=getPosition().getY()+1)&&(other.getPosition().getY()>=getPosition().getY()-1);
			if ((other instanceof Player)&&(condition)&&(other.getPosition().getX()>getPosition().getX())){
				Arrow arrow=new Arrow(new Box(getPosition(),0.25,2),super.getWorld().getLoader().getSprite("arrow"),this);
				super.getWorld().register(arrow);
				cooldown=3;
			}
		}
	}

	@Override
	public int getPriority(){
		return PRIORITY;

	}

	@Override
	public void update(Input input){
		cooldown -= input.getDeltaTime();
	}
}


