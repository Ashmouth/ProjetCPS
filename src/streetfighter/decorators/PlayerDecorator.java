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
	public void keyPressed(int key, char c) {
		delegate.keyPressed(key, c);
	}

	@Override
	public void keyReleased(int key, char c) {
		delegate.keyReleased(key, c);
	}
}
