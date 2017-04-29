package streetfighter.contracts;

import streetfighter.decorators.DisplayDecorator;
import streetfighter.services.DisplayService;

public class DisplayContract extends DisplayDecorator {

	public DisplayContract(DisplayService delegate) {
        super(delegate);
    }
}
