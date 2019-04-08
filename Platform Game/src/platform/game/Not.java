package platform.game;
/**
 *A signal inverting another signal
 */
public class Not implements Signal {

	private final Signal signal;
	/**
	 *@param signal signal that is going to be inverted 
	 */
	public Not(Signal signal){
		if(signal == null)
			throw new NullPointerException();
		this.signal=signal;
	}

	@Override
	public boolean isActive(){
		return !signal.isActive();
	}


}
