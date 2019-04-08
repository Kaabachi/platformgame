package platform.game;

import platform.util.*;

/**
 *An overlay on top of the player showing hearts according to its current health
 */
public class Overlay extends Actor {
	private Player player;
	private final double SIZE = 0.3; //was 0.3
	private final int PRIORITY=200;
	/**
	 *@param player the player that the Overlay shows the life of
	 */
	public Overlay(Player player){
		this.player=player;
	}
	@Override
	public int getPriority(){
		return PRIORITY;
	}
	@Override
	public void draw(Input input,Output output){

		double health=5.0*player.getHealth() / player.getHealthMax();
		for(int i=1;i<=player.getHealthMax();++i){
			//Draws hearts depending on player current health
			String name;
			if(health>=i)
				name ="heart.full";
			else if(health>=i-0.5)
				name="heart.half";
			else
				name="heart.empty";

			Box box = new Box (new Vector((player.getPosition().getX()-0.75+0.25*i),player.getPosition().getY()+10),SIZE,SIZE);
			output.drawSprite(getWorld().getLoader().getSprite(name),box);	//was gety + 0.5



		}
	}

}
