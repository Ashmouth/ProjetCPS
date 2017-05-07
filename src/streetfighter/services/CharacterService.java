package streetfighter.services;

import streetfighter.data.CommandData;


public interface CharacterService {

	public void init(int l, int s, boolean f, EngineService e);
	//pre init(l,s,f,e) requires l > 0 ∧ s > 0
	
	//Observators: 
	public int getPositionX();
	public int getPositionY();
	public EngineService getEngine();
	public HitboxService getcharBox();
	public int getLife();
	public int getSpeed();
	public boolean getFaceRight();
	public boolean isDead();
	
	//Operators: 
	public void moveLeft();
	public void moveRight();
	public void switchSide();
	public void step(CommandData c);
	public void damaged(int degats);
	public void setPosition(int x, int y);
	
	//
	public void crouch();
	public void rise();
	public void jump();
	public void jumpRight();
	public void jumpLeft();
	
	public boolean isJumpRightHigh();
	public boolean isJumpLeftHigh();
	public boolean isJumpHigh();
	public boolean isCrouch();
}
