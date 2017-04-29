package streetfighter.implem;

import streetfighter.data.CommandData;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.HitboxService;

public class Character implements CharacterService {
	int life;
	int speed;
	boolean faceRight;
	EngineService engine;
	HitboxService hitbox;
	
	@Override
	public void init(int l, int s, boolean f, EngineService e) {
		life = l;
		speed = s;
		faceRight = f;
		engine = e;
	}

	//Observators: 
	@Override
	public int getPositionX() {
		return hitbox.getPositionX();
	}

	@Override
	public int getPositionY() {
		return hitbox.getPositionY();
	}

	@Override
	public EngineService getEngine() {
		return engine;
	}

	@Override
	public HitboxService getcharBox() {
		return hitbox;
	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public boolean getFaceRight() {
		return faceRight;
	}

	@Override
	public boolean isDead() {
		if(life == 0) {
			return true;
		} else {
			return false;
		}
	}

	//Operators: 
	@Override
	public void moveLeft() {
		getcharBox().setPositionX(getcharBox().getPositionX() - speed);
	}

	@Override
	public void moveRight() {
		getcharBox().setPositionX(getcharBox().getPositionX() + speed);
	}

	@Override
	public void switchSide() {
		if(faceRight == true) {
			faceRight = false;
		} else {
			faceRight = true;
		}
	}

	public void step(CommandData c) {
		//pre step() requires ¬dead
	}
}
