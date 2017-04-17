package streetfighter.decorators;

import streetfighter.condition.PreConditionError;
import streetfighter.services.FightCharService;

public class FightCharDecorator extends CharacterDecorator implements FightCharService {
	
	private boolean block;
	private boolean blockstun;
	private boolean hitstun;
	private boolean teching;
	private TechDecorator tech;

	public FightCharDecorator(int l, int s, boolean f, EngineDecorator e) {
		super(l, s, f, e);
		// TODO Auto-generated constructor stub
	}

	//Observators: 
	public boolean isBlocking() {
		return block;
	}

	public boolean isBlockstunned() {
		return blockstun; 
	}

	public boolean isHitstunned(){
		return hitstun; 
	}

	public boolean isTeching() {
		return teching; 
	}

	public TechDecorator tech() throws PreConditionError {
		//pre tech(C) requires isTeching(C) 
		if(!isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		return tech;
	}

	public boolean techFrame() throws PreConditionError { 
		//pre techFrame(C) requires isTeching(C)
		if(!isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		return true;
	}

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
	public void startTech(TechDecorator tech) throws PreConditionError {
		//pre startTech(C,T) requires ¬isTeching(C) 
		if(isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		this.tech = tech;
		teching = true;
	}

	//Observation: ...
}
