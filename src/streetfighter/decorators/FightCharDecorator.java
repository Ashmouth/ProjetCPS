package streetfighter.decorators;

import streetfighter.condition.PreConditionError;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;
import streetfighter.data.TechData;

public class FightCharDecorator extends CharacterDecorator implements FightCharService {
	private FightCharService delegate;

	public FightCharDecorator(FightCharService delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	
	@Override
	public void init(int l, int s, boolean f, EngineService e) throws PreConditionError {
		super.init(l, s, f, e);
	}

	//Observators: 
	@Override
	public boolean isBlocking() {
		return delegate.isBlocking();
	}

	@Override
	public boolean isBlockstunned() {
		return delegate.isBlockstunned();
	}

	@Override
	public boolean isHitstunned(){
		return delegate.isHitstunned();
	}

	@Override
	public boolean isTeching() {
		return delegate.isTeching();
	}

	@Override
	public TechData tech() throws PreConditionError {
		return delegate.tech();
	}

	@Override
	public boolean techFrame() throws PreConditionError { 
		return delegate.techFrame();
	}

	@Override
	public boolean techHasAlreadyHit() throws PreConditionError { 
		return delegate.techHasAlreadyHit();
	}

	//Operators: 
	@Override
	public void startTech(TechData tech) throws PreConditionError {
		delegate.startTech(tech);
	}

	//Observation:
}
