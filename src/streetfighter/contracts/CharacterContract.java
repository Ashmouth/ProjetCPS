package streetfighter.contracts;

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
	public void init(int l, int s, boolean f, EngineService e) throws PreConditionError {
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
		
		//TODO vérifier
		//life(init(l, s, f, e)) = l ∧ speed(init(l, s, f, e)) = s ∧ faceRight(init(l, s, f, e)) = 
		//f ∧engine(init(l, s, f, e)) = e 
		if (getLife() != l || getSpeed() != s || delegate.getFaceRight() != f || getEngine() != e) {
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
		
		/** DELEGATE **/
		super.moveLeft();
		
		/** POSTCONDITIONS **/
		
		checkInvariants();
	}

	@Override
	public void moveRight() {
		checkInvariants();
		
		/** PRECONDITIONS **/
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		super.moveRight();
		
		/** POSTCONDITIONS **/
		
		checkInvariants();
	}

	@Override
	public void switchSide() {
		checkInvariants();
		
		/** PRECONDITIONS **/
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		super.switchSide();
		
		/** POSTCONDITIONS **/
		
		checkInvariants();
	}

	@Override
	public void step(CommandData c) {
		checkInvariants();
		
		/** PRECONDITIONS **/
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		super.step(c);
		
		/** POSTCONDITIONS **/
		
		checkInvariants();
	}
	
	private void checkInvariants() {
		// TODO Auto-generated method stub
		
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
