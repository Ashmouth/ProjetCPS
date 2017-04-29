package streetfighter.decorators;

import streetfighter.condition.PreConditionError;
import streetfighter.data.CommandData;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.PlayerService;

public class EngineDecorator implements EngineService {
	private final EngineService delegate;
	
	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}

	@Override
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) throws PreConditionError {
		
		delegate.init(h,w,s,p1,p2);
	}

	//Observators: 
	@Override
	public int getHeight() {
		return delegate.getHeight(); 
	}

	@Override
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

	@Override
	public boolean gameOver() {
		return delegate.gameOver(); 
	}

	//Operators: 
	@Override
	public void step(CommandData com1, CommandData com2) {
		delegate.step(com1, com2);
	}

	//Observations: 
	@Override
	public void checkInvariant() {
		delegate.checkInvariant();
	}
}
