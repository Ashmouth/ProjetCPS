package streetfighter.decorators;

import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;

public class HitboxRectDecorator extends HitboxDecorator implements HitboxRectService {
	private HitboxRectService delegate;
	
	public HitboxRectDecorator(HitboxRectService delegate) {
		super(delegate);
	}
	
	public void initRect(int x, int y, int h, int w) {
		delegate.initRect(x, y, h, w);
	}
	
	//Observators: 
	public int getHeight() {
		return delegate.getHeight();
	}
	
	public int getWidth() {
		return delegate.getWidth();
	}
		
	public boolean collidesWith(HitboxService h) {
		return collidesWith(h);
	}
	
	public boolean equalsTo(HitboxService h) {
		return delegate.equalsTo(h);
	}
	
	public boolean collidesWith(HitboxRectService h) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean equalsTo(HitboxRectService h) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Observations:
	public void checkInvariant() {
		delegate.checkInvariant();
	}
	
	public void checkMoveTo() {
		delegate.checkMoveTo();
	}

	@Override
	public void init(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPositionX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPositionY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean belongsTo(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
