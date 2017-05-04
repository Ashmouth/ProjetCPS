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
	
	boolean ijrh, ijlh, ijh, iscrouch;
	
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
				y);
		
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
				y);
		
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

	
	
	@Override
	public void damaged(int degats) {
		life -= degats;		
	}

	@Override
	public void setPosition(int x, int y) {
		hitbox.moveTo(x,y);
	}

	@Override
	public boolean isJumpRightHigh() {
		return ijrh;
	}

	@Override
	public boolean isJumpLeftHigh() {
		return ijlh;
	}

	@Override
	public boolean isJumpHigh() {
		return ijh;
	}

	@Override
	public boolean isCrouch() {
		return iscrouch;
	}

	@Override
	public void crouch() {
		iscrouch = true;
		
		if(hitbox instanceof HitboxRect) {
			HitboxRect hb = (HitboxRect)hitbox;
			
			hb.resize(hb.getWidth(), hb.getHeight() - 20);
		}
	}
	
	@Override
	public void rise() {
		iscrouch = false;
		
		if(hitbox instanceof HitboxRect) {
			HitboxRect hb = (HitboxRect)hitbox;
			
			hb.resize(hb.getWidth(), hb.getHeight() + 20);
		}
	}

	@Override
	public void jump() {
		ijh = true;
		hitbox.moveTo(getPositionX(), getPositionY()+speed);
	}

	@Override
	public void jumpRight() {
		ijrh = true;
		hitbox.moveTo(getPositionX()+speed, getPositionY()+speed);		
	}

	@Override
	public void jumpLeft() {
		ijlh = true;
		hitbox.moveTo(getPositionX()-speed, getPositionY()+speed);			
	}
	
	public void step(CommandData c) {
		// priorité : en l'air on peut rien faire
		// donc si on est en l'air on fini le saut
		if (hitbox.getPositionY() > 0) {
			// si on est assez haut, on commence la chute
			if (getPositionY() > 100) {
				ijh = false;
				ijrh = false;
				ijlh = false;
			}
			
			if (ijh) {
				hitbox.moveTo(hitbox.getPositionX(), hitbox.getPositionY()+5);
			} else if (ijrh){
				hitbox.moveTo(hitbox.getPositionX()+speed, hitbox.getPositionY()+5);
			} else if (ijlh) {
				hitbox.moveTo(hitbox.getPositionX()-speed, hitbox.getPositionY()+5);
			} else { // phase de chute 
				hitbox.moveTo(hitbox.getPositionX(), Math.max(hitbox.getPositionY()-5, 0));
			}
			
			return; // on ne fait rien d'autre que sauter
		}
		
		if (c != CommandData.DOWN && c != CommandData.DOWNLEFT && c != CommandData.DOWNRIGHT) {
			if(iscrouch) {
				rise();
			}
		}
		switch(c) {
		
		case LEFT:
			moveLeft();
			break;
			
		case RIGHT:
			moveRight();
			break;

		case UPRIGHT:
			jumpRight();
			break;
			
		case UPLEFT:
			jumpLeft();
			break;
			
		case UP:
			if(!iscrouch && hitbox.getPositionY() == 0) {
				jump();
			}
			break;
			
		case DOWNRIGHT:	
			if(!iscrouch) crouch();
			moveRight();
			break;
			
		case DOWNLEFT:
			if(!iscrouch) crouch();
			moveLeft();
			break;
			
		case DOWN:
			
			if(!iscrouch) crouch();
			break;
			
		case NEUTRAL:
			if(iscrouch) rise();
			
			break;
			
		default :
			return;
		}
	}
}
