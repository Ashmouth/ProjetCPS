package streetfighter.contracts;

import streetfighter.condition.PreConditionError;
import streetfighter.decorators.CharacterDecorator;
import streetfighter.services.CharacterService;
import streetfighter.services.CommandService;
import streetfighter.services.EngineService;
import streetfighter.services.HitboxService;

public class CharacterContract extends CharacterDecorator {
	int life;
	int speed;
	boolean faceRight;
	EngineService engine;
	CommandService com;
	HitboxService hitbox;
	
	public CharacterContract(CharacterService delegate) {
		super(delegate);
	}

	public void init(int l, int s, boolean f, EngineService e) throws PreConditionError {
		//pre init(l,s,f,e) requires l > 0 ∧ s > 0
		boolean test = l > 0 && s > 0;
		if(!test) {
			throw new PreConditionError("CharacterDecorator.CharacterDecorator()");
		}
		life = l;
		speed = s;
		faceRight = f;
		engine = e;
	}

	//Observators: 
	public int getPositionX() {
		return hitbox.getPositionX();
	}

	public int getPositionY() {
		return hitbox.getPositionY();
	}

	public EngineService getEngine() {
		return engine;
	}

	public HitboxService getcharBox() {
		return hitbox; //TODO Check
	}

	public int getLife() {
		return life;
	}

	public int getSpeed() {
		return speed;
	}

	public boolean getFaceRight() {
		return faceRight;
	}

	public boolean isDead() {
		if(life == 0) {
			return true;
		} else {
			return false;
		}
	}

	//Operators: 
	public void moveLeft() {
		// 
	}

	public void moveRight() {
		// 
	}

	public void switchSide() {
		if(faceRight == true) {
			faceRight = false;
		} else {
			faceRight = true;
		}
	}

	public void step(CommandService c) {
		//pre step() requires ¬dead
	}


	//Observations: 
	/*
			[invariant]: 
				positionX(C) > 0 ∧ positionX(C) < Engine:: width(engine) positionY(C) > 0 ∧ positionY(C) < Engine:: height(engine) dead(C) = ¬(life > 0) 
			[init]: 
				life(init(l, s, f, e)) = l ∧ speed(init(l, s, f, e)) = s ∧ faceRight(init(l, s, f, e)) = f ∧engine(init(l, s, f, e)) = e 
			∃h :Hitbox, charbox(init(l, s, f, e)) = h 
			[moveLeft]: 
				(∃i, player(engine(C), i) 6= C ∧ collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveLeft(C)) = positionX(C)
				positionX(C) ≤ speed(C) ∧(∀i, player(engine(C), i) 6= C ⇒ ¬collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveLeft(C)) = positionX(C) − speed(C)
				positionX(C) > speed(C) ∧(∀i, player(engine(C), i) 6= C ⇒ ¬collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveLeft(C)) = 0 faceRight(moveLeft(C)) = faceRight(C) ∧ life(moveLeft(C)) = life(C) positionY(moveLeft(C)) = positionY(C) 
			[moveRight]: 
				...
			[switchSide]: 
				faceRight(switchSide(C))! = faceRight(C) positionX(switchSide(C)) = positionX(C) 
			[step]: 
				step(C, LEFT) = moveLeft(C) step(C, RIGHT) = moveRight(C) step(C, NEUTRAL) = C
	 */
}
