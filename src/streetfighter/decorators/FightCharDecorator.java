package streetfighter.decorators;

import streetfighter.condition.PreConditionError;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;

public class FightCharDecorator extends CharacterDecorator implements FightCharService {
	private FightCharService delegate;

	public FightCharDecorator(FightCharService delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	public void init(int l, int s, boolean f, EngineService e) throws PreConditionError {
		super.init(l, s, f, e);
	}

	//Observators: 
	public boolean isBlocking() {
		return delegate.isBlocking();
	}

	public boolean isBlockstunned() {
		return delegate.isBlockstunned();
	}

	public boolean isHitstunned(){
		return delegate.isHitstunned();
	}

	public boolean isTeching() {
		return delegate.isTeching();
	}

	public TechData tech() throws PreConditionError {
		return delegate.tech();
	}

	public boolean techFrame() throws PreConditionError { 
		return delegate.techFrame();
	}

	public boolean techHasAlreadyHit() throws PreConditionError { 
		return delegate.techHasAlreadyHit();
	}

	//Operators: 
	public void startTech(TechData tech) throws PreConditionError {
		delegate.startTech(tech);
	}

	//Observation:
}
