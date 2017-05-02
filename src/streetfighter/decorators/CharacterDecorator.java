package streetfighter.decorators;

import streetfighter.data.CommandData;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.HitboxService;

public class CharacterDecorator implements CharacterService {
	protected final CharacterService delegate;
	
	public CharacterDecorator(CharacterService delegate) {
		this.delegate = delegate;
	}

	@Override
	public void init(int l, int s, boolean f, EngineService e, HitboxService b) {
		delegate.init(l,s,f,e, b);
	}

	//Observators: 
	@Override
	public int getPositionX() {
		return delegate.getPositionX();
	}

	@Override
	public int getPositionY() {
		return delegate.getPositionY();
	}

	@Override
	public EngineService getEngine() {
		return delegate.getEngine();
	}

	@Override
	public HitboxService getcharBox() {
		return delegate.getcharBox();
	}

	@Override
	public int getLife() {
		return delegate.getLife();
	}

	@Override
	public int getSpeed() {
		return delegate.getSpeed();
	}

	@Override
	public boolean getFaceRight() {
		return delegate.getFaceRight();
	}

	@Override
	public boolean isDead() {
		return delegate.isDead();
	}

	//Operators: 
	@Override
	public void moveLeft() {
		delegate.moveLeft(); 
	}

	@Override
	public void moveRight() {
		delegate.moveRight();
	}

	@Override
	public void switchSide() {
		delegate.switchSide();
	}

	@Override
	public void step(CommandData c) {
		delegate.step(c);
	}
}
