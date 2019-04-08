package platform.game;
import platform.game.level.*; 
import platform.util.*;

/**
 * A door that leads to a new level
 */
public class Exit extends Actor implements Signal{
	private Vector position;
	private Signal signal;
	private Level level;
	private final int PRIORITY=100;

	/**
	 * @param position a vector indicating its position, not null
	 * @param signal a signal indicating if it is open or not
	 * @param level the level that it will lead to
	 */
	public Exit(Vector position,Signal signal,Level level){
		if(position == null){
			throw new NullPointerException();
		}
		else{
			this.position=position;
			this.signal=signal;
			this.level=level;
		}



	}

	@Override
	public int getPriority(){
		return PRIORITY;
	}

	@Override
	public boolean isActive(){
		return signal.isActive();
	}

	@Override
	public Box getBox(){
		return new Box(position,1,1);
	}

	@Override
	public void interact(Actor other){
		if(signal.isActive()){
			//on teste si la priorite de other est celle de player pour s'assurer
			//que nous avons affaire a un player sans recourir a un test de type
			if((other.getPriority()==PLAYER_PRIORITY)&&(other.getBox().isColliding(getBox()))){
				// Si le signal est actif et que la porte entre en contact avec un player
				// elle choisit le prochain niveau et registre dans le monde un End qui va assurer
				// la transition fluide
				getWorld().setNextLevel(level);
				getWorld().register(new End());
			}
		}

	}

	@Override
	public void draw(Input input,Output output){
		if(signal.isActive())
			output.drawSprite(getWorld().getLoader().getSprite("door.open"),getBox() );
		else{
			output.drawSprite(getWorld().getLoader().getSprite("door.closed"), getBox());
		}

	}

}
