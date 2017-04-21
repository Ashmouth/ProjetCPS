package streetfighter.decorators;

import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;

public class HitboxRectDecorator extends HitboxDecorator implements HitboxRectService {
	private HitboxRectService delegate;
	
	public HitboxRectDecorator(HitboxRectService delegate) {
		super(delegate);
	}
	
	public void init(int x, int y, int h, int w) {
		delegate.init(x, y, h, w);
	}
	
	//Observators: 
	public int getHeight() {
		return delegate.getHeight();
	}
	
	public int getWidth() {
		return delegate.getWidth();
	}

	//Operators :
	public void resize(int w, int h) {
		delegate.resize(w, h);
		
	}
		
	
}
