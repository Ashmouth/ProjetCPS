package streetfighter.implem;

import streetfighter.condition.InvariantError;
import streetfighter.contracts.HitboxRectContract;
import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;

public class HitboxRect extends Hitbox implements HitboxRectService {
	private int height, width;
	
	public void init(int x, int y) {
		super.init(x, y);
		height = 0;
		width = 0;
	}
	
	public void init(int x, int y, int w, int h) {
		super.init(x, y);
		height = h;
		width = w;
	}
	
	//Observators:
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public boolean belongsTo(int x, int y) {
		if(getPositionX() <= x && x <= width) {
			if(getPositionY() <= y && y <= height) {
				return true;
			}
		}
		return false;
	}
		
	public boolean collidesWith(HitboxService h) {
		//Not possible without width and height
		if(h instanceof HitboxRectContract) {
			HitboxRectContract hr = (HitboxRectContract) h;
			return getPositionX() < hr.getPositionX() + hr.getWidth() &&
					getPositionX() + getWidth() > hr.getPositionX() &&
					getPositionY() < hr.getPositionY() + hr.getHeight() &&
					getPositionY() + getHeight() > hr.getPositionY();
		} else {
			return (getPositionX() <= h.getPositionX() &&
					h.getPositionX() <= (getPositionX() + getWidth()) &&
					getPositionY() <= h.getPositionY() &&
					h.getPositionY() <= (getPositionY() + getHeight()));
		}
	}
	
	public boolean equalsTo(HitboxRectService h) {
		if(getPositionX() == h.getPositionX() && getPositionY() == h.getPositionY()) {
			if(getHeight() == h.getHeight() && getWidth() == h.getWidth()) {
				return true;
			}
		}
		return false;
	}
		
	//Operators: 
	public void moveTo(int x, int y){ 
		this.setPositionX(x);
		this.setPositionY(y);
	}
	
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
		//[invariant]: 
	public void checkInvariant() {
		int pos_x = getPositionX();
		int pos_y = getPositionY();
		HitboxRectService delegate = null;
		HitboxRectContract H1 = new HitboxRectContract(delegate);
		H1.init(pos_x, pos_y);
		
		//CollidesWith(H,H1) = ∃ x,y:int × int, BelongsTo(H,x,y) ∧ BelongsTo(H1,x,y) 
		if(collidesWith(H1) != (belongsTo(pos_x,pos_y) && H1.belongsTo(pos_x,pos_y))) {
			throw new InvariantError("HitboxContract.checkInvariant()");
		}
		
		//EqualsTo(H,H1) = ∀ x,y:int × int, BelongsTo(H,x,y) = BelongsTo(H1,x,y) 
		if(equalsTo(H1) != (belongsTo(pos_x,pos_y) == H1.belongsTo(pos_x,pos_y))) {
			throw new InvariantError("HitboxContract.checkInvariant()");
		}
	}
}
