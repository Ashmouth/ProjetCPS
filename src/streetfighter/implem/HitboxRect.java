package streetfighter.implem;

import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;

public class HitboxRect extends Hitbox implements HitboxRectService {
	private int height, width;
	
	@Override
	public void init(int x, int y, int w, int h) {
		super.init(x, y);
		height = h;
		width = w;
	}
	
	//Observators: 
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public boolean belongsTo(int x, int y) {
		if(getPositionX() <= x && x <= getPositionX()+width) {
			if(getPositionY() <= y && y <= getPositionY() + height) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean collidesWith(HitboxService h) {
		if(h instanceof HitboxRectService) {
			HitboxRectService hr = (HitboxRectService) h;
			return getPositionX() < hr.getPositionX() + hr.getWidth() &&
					getPositionX() + getWidth() > hr.getPositionX() &&
					getPositionY() < hr.getPositionY() + hr.getHeight() &&
					getPositionY() + getHeight() > hr.getPositionY();
		}
		return false;
	}
	
	@Override
	public boolean equalsTo(HitboxService h) {
		if(h instanceof HitboxRectService) {
			HitboxRectService hr = (HitboxRectService) h;
			if(getPositionX() == hr.getPositionX() && getPositionY() == hr.getPositionY()) {
				if(getHeight() == hr.getHeight() && getWidth() == hr.getWidth()) {
					return true;
				}
			}
		}
		return false;
	}
	
	//Operators:
	@Override
	public void resize(int w, int h) {
		if(h >= 0) {
			height = h;
		}
		if(w >= 0) {
			width = w;
		}
	}
	
	//Observations:
}
