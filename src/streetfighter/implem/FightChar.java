package streetfighter.implem;

import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;
import streetfighter.data.CommandData;
import streetfighter.data.TechData;

public class FightChar extends Character implements FightCharService {

	//REFINE CHARACHER

	private boolean block;
	private boolean blockstun;
	private boolean hitstun;
	private boolean teching;
	private TechData tech;

	@Override
	public void init(int l, int s, boolean f, EngineService e) {
		super.init(l, s, f, e);
		block = false;
		blockstun = false;
		hitstun = false;
		teching = false;
	}

	//Observators: 
	@Override
	public boolean isBlocking() {
		return block;
	}

	@Override
	public boolean isBlockstunned() {
		return blockstun; 
	}

	@Override
	public boolean isHitstunned(){
		return hitstun; 
	}

	@Override
	public boolean isTeching() {
		return teching; 
	}

	@Override
	public TechData tech() {
		if(!isTeching()) {
			return null;
		}
		return tech;
	}

	@Override
	public boolean techFrame() { 
		if(!isTeching()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean techHasAlreadyHit() { 
		if(!isTeching()) {
			return false;
		}
		if(tech == null) {
			return true;
		} else {
			this.tech = null;
			teching = false;
		}
		return false;
	}

	@Override
	public void startTech(TechData tech) {
		if(isTeching()) {
			return;
		}
		this.tech = tech;
		teching = true;
	}
	
	protected boolean canAttack() {
		return !block && !blockstun && !hitstun && !teching && getPositionY()==0;
	}

	@Override
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
		
		if(c != CommandData.GUARD) {
			block = false;
		}
		
		switch(c) {

		case LEFT:
			if(canAttack())	
				moveLeft();
			break;

		case RIGHT:
			if(canAttack())
				moveRight();
			break;

		case UPRIGHT:
			if(canAttack())
				jumpRight();
			break;

		case UPLEFT:
			if(canAttack())
				jumpLeft();
			break;

		case UP:
			if(!iscrouch && hitbox.getPositionY() == 0 && canAttack()) {
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
			
		case PUNCH:
			if (canAttack()) {
				TechData tec = TechData.punch();
				startTech(tec);
			}
			break;
			
		case GUARD:
			block = true;
			break;
			
		case DOWNGUARD:
			if(!iscrouch) crouch();
			block = true;
			break;

		case NEUTRAL:
			if(iscrouch) rise();
			block = false;

			break;

		default :
			return;
		}
	}
}
