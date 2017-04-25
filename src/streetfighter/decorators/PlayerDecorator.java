package streetfighter.decorators;

import streetfighter.data.CommandData;
import streetfighter.services.PlayerService;

public class PlayerDecorator implements PlayerService {

	private PlayerService delegate;

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
	public CommandData keyReleased(int key, char c) {
		return delegate.keyReleased(key, c);
	}

	@Override
	public void step() {
		delegate.step();
	}
}
