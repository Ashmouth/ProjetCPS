package streetfighter.decorators;

import streetfighter.condition.PreConditionError;
import streetfighter.services.CharacterService;
import streetfighter.services.CommandService;

public class CharacterDecorator implements CharacterService {
	int life;
	int speed;
	boolean faceRight;
	EngineDecorator engine;
	CommandDecorator com;

	public CharacterDecorator(int l, int s, boolean f, EngineDecorator e) {
		//pre init(l,s,f,e) requires l > 0 ∧ s > 0
		boolean test = l > 0 && s > 0;
		if(!test) {
			throw new PreConditionError("msg");
		}
		life = l;
		speed = s;
		faceRight = f;
		engine = e;
	}

	//Observators: 
	public int getPositionX() {
		return x;
	}

	public int getPositionY() {
		return y;
	}

	public EngineDecorator getEngine() {
		return engine;
	}

	public HitboxDecorator getcharBox() {
		return hitbox; 
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

	public void step(CommandDecorator c) {
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
