package streetfighter.implem;

import streetfighter.data.CommandData;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.HitboxRectService;
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
		
		HitboxRectService hr = new HitboxRect();
		hr.init(0,0,90,150);
		
		hitbox = hr;
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
		int x, y;
		x = hitbox.getPositionX();
		y = hitbox.getPositionY();
		
		hitbox.moveTo(
				Math.max(getcharBox().getPositionX() - speed, 0),
				0);
		
		if (engine.getCharacter(1).getcharBox().collidesWith(engine.getCharacter(2).getcharBox())) {
			hitbox.moveTo(x, y);
			return;
		}
	}

	@Override
	public void moveRight() {
		
		int x, y;
		x = hitbox.getPositionX();
		y = hitbox.getPositionY();
		
		hitbox.moveTo(
				Math.min(getcharBox().getPositionX() + speed, engine.getWidth()-1),
				0);
		
		if (engine.getCharacter(1).getcharBox().collidesWith(engine.getCharacter(2).getcharBox())) {
			hitbox.moveTo(x, y);
			return;
		}
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
		if (c == CommandData.LEFT) {
			
			moveLeft();
		}
		if (c == CommandData.RIGHT) {
			moveRight();
		}
	}
	
	@Override
	public void damaged(int degats) {
		life -= degats;		
	}

	@Override
	public void setPosition(int x, int y) {
		hitbox.moveTo(x,y);
	}
}
