package streetfighter.decorators;

import streetfighter.services.DisplayService;

public class DisplayDecorator implements DisplayService {

	protected DisplayService delegate;

	public DisplayDecorator(DisplayService delegate) {
		this.delegate = delegate;
	}
}
