package streetfighter.core;

import streetfighter.command.Tech;
import streetfighter.condition.PreConditionError;

public class FightChar extends Character {
	
	boolean block;
	boolean blockstun;
	boolean hitstun;
	boolean teching;

	public FightChar(int l, int s, boolean f, Engine e) {
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

	public Tech tech() {
		//pre tech(C) requires isTeching(C) 
		if(!isTeching()) {
			throw new PreConditionError("FightChar.tech()");
		}
		return new Tech();
	}

	public boolean techFrame() { 
		//pre techFrame(C) requires isTeching(C)
	}

	public boolean techHasAlreadyHit() { 
		//pre techHasAlreadyHit(C) requires isTeching(C)
	}

	//Operators: 
	public void startTech(Tech tech) {
		//pre startTech(C,T) requires ¬isTeching(C) 
	}

	//Observation: ...
}
