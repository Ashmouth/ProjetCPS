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
	public void init(int l, int s, boolean f, EngineService e) {
		delegate.init(l,s,f,e);
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

	@Override
	public void damaged(int degats) {
		delegate.damaged(degats);		
	}

	@Override
	public void setPosition(int x, int y) {
		delegate.setPosition(x, y);		
	}
	
	@Override
	public void crouch() {
		delegate.crouch();
	}

	@Override
	public void rise() {
		delegate.rise();
	}

	@Override
	public void jump() {
		delegate.jump();
	}

	@Override
	public void jumpRight() {
		delegate.jumpRight();
	}

	@Override
	public void jumpLeft() {
		delegate.jumpLeft();
	}

	@Override
	public boolean isJumpRightHigh() {
		return delegate.isJumpRightHigh();
	}

	@Override
	public boolean isJumpLeftHigh() {
		return delegate.isJumpLeftHigh();
	}

	@Override
	public boolean isJumpHigh() {
		return delegate.isJumpHigh();
	}

	@Override
	public boolean isCrouch() {
		return delegate.isCrouch();
	}
}
