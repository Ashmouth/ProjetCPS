package streetfighter.decorators;

import streetfighter.services.DisplayService;
import streetfighter.services.EngineService;

public class DisplayDecorator implements DisplayService {

	protected DisplayService delegate;

	public DisplayDecorator(DisplayService delegate) {
		this.delegate = delegate;
	}

	@Override
	public void init(EngineService engine) {
		delegate.init(engine);
	}

	@Override
	public EngineService getEngine() {
		return delegate.getEngine();
	}
}
