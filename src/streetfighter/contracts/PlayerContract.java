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
		return super.getCommand();
	}
	
	//Operators: 
	@Override
	public void init(int num) {
		//TODO
		super.init(num);
	}
	
	@Override
	public CommandData getInput(int key, char c) {
		//TODO
		return super.getInput(key, c);
	}
	
	@Override
	public void step(int key, char c) {
		//TODO
		super.step(key, c);
	}
}
