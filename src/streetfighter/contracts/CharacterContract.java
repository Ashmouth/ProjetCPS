package streetfighter.contracts;

import com.sun.xml.internal.ws.api.pipe.Engine;

import streetfighter.condition.InvariantError;
import streetfighter.condition.PostConditionError;
import streetfighter.condition.PreConditionError;
import streetfighter.data.CommandData;
import streetfighter.decorators.CharacterDecorator;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.HitboxService;

public class CharacterContract extends CharacterDecorator {
	public CharacterContract(CharacterService delegate) {
		super(delegate);
	}

	@Override
	public void init(int l, int s, boolean f, EngineService e) {
		checkInvariants();

		/** PRECONDITIONS **/
		//pre init(l,s,f,e) requires l > 0 ∧ s > 0
		boolean test = l > 0 && s > 0;
		if(!test) {
			throw new PreConditionError("CharacterDecorator.CharacterDecorator()");
		}

		/** CAPTURES **/

		/** DELEGATE **/
		super.init(l, s, f, e);

		/** POSTCONDITIONS **/

		// life(init(l, s, f, e)) = l ∧ speed(init(l, s, f, e)) = s ∧ faceRight(init(l, s, f, e)) = f ∧
		// engine(init(l, s, f, e)) = e 
		if (getLife() != l || getSpeed() != s || getFaceRight() != f || getEngine() != e) {
			throw new PostConditionError("Hitbox.init(l, s, f, e)");
		}
		//∃h :Hitbox, charbox(init(l, s, f, e)) = h
		if (getcharBox() == null) {
			throw new PostConditionError("Hitbox.init(l, s, f, e)");
		}

		checkInvariants();
	}

	//Observators: 
	@Override
	public int getPositionX() {
		return super.getPositionX();
	}

	@Override
	public int getPositionY() {
		return super.getPositionY();
	}

	@Override
	public EngineService getEngine() {
		return super.getEngine();
	}

	@Override
	public HitboxService getcharBox() {
		return super.getcharBox(); //TODO Check
	}

	@Override
	public int getLife() {
		return super.getLife();
	}

	@Override
	public int getSpeed() {
		return super.getSpeed();
	}

	@Override
	public boolean getFaceRight() {
		return super.getFaceRight();
	}

	@Override
	public boolean isDead() {
		return super.isDead();
	}

	//Operators: 
	@Override
	public void moveLeft() {
		checkInvariants();

		/** PRECONDITIONS **/

		/** CAPTURES **/
		int posX_pre = getPositionX();
		int posY_pre = getPositionY();
		int life_pre = getLife();
		boolean faceRight_pre = getFaceRight();
		
		int speed = getSpeed();

		/** DELEGATE **/
		super.moveLeft();

		/** POSTCONDITIONS **/
		// (∃i, player(engine(C), i) != C ∧ collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveLeft(C)) = positionX(C)
		
		//TODO supprimé -> remplacé par l'invariant "pas de collision"
		
		// positionX(C) >= speed(C) ∧ (∀i, player(engine(C), i) != C 
		// ⇒ ¬collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i))))
		// ⇒ positionX(moveLeft(C)) = positionX(C) − speed(C)
		if (posX_pre >= speed) {
			if (getPositionX() != posX_pre - speed) {
				throw new PostConditionError("Character.moveLeft.speed1");
			}
		}
		
		// positionX(C) ≤ speed(C) ∧(∀i, player(engine(C), i) != C 
		// ⇒ ¬collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i))))
		// ⇒ positionX(moveLeft(C)) = 0
		if (posX_pre < speed) {
			if (getPositionX() != 0) {
				throw new PostConditionError("Character.moveLeft.speed2");
			}
		}
		
		// faceRight(moveLeft(C)) = faceRight(C) ∧ life(moveLeft(C)) = life(C)
		if (!(getFaceRight() == faceRight_pre &&
				getLife() == life_pre)) {
			throw new PostConditionError("Character.moveLeft.faceright/life");
		}
		
		// positionY(moveLeft(C)) = positionY(C)
		if(getPositionY() != posY_pre) {
			throw new PostConditionError("Character.moveLeft.positionY");
		}

		checkInvariants();
	}

