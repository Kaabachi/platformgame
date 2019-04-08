package platform.game;

import platform.util.*;
/**
 * Simple solid actor that puts tiles together to form a platform .
 */
public class Floor extends Actor {
	private Vector positionMin;
	private Vector positionMax;
	private final double SIZE  ;
	private String name;

	/**
	 * @param positionMin Vector min of the box
	 * @param positionMax Vector max of the box
	 * @param name of the sprite that will later be concatened with either left/right/middle
	 */
	public Floor(Vector positionMin,Vector positionMax,String name) {

		this.positionMin=positionMin;
		this.positionMax=positionMax;
		this.name=name;
		SIZE = positionMax.getY()-positionMin.getY();



	}





	@Override
	public Box getBox(){

		return new Box(positionMin,positionMax);
	}
	@Override
	public boolean isSolid(){
		return true;
	}
	@Override
	public int getPriority(){
		return 0;
	}

	@Override
	public void draw(Input input,Output output){

		for(int i = (int)positionMin.getX();i<(int)positionMax.getX();i++){

			Vector position = new Vector(i+SIZE/2,positionMax.getY()-SIZE/2);
			if(i==(int)positionMin.getX()){
				//dessine un tile avec un affix "left"
				output.drawSprite(getWorld().getLoader().getSprite(name+".left"), new Box(position,SIZE,SIZE));
			} else if(i==(int)positionMax.getX()-1){
				//dessine un tile avec un affixe "right"
				output.drawSprite(getWorld().getLoader().getSprite(name+".right"), new Box(position,SIZE,SIZE));
			}else{
				//dessine un tile avec un affixe"middle"
				output.drawSprite(getWorld().getLoader().getSprite(name+".middle"), new Box(position,SIZE,SIZE));
			}
		}


	}




}
