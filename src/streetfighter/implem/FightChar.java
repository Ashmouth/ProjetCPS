package streetfighter.implem;

import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;
import streetfighter.data.TechData;

public class FightChar extends Character implements FightCharService {

	//REFINE CHARACHER

	private boolean block;
	private boolean blockstun;
	private boolean hitstun;
	private boolean teching;
	private TechData tech;

	@Override
	public void init(int l, int s, boolean f, EngineService e) {
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
	public TechData tech() {
		if(!isTeching()) {
			return null;
		}
		return tech;
	}

	@Override
	public boolean techFrame() { 
		if(!isTeching()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean techHasAlreadyHit() { 
		if(!isTeching()) {
			return false;
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
		if(isTeching()) {
			return;
		}
		this.tech = tech;
		teching = true;
	}

	//Observation:
}
