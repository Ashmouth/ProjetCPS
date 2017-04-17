package streetfighter.decorators;

import streetfighter.condition.PreConditionError;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;
import streetfighter.services.TechService;

public class FightCharDecorator extends CharacterDecorator implements FightCharService {
	
	private boolean block;
	private boolean blockstun;
	private boolean hitstun;
	private boolean teching;
	private TechService tech;

	public FightCharDecorator(FightCharService delegate) {
		super(delegate);
	}
	
	public void init(int l, int s, boolean f, EngineService e) throws PreConditionError {
		super.init(l, s, f, e);
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

	public TechService tech() throws PreConditionError {
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
	public void startTech(TechService tech) throws PreConditionError {
		//pre startTech(C,T) requires ¬isTeching(C) 
		if(isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		this.tech = tech;
		teching = true;
	}

	//Observation: ...
}
