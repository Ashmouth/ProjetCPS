package streetfighter.implembug;

import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;
import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;
import streetfighter.data.CommandData;
import streetfighter.data.TechData;

public class FightCharBug extends CharacterBug implements FightCharService {

	//REFINE CHARACHER

	private boolean block;
	private boolean blockstun;
	private boolean hitstun;
	private boolean teching;
	private TechData tech;
	private int techFrame;
	private int timestun;
	private boolean alreadyHit;

	@Override
	public void init(int l, int s, boolean f, EngineService e) {
		super.init(l, s, f, e);
		block = false;
		blockstun = false;
		hitstun = false;
		teching = false;
		techFrame = 0;
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
	public int techFrame() { 
		if(!isTeching()) {
			return -1;
		}
		return techFrame;
	}

	@Override
	public boolean techHasAlreadyHit() { 
		if(!isTeching()) {
			return false;
		}
		return alreadyHit;
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
		return !iscrouch && !block && !blockstun && !hitstun && !teching && getPositionY()==0;
	}

	protected CharacterService other() {
		CharacterService c1,c2;
		c1 = engine.getCharacter(1);
		c2 = engine.getCharacter(2);

		if(c1.getPositionX() == getPositionX()) {
			return c2;
		}
		return c1;
	}

	@Override
	public void step(CommandData c) {
		// en l'air on peut rien faire
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

		// si on est stun, on fait rien
		if (timestun > 0) {
			timestun--;
			return;
		} else {
			hitstun = false;
			blockstun = false;
		}

		// si une attaque est en cours, on peut rien faire
		// on calcule donc l'attaque
		if (teching) {
			// on a atteint la fin
			if (techFrame >= tech.hframe + tech.rframe + tech.sframe) {
				techFrame = -1;
				tech = null;
				teching = false;
				alreadyHit = false;
			} 

			// phase de recup 
			else if (techFrame >= tech.hframe + tech.sframe) {

			}

			// phase de hit
			else if (techFrame >= tech.sframe && !alreadyHit) {
				HitboxService hbtech = tech.getHitbox(getPositionX(), getPositionY(), faceRight, ((HitboxRectService)hitbox).getWidth());
				CharacterService oth = other();

				if (hbtech.collidesWith(oth.getcharBox())) {
					alreadyHit = true;
					((FightCharService) oth).damaged(tech.damage, tech.hstun, tech.bstun);
				}


			}

			// phase de prépa
			else {

			}

			techFrame++;
			return; // on ne fait rien d'autre que la technique
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
		/** DEPLACEMENTS **/
		case LEFT:
			if(!block && !blockstun && !hitstun && !teching && getPositionY()==0)	
				moveLeft();
			break;

		case RIGHT:
			if(!block && !blockstun && !hitstun && !teching && getPositionY()==0)
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
			if(canAttack()) {
				jump();
			}
			break;

			/** CROUCH **/
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

			/** FIGHT ! **/
		case PUNCH:
			if (canAttack()) {
				TechData tec = TechData.punch();
				startTech(tec);
			}
			break;

		case KICK:
			if (canAttack()) {
				TechData tec = TechData.kick();
				startTech(tec);
			}
			break;

		case HEAD:
			if (canAttack()) {
				TechData tec = TechData.head();
				startTech(tec);
			}
			break;

			/** GUARD **/
		case GUARD:
			block = true;
			break;

		case DOWNGUARD:
			if (!iscrouch) crouch();
			block = true;
			break;

			/* else */
		case NEUTRAL:
			if(iscrouch) rise();
			block = false;

			break;

		default :
			return;
		}
	}

	@Override
	public void damaged(int degats, int hstun, int bstun) {
		if (block) {
			timestun = bstun;
			blockstun = true;
		} else {
			timestun = hstun;
			super.damaged(degats);
			hitstun = true;
		}

	}

	@Override
	public void guard() {
		block = true;
	}
}
