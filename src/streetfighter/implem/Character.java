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
		return life <= 0;
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
				Math.min(y+JUMP_UP_SPEED, engine.getHeight()));
		
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
		if(iscrouch) return;
		
		iscrouch = true;
		
		if(hitbox instanceof HitboxRect) {
			HitboxRect hb = (HitboxRect)hitbox;
			
			hb.resize(hb.getWidth(), hb.getHeight() - CROUCH_VAL);
		}
	}
	
	@Override
	public void rise() {
		if (iscrouch) {
			if(hitbox instanceof HitboxRect) {
				HitboxRect hb = (HitboxRect)hitbox;
				
				int h = hb.getHeight();
				hb.resize(hb.getWidth(), hb.getHeight() + CROUCH_VAL);
				iscrouch = false;
				
				if (engine.getCharacter(1).getcharBox().collidesWith(engine.getCharacter(2).getcharBox())) {
					hb.resize(hb.getWidth(), h);
					iscrouch = true;
					return;
				}
			}
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
			rise();
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
			jump();
			break;
			
		case DOWNRIGHT:	
			crouch();
			moveRight();
			break;
			
		case DOWNLEFT:
			crouch();
			moveLeft();
			break;
			
		case DOWN:
			crouch();
			break;
			
		case NEUTRAL:
			rise();
			
			break;
			
		default :
			return;
		}
	}
}
