package platform.game;


import platform.util.*;
/**
 * an enemy that jumps forward and backwards 
 */
public class Frog extends GroundEnemy {

	/**
	 * @param position position not null of the frog
	 * @param health health of the frog
	 */
	public	Frog(Vector position, double health){
		super(position,health);
	}
	
	@Override
	public Box getBox(){
		return new Box(this.position,5,5);
	}



	@Override
	public void draw(Input input,Output output){
		if(!isDead()){
			if(getVelocity().getX()<=0){
				if((getPosition().getY()>getPositionInit().getY()-0.1)&&(getPosition().getY()<getPositionInit().getY()+0.1)){
					//La grenouille est dans un intervalle ou elle est en train de se poser vers la droite
					output.drawSprite(getWorld().getLoader().getSprite("frog.left"), getBox());
				}else {
					//la grenouille est en train de sauter vers la gauche
					output.drawSprite(getWorld().getLoader().getSprite("frog.left.leap"), getBox());

				}
			}
			else if(getVelocity().getX()>0){
				if((getPosition().getY()>getPositionInit().getY()-0.1)&&(getPosition().getY()<getPositionInit().getY()+0.1)){
					//la grenouille est dans un intervalle ou elle est en train de se poser vers la droite
					output.drawSprite(getWorld().getLoader().getSprite("frog.right"), getBox());
				}else {
					//la grenouille est en train de sauter vers la droite
					output.drawSprite(getWorld().getLoader().getSprite("frog.right.leap"), getBox());

				}
			}
		}
		
		
	}
}

