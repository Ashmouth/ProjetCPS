package streetfighter.contracts;

import streetfighter.condition.PreConditionError;
import streetfighter.data.CommandData;
import streetfighter.decorators.CharacterDecorator;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.HitboxService;

public class CharacterContract extends CharacterDecorator {
	int life;
	int speed;
	boolean faceRight;
	EngineService engine;
	HitboxService hitbox;
	
	public CharacterContract(CharacterService delegate) {
		super(delegate);
	}

	@Override
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
		//life(init(l, s, f, e)) = l ∧ speed(init(l, s, f, e)) = s ∧ faceRight(init(l, s, f, e)) = 
		//f ∧engine(init(l, s, f, e)) = e 
		if (getLife() != l || getSpeed() != s || faceRight != f || getEngine() != e) {
			throw new PreConditionError("Hitbox.init(l, s, f, e)");
		}
		//∃h :Hitbox, charbox(init(l, s, f, e)) = h
		if (getcharBox() == null) {
			throw new PreConditionError("Hitbox.init(l, s, f, e)");
		}
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
		return hitbox; //TODO Check
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

	@Override
	public void step(CommandData c) {
		//pre step() requires ¬dead
	}


	//Observations: 
	/*
			[invariant]: 
				positionX(C) > 0 ∧ positionX(C) < Engine:: width(engine) positionY(C) > 0 ∧ positionY(C) < Engine:: height(engine) dead(C) = ¬(life > 0) 
			
			[moveLeft]: 
				(∃i, player(engine(C), i) 6= C ∧ collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveLeft(C)) = positionX(C)
				positionX(C) ≤ speed(C) ∧(∀i, player(engine(C), i) 6= C ⇒ ¬collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveLeft(C)) = positionX(C) − speed(C)
				positionX(C) > speed(C) ∧(∀i, player(engine(C), i) 6= C ⇒ ¬collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveLeft(C)) = 0 faceRight(moveLeft(C)) = faceRight(C) ∧ life(moveLeft(C)) = life(C) positionY(moveLeft(C)) = positionY(C) 
			[moveRight]: 
				[moveRight]: 
				(∃i, player(engine(C), i) != C ∧ collisionwith(hitbox(moveRight(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveRight(C)) = positionX(C)
				positionX(C) ≤ speed(C) ∧(∀i, player(engine(C), i) != C ⇒ ¬collisionwith(hitbox(moveRight(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveRight(C)) = positionX(C) + speed(C)
				positionX(C) > speed(C) ∧(∀i, player(engine(C), i) != C ⇒ ¬collisionwith(hitbox(moveRight(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveRight(C)) = 0 faceRight(moveRight(C)) = faceLeft(C) ∧ life(moveRight(C)) = life(C) positionY(moveRight(C)) = positionY(C) 
			[switchSide]: 
				faceRight(switchSide(C))! = faceRight(C) positionX(switchSide(C)) = positionX(C) 
			[step]: 
				step(C, LEFT) = moveLeft(C) step(C, RIGHT) = moveRight(C) step(C, NEUTRAL) = C
	 */
}
