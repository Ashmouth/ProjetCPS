package streetfighter.contracts;

import streetfighter.data.CommandData;
import streetfighter.decorators.PlayerDecorator;
import streetfighter.services.PlayerService;

public class PlayerContract extends PlayerDecorator implements PlayerService {
	
	public PlayerContract(PlayerService delegate) {
		super(delegate);
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
