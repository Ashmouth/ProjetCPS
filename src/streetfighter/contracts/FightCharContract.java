package streetfighter.contracts;

import streetfighter.condition.PreConditionError;
import streetfighter.decorators.FightCharDecorator;
import streetfighter.data.TechData;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;

public class FightCharContract extends FightCharDecorator {
	//REFINE CHARACHER
	
	private boolean block;
	private boolean blockstun;
	private boolean hitstun;
	private boolean teching;
	private TechData tech;

	public FightCharContract(FightCharService delegate) {
		super(delegate);
	}
	
	@Override
	public void init(int l, int s, boolean f, EngineService e) throws PreConditionError {
		super.init(l, s, f, e);
		block = false;
		blockstun = false;
		hitstun = false;
		teching = false;
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
	public TechData tech() throws PreConditionError {
		//pre tech(C) requires isTeching(C) 
		if(!isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		return tech;
	}

	@Override
	public boolean techFrame() throws PreConditionError { 
		//pre techFrame(C) requires isTeching(C)
		if(!isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		return true;
	}

	@Override
	public boolean techHasAlreadyHit() throws PreConditionError { 
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
	public void startTech(TechData tech) throws PreConditionError {
		//pre startTech(C,T) requires ¬isTeching(C) 
		if(isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		this.tech = tech;
		teching = true;
	}

	//Observation: ...
}
