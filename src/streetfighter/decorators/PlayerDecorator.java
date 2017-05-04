package streetfighter.decorators;

import streetfighter.data.CommandData;
import streetfighter.services.PlayerService;

public class PlayerDecorator implements PlayerService {

	protected PlayerService delegate;

	public PlayerDecorator(PlayerService delegate) {
		this.delegate = delegate;
	}
	
	//Observators: 
	@Override
	public CommandData getCommand() {
		return delegate.getCommand();
	}
	
	//Operators: 
	@Override
	public void init(int num) {
		delegate.init(num);
	}

	@Override
	public void addInput(int key) {
		delegate.addInput(key);
	}

	@Override
	public void clearInput(int key) {
		delegate.clearInput(key);
	}
}
