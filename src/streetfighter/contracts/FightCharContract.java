package streetfighter.contracts;

import streetfighter.condition.PreConditionError;
import streetfighter.decorators.FightCharDecorator;
import streetfighter.data.TechData;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;

public class FightCharContract extends FightCharDecorator {
	//REFINE CHARACTER
	public FightCharContract(FightCharService delegate) {
		super(delegate);
	}
	
	@Override
	public void init(int l, int s, boolean f, EngineService e)  {
		super.init(l, s, f, e);
	}

	//Observators: 
	@Override
	public boolean isBlocking() {
		return super.isBlocking();
	}

	@Override
	public boolean isBlockstunned() {
		return super.isBlockstunned(); 
	}

	@Override
	public boolean isHitstunned(){
		return super.isHitstunned(); 
	}

	@Override
	public boolean isTeching() {
		return super.isTeching(); 
	}

	@Override
	public TechData tech() {
		//pre tech(C) requires isTeching(C) 
		if(!isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		return tech;
	}

	@Override
	public boolean techFrame(){ 
		//pre techFrame(C) requires isTeching(C)
		if(!isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		return true;
	}

	@Override
	public boolean techHasAlreadyHit() { 
		//pre techHasAlreadyHit(C) requires isTeching(C)
		if(!isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		if(tech == null) {
			return true;
		} else {
			this.tech = null;
			teching = false;
		}
		return false;
	}

	//Operators: 
	@Override
	public void startTech(TechData tech) {
		//pre startTech(C,T) requires ¬isTeching(C) 
		if(isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		this.tech = tech;
		teching = true;
	}

	//Observation: ...
}
