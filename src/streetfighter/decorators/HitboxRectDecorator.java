package streetfighter.decorators;

import streetfighter.services.HitboxRectService;

public class HitboxRectDecorator extends HitboxDecorator implements HitboxRectService {
	private HitboxRectService delegate;
	
	public HitboxRectDecorator(HitboxRectService delegate) {
		super(delegate);
	}
	
	@Override
	public void init(int x, int y, int h, int w) {
		delegate.init(x, y, h, w);
	}
	
	//Observators: 
	@Override
	public int getHeight() {
		return delegate.getHeight();
	}
	
	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	//Operators :
	@Override
	public void resize(int w, int h) {
		delegate.resize(w, h);
		
	}
}
