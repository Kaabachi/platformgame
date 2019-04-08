package platform.game;
/**
 * A signal that requires that two other signals are different
 */
public class Xor implements Signal {

	private final Signal left;
	private final Signal right;

	public Xor(Signal left,Signal right){
		if((left==null)||(right==null))
			throw new NullPointerException();
		this.left=left;
		this.right=right;
	}

	@Override
	public boolean isActive(){
		return (!left.isActive() && right.isActive())||(left.isActive()&& !right.isActive());
	}

}



