package platform.game;

/**
 * returns a constant signal
 */
public class Constant implements Signal {
	private boolean constant;


	public Constant(boolean constant){
		this.constant=constant;
		
	}


	@Override
	public boolean isActive(){
		return constant;
	}

}
