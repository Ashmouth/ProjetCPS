package streetfighter.decorators;

import streetfighter.data.CommandData;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.PlayerService;

public class EngineDecorator implements EngineService {
	protected final EngineService delegate;
	
	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}

	@Override
	public void init(int w, int h, int s, PlayerService p1, PlayerService p2, CharacterService c1, CharacterService c2) {
		delegate.init(w,h,s,p1,p2,c1,c2);
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
	public CharacterService getCharacter(int i) {
		return delegate.getCharacter(i);
	}

	@Override
	public PlayerService getPlayer(int i) {
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
}
