package streetfighter.decorators;

import streetfighter.condition.PreConditionError;
import streetfighter.data.CommandData;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.HitboxService;

public class CharacterDecorator implements CharacterService {
	private final CharacterService delegate;
	
	public CharacterDecorator(CharacterService delegate) {
		this.delegate = delegate;
	}

	public void init(int l, int s, boolean f, EngineService e) throws PreConditionError {
		delegate.init(l,s,f,e);
		//getEngine().init(h, w, s, p1, p2);
	}

	//Observators: 
	public int getPositionX() {
		return delegate.getPositionX();
	}

	public int getPositionY() {
		return delegate.getPositionY();
	}

	public EngineService getEngine() {
		return delegate.getEngine();
	}

	public HitboxService getcharBox() {
		return delegate.getcharBox();
	}

	public int getLife() {
		return delegate.getLife();
	}

	public int getSpeed() {
		return delegate.getSpeed();
	}

	public boolean getFaceRight() {
		return delegate.getFaceRight();
	}

	public boolean isDead() {
		return delegate.isDead();
	}

	//Operators: 
	public void moveLeft() {
		delegate.moveLeft(); 
	}

	public void moveRight() {
		delegate.moveRight();
	}

	public void switchSide() {
		delegate.switchSide();
	}

	public void step(CommandData c) {
		delegate.step(c);
	}
}
