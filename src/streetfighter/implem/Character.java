package streetfighter.implem;

import streetfighter.data.CommandData;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;

public class Character implements CharacterService {
	protected int life;
	protected int speed;
	protected boolean faceRight;
	protected EngineService engine;
	protected HitboxService hitbox;
	
	protected final int JUMP_UP_SPEED = 10,
						JUMP_DOWN_SPEED = 10,
						CROUCH_VAL = 50,
						MAX_Y = 100;
	
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
	
	protected void moveUp() {
		int x, y;
		x = hitbox.getPositionX();
		y = hitbox.getPositionY();
		
		hitbox.moveTo(
				x,
				y+JUMP_UP_SPEED);
		
		if (engine.getCharacter(1).getcharBox().collidesWith(engine.getCharacter(2).getcharBox())) {
			hitbox.moveTo(x, y);
			return;
		}
	}
	
	protected void moveDown() {
		int x, y;
		x = hitbox.getPositionX();
		y = hitbox.getPositionY();
		
		hitbox.moveTo(
				x,
				Math.max(0, y-JUMP_DOWN_SPEED));
		
		if (engine.getCharacter(1).getcharBox().collidesWith(engine.getCharacter(2).getcharBox())) {
			hitbox.moveTo(x, y);
			return;
		}
	}

	@Override
	public void switchSide() {
		faceRight = !faceRight;
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
			
			hb.resize(hb.getWidth(), hb.getHeight() - CROUCH_VAL);
		}
	}
	
	@Override
	public void rise() {
		iscrouch = false;
		
		if(hitbox instanceof HitboxRect) {
			HitboxRect hb = (HitboxRect)hitbox;
			
			hb.resize(hb.getWidth(), hb.getHeight() + CROUCH_VAL);
		}
	}

	@Override
	public void jump() {
		ijh = true;
		moveUp();
	}

	@Override
	public void jumpRight() {
		ijrh = true;
		moveRight();
		moveUp();
	}

	@Override
	public void jumpLeft() {
		ijlh = true;
		moveLeft();
		moveUp();
	}
	
	public void step(CommandData c) {
		// priorité : en l'air on peut rien faire
		// donc si on est en l'air on fini le saut
		if (hitbox.getPositionY() > 0) {
			// si on est assez haut, on commence la chute
			if (getPositionY() > MAX_Y) {
				ijh = false;
				ijrh = false;
				ijlh = false;
			}
			
			if (ijh) {
				moveUp();
			} else if (ijrh){
				moveRight();
				moveUp();
			} else if (ijlh) {
				moveLeft();
				moveUp();
			} else { // phase de chute 
				moveDown();
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
