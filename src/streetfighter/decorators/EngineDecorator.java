package streetfighter.decorators;

import streetfighter.condition.PreConditionError;
import streetfighter.services.CharacterService;
import streetfighter.services.CommandService;
import streetfighter.services.EngineService;
import streetfighter.services.PlayerService;

public class EngineDecorator implements EngineService {
	private final EngineService delegate;
	
	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}

	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) throws PreConditionError {
		
		delegate.init(h,w,s,p1,p2);
	}

	//Observators: 
	public int getHeight() {
		return delegate.getHeight(); 
	}

	public int getWidth() {
		return delegate.getWidth(); 
	}

	@Override
	public CharacterService getCharacter(int i) throws PreConditionError {
		return delegate.getCharacter(i);
	}

	@Override
	public PlayerService getPlayer(int i) throws PreConditionError {
		return delegate.getPlayer(i);
	}

	public boolean gameOver() {
		return delegate.gameOver(); 
	}

	//Operators: 
	public void step(CommandService com1, CommandService com2) {
		delegate.step(com1, com2);
	}

	//Observations: 
	public void checkInvariant() {
		delegate.checkInvariant();
	}

	public void checkInit() {
		delegate.checkInit();
	}

	public void checkStep() {
		delegate.checkStep();
	}
}
