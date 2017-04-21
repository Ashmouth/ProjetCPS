package streetfighter.decorators;

import streetfighter.services.HitboxService;

public class HitboxDecorator implements HitboxService {
	private HitboxService delegate;
	
	public HitboxDecorator(HitboxService delegate) {
		this.delegate = delegate;
	}
	
	public void init(int x, int y) {
		delegate.init(x, y);
	}
	
	//Observators: 
	public int getPositionX() {
		return delegate.getPositionX();
	}
	
	public int getPositionY() {
		return delegate.getPositionY();
	}
	
	public boolean belongsTo(int x, int y) {
		return belongsTo(x, y);
	}
		
	public boolean collidesWith(HitboxService h) {
		return collidesWith(h);
	}
	
	public boolean equalsTo(HitboxService h) {
		return delegate.equalsTo(h);
	}
		
	//Operators: 
	public void moveTo(int x, int y){
		delegate.moveTo(x, y);
	}
	
	//Observations:
	public void checkInvariant() {
		delegate.checkInvariant();
	}
	
	public void checkMoveTo() {
		delegate.checkMoveTo();
	}
}
