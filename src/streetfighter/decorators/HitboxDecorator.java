package streetfighter.decorators;

import streetfighter.services.HitboxService;

public class HitboxDecorator implements HitboxService {
	protected HitboxService delegate;
	
	public HitboxDecorator(HitboxService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public void init(int x, int y) {
		delegate.init(x, y);
	}
	
	//Observators: 
	@Override
	public int getPositionX() {
		return delegate.getPositionX();
	}
	
	@Override
	public int getPositionY() {
		return delegate.getPositionY();
	}
	
	@Override
	public boolean belongsTo(int x, int y) {
		return belongsTo(x, y);
	}
		
	@Override
	public boolean collidesWith(HitboxService h) {
		return collidesWith(h);
	}
	
	@Override
	public boolean equalsTo(HitboxService h) {
		return delegate.equalsTo(h);
	}
		
	//Operators: 
	@Override
	public void moveTo(int x, int y){
		delegate.moveTo(x, y);
	}
	
	@Override
	public void setPositionX(int x) {
		delegate.setPositionX(x);
	}

	@Override
	public void setPositionY(int y) {
		delegate.setPositionY(y);
	}
}
