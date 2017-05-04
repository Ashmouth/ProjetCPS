package streetfighter.contracts;

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
		/** PRECONDITIONS **/
		//pre init(l,s,f,b) requires l > 0 ∧ s > 0
		boolean test = l > 0 && s > 0;
		if(!test) {
			throw new PreConditionError("CharacterContract.init");
		}

		/** CAPTURES **/

		/** DELEGATE **/
		delegate.init(l, s, f, e);

		/** POSTCONDITIONS **/

		// life(init(l, s, f, b)) = l ∧ speed(init(l, s, f, b)) = s ∧ faceRight(init(l, s, f, b)) = f ∧
		// engine(init(l, s, f, e)) = e
		if (getLife() != l || getSpeed() != s || getFaceRight() != f || getEngine() != e) {
			throw new PostConditionError("CharacterContract.init.lsfeb");
		}
		
		// ∃ b:Hitbox, charbox(init(l, s, f, e)) = b
		if (getcharBox() == null) {
			throw new PostConditionError("CharacterContract.init.charbox");
		}
	}

	//Observators: 
	@Override
	public int getPositionX() {
		return delegate.getPositionX();
	}

	@Override
	public int getPositionY() {
		return delegate.getPositionY();
	}

	@Override
	public EngineService getEngine() {
		return delegate.getEngine();
	}

	@Override
	public HitboxService getcharBox() {
		return delegate.getcharBox(); //TODO Check
	}

	@Override
	public int getLife() {
		return delegate.getLife();
	}

	@Override
	public int getSpeed() {
		return delegate.getSpeed();
	}

	@Override
	public boolean getFaceRight() {
		return delegate.getFaceRight();
	}

	@Override
	public boolean isDead() {
		return delegate.isDead();
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
		delegate.moveLeft();

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
		delegate.moveLeft();

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
		delegate.switchSide();

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
		delegate.step(c);

		/** POSTCONDITIONS **/
		//TODO
		// step(C, LEFT) = moveLeft(C)
		// step(C, RIGHT) = moveRight(C)
		// step(C, NEUTRAL) = C

		checkInvariants();
	}
	
    @Override
    public void setPosition(int x, int y) {
//        checkInvariants();

        /** PRECONDITIONS **/
        //TODO VOIR SI CETTE FONCTION ET DAMAGED SONT UTILES. + dans la spec

        /** CAPTURES **/

        /** DELEGATE **/
        delegate.setPosition(x, y);

        /** POSTCONDITIONS **/
        // positionX(setPosition(C,x,y)) = x
        // positionY(setPosition(C,x,y)) = y
        if (getPositionX() != x || getPositionY() != y) {
            throw new PostConditionError("CharacterContract.setPosition");
        }

//        checkInvariants();
    }
	
	@Override
	public void damaged(int de) {
		checkInvariants();

		/** PRECONDITIONS **/
		

		/** CAPTURES **/

		/** DELEGATE **/
		delegate.damaged(de);

		/** POSTCONDITIONS **/
		
		

		checkInvariants();
	}

	private void checkInvariants() {
        // positionX(C) > 0 ∧ positionX(C) < Engine:: width(engine) ∧ positionY(C) > 0 ∧ positionY(C) < Engine:: height(engine) ∧ dead(C) = ¬(life > 0) 
//        System.out.println(getPositionX());
        if(!(getPositionX() >= 0 &&
                getPositionX() < getEngine().getWidth() && 
                getPositionY() >= 0 && 
                getPositionY() < getEngine().getHeight() &&
                isDead() == !(getLife() > 0)
                )) {
            throw new InvariantError("CharacterContract.position");
        }
        
        // positionX(hitbox(C)) = positionX(C) 
        // positionY(hitbox(C)) = positionY(C)
        if (getPositionX() != getcharBox().getPositionX() ||
            getPositionY() != getcharBox().getPositionY()) {
            throw new InvariantError("CharacterContract.hitboxposition");
        }
        
        // ∃ engine(C)
        if (getEngine() == null) {
            throw new InvariantError("CharacterContract.noboundengine");
        }

        // !∃i tq (player(engine(c), i) != C ∧ collisionwith(hitbox(C), hitbox(player(engine(C), i))))
        if(getEngine().getCharacter(1).getcharBox().collidesWith(getEngine().getCharacter(2).getcharBox())) {
            throw new InvariantError("CharacterContract.collisionWithPlayer");
        }
    }
}
