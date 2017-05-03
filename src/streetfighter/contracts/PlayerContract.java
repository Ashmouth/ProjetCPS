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
		//TODO
		delegate.init(num);
	}
}