	@Override
	public void moveRight() {
		checkInvariants();

		/** PRECONDITIONS **/

		/** CAPTURES **/
		int posX_pre = getPositionX();
		int posY_pre = getPositionY();
		int life_pre = getLife();
		boolean faceRight_pre = getFaceRight();
		
		int speed = getSpeed();

		/** DELEGATE **/
		super.moveLeft();

		/** POSTCONDITIONS **/
		// (∃i, player(engine(C), i) != C ∧ collisionwith(hitbox(moveRight(C)), hitbox(player(engine(C), i)))) ⇒ positionX(moveRight(C)) = positionX(C)
		
		//TODO supprimé -> remplacé par l'invariant "pas de collision"
		
		// positionX(C) <= speed(C) ∧ (∀i, player(engine(C), i) != C 
		// ⇒ ¬collisionwith(hitbox(moveRight(C)), hitbox(player(engine(C), i))))
		// ⇒ positionX(moveRight(C)) = positionX(C) + speed(C)
		if (posX_pre <= speed) {
			if (getPositionX() != posX_pre + speed) {
				throw new PostConditionError("Character.moveRight.speed1");
			}
		}
		
		// positionX(C) ≤ speed(C) ∧(∀i, player(engine(C), i) != C 
		// ⇒ ¬collisionwith(hitbox(moveRight(C)), hitbox(player(engine(C), i))))
		// ⇒ positionX(moveRight(C)) = 0
		if (posX_pre > speed) {
			if (getPositionX() != getEngine().getWidth()) {
				throw new PostConditionError("Character.moveRight.speed2");
			}
		}
		
		// faceRight(moveRight(C)) = faceRight(C) ∧ life(moveRight(C)) = life(C)
		if (!(getFaceRight() == faceRight_pre &&
				getLife() == life_pre)) {
			throw new PostConditionError("Character.moveRight.faceright/life");
		}
		
		// positionY(moveRight(C)) = positionY(C)
		if(getPositionY() != posY_pre) {
			throw new PostConditionError("Character.moveLeft.positionY");
		}

		checkInvariants();
	}

	@Override
	public void switchSide() {
		checkInvariants();

		/** PRECONDITIONS **/

		/** CAPTURES **/
		boolean faceRight_pre = getFaceRight();
		int posX_pre = getPositionX();

		/** DELEGATE **/
		super.switchSide();

		/** POSTCONDITIONS **/
		// faceRight(switchSide(C))! = faceRight(C)
		if (getFaceRight() == faceRight_pre) {
			throw new PostConditionError("CharacterContract.switchSide.faceRight");
		}
		
		// positionX(switchSide(C)) = positionX(C)
		if (getPositionX() != posX_pre) {
			throw new PostConditionError("CharacterContract.switchSide.posX");
		}
		
		checkInvariants();
	}

	@Override
	public void step(CommandData c) {
		checkInvariants();

		/** PRECONDITIONS **/
		// step() requires ¬dead
		if (isDead()) {
			throw new PreConditionError("CharacterContract.step.isDead");
		}

		/** CAPTURES **/

		/** DELEGATE **/
		super.step(c);

		/** POSTCONDITIONS **/
		//TODO
		// step(C, LEFT) = moveLeft(C)
		// step(C, RIGHT) = moveRight(C)
		// step(C, NEUTRAL) = C

		checkInvariants();
	}

	private void checkInvariants() {
		// positionX(C) > 0 ∧ positionX(C) < Engine:: width(engine) ∧ positionY(C) > 0 ∧ positionY(C) < Engine:: height(engine) ∧ dead(C) = ¬(life > 0) 
		if(!(getPositionX() > 0 &&
				getPositionX() < getEngine().getWidth() && 
				getPositionY() > 0 && 
				getPositionY() < getEngine().getHeight() &&
				isDead() == !(getLife() > 0)
				)) {
			throw new InvariantError("CharacterContract.position");
		}

		// !∃i tq (player(engine(c), i) != C ∧ collisionwith(hitbox(C), hitbox(player(engine(C), i))))
		if(getEngine().getCharacter(1).getcharBox().collidesWith(getEngine().getCharacter(2).getcharBox())) {
			throw new InvariantError("CharacterContract.collisionWithPlayer");
		}
	}
}
