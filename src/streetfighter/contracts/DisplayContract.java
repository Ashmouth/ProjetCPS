package streetfighter.contracts;

import streetfighter.decorators.DisplayDecorator;
import streetfighter.services.DisplayService;
import streetfighter.services.EngineService;

public class DisplayContract extends DisplayDecorator {

	public DisplayContract(DisplayService delegate) {
        super(delegate);
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
