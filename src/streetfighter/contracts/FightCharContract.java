package streetfighter.contracts;

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
		delegate.init(l, s, f, e);
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
	public boolean techFrame(){ 
		//pre techFrame(C) requires isTeching(C)
		if(!isTeching()) {
			throw new PreConditionError("FightChar.techFrame.teching");
		}
		
		return ((FightCharService) delegate).techFrame();
	}

	@Override
	public boolean techHasAlreadyHit() { 
		//pre techHasAlreadyHit(C) requires isTeching(C)
		if(!isTeching()) {
			throw new PreConditionError("FightChar.techHasAlreadyHit.teching");
		}
		return ((FightCharService) delegate).techHasAlreadyHit();
	}

	//Operators: 
	@Override
	public void startTech(TechData tech) {
		//pre startTech(C,T) requires ¬isTeching(C) ∧ ¬isHitstunned(C) ∧ ¬isBlockstunned(C) 
		if(isTeching()) {
			throw new PreConditionError("FightChar.startTech.isTeching");
		}
		if(isHitstunned()) {
			throw new PreConditionError("FightChar.startTech.isHitstunned");
		}
		if(isBlockstunned()) {
			throw new PreConditionError("FightChar.startTech.isBlockstunned");
		}
		((FightCharService) delegate).startTech(tech);
	}

	//Observation: ...
}
