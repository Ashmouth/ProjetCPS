package streetfighter.contracts;

import streetfighter.condition.PostConditionError;
import streetfighter.condition.PreConditionError;
import streetfighter.data.TechData;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;

public class FightCharContract extends CharacterContract implements FightCharService {
	//REFINE CHARACTER
	public FightCharContract(FightCharService delegate) {
		super(delegate);
	}
	
	@Override
	public void init(int l, int s, boolean f, EngineService e)  {
		//isBlocking(C) ∧ isBlockstunned(C) ∧ isHitstunned(C)  ∧ isTeching(C) = false
		delegate.init(l, s, f, e);
		
		if(isBlocking()) {
			throw new PostConditionError("FightChar.init.isBlocking");
		}
		if(isBlockstunned()){
			throw new PostConditionError("FightChar.init.isBlockstunned");
		}
		if(isHitstunned()) {
			throw new PostConditionError("FightChar.init.isHitstunned");
		}
		if(isTeching()) {
			throw new PostConditionError("FightChar.init.isTeching");
		}
	}

	//Observators: 
	@Override
	public boolean isBlocking() {
		return ((FightCharService) delegate).isBlocking();
	}

	@Override
	public boolean isBlockstunned() {
		return ((FightCharService) delegate).isBlockstunned(); 
	}

	@Override
	public boolean isHitstunned(){
		return ((FightCharService) delegate).isHitstunned(); 
	}

	@Override
	public boolean isTeching() {
		return ((FightCharService) delegate).isTeching(); 
	}

	@Override
	public TechData tech() {
		//pre tech(C) requires isTeching(C) 
		if(!isTeching()) {
			throw new PreConditionError("FightChar.tech.teching");
		}
		
		return ((FightCharService) delegate).tech();

	}

	@Override
	public int techFrame(){ 
		//pre techFrame(C) requires isTeching(C)
		checkInvariants();
		/** PRECONDITIONS **/
		if(!isTeching()) {
			throw new PreConditionError("FightChar.techFrame.teching");
		}
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		return ((FightCharService) delegate).techFrame();
		/** POSTCONDITIONS **/
	}

	@Override
	public boolean techHasAlreadyHit() { 
		//pre techHasAlreadyHit(C) requires isTeching(C)
		/** PRECONDITIONS **/
		if(!isTeching()) {
			throw new PreConditionError("FightChar.techHasAlreadyHit.teching");
		}

		/** CAPTURES **/
		
		/** DELEGATE **/
		return ((FightCharService) delegate).techHasAlreadyHit();
		/** POSTCONDITIONS **/
	}

	//Operators: 
	@Override
	public void startTech(TechData tech) {
		//pre startTech(C,T) requires ¬isTeching(C) ∧ ¬isHitstunned(C) ∧ ¬isBlockstunned(C) 
		/** PRECONDITIONS **/
		if(isTeching()) {
			throw new PreConditionError("FightChar.startTech.isTeching");
		}
		if(isHitstunned()) {
			throw new PreConditionError("FightChar.startTech.isHitstunned");
		}
		if(isBlockstunned()) {
			throw new PreConditionError("FightChar.startTech.isBlockstunned");
		}

		/** CAPTURES **/
		
		/** DELEGATE **/
		((FightCharService) delegate).startTech(tech);
		
		/** POSTCONDITIONS **/
		if(!isTeching()) {
			throw new PostConditionError("FightChar.startTech.isTeching");
		}
	}
	
	@Override
	public void moveLeft() { 
	//isHitstunned(C) ∨ isBlockstunned(C) ∨ isTeching(C) ⇒ positionX(moveLeft(C)) = positionX(C)
		checkInvariants();

		/** PRECONDITIONS **/
		if(getPositionY() != 0) {
			throw new PreConditionError("FightChar.moveLeft.getPositionY");
		}

		/** CAPTURES **/
		boolean ihsp = isHitstunned();
		boolean ibsp = isBlockstunned();
		boolean itp = isTeching();
		int posXold = getPositionX();

		/** DELEGATE **/
		delegate.moveLeft();

		/** POSTCONDITIONS **/
		if(ihsp || ibsp || itp) { 
			if(getPositionX() != posXold) {
				throw new PostConditionError("FightChar.moveLeft.posXold");
			}
		}

		checkInvariants();
		
	}
	
	@Override
	public void moveRight() { 
	//isHitstunned(C) ∨ isBlockstunned(C) ∨ isTeching(C) ⇒ positionX(moveRight(C)) = positionX(C)
		
		checkInvariants();

		/** PRECONDITIONS **/
		if(getPositionY() != 0) {
			throw new PreConditionError("FightChar.moveRight.getPositionY");
		}

		/** CAPTURES **/
		boolean ihsp = isHitstunned();
		boolean ibsp = isBlockstunned();
		boolean itp = isTeching();
		int posXold = getPositionX();

		/** DELEGATE **/
		delegate.moveRight();

		/** POSTCONDITIONS **/
		if(ihsp || ibsp || itp) { 
			if(getPositionX() != posXold) {
				throw new PostConditionError("FightChar.moveLeft.posXold");
			}
		}

		checkInvariants();
		
	}

	@Override
	public void damaged(int degats, int hstun, int bstun) {
		((FightCharService) delegate).damaged(degats, hstun, bstun);		
	}

	@Override
	public void guard() {
		
	}
}
