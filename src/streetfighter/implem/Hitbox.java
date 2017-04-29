package streetfighter.implem;

import streetfighter.condition.InvariantError;
import streetfighter.contracts.HitboxContract;
import streetfighter.services.HitboxService;

public class Hitbox implements HitboxService {
private int pos_x, pos_y;
	
	public void init(int x, int y) {
		this.pos_x = x;
		this.pos_y = y;
	}
	
	//Observators: 
	public int getPositionX() {
		return pos_x;
	}
	
	public int getPositionY() {
		return pos_y;
	}
	
	public boolean belongsTo(int x, int y) {
		return(pos_x == x && pos_y == y);
	}
		
	public boolean collidesWith(HitboxService h) {
		return(pos_x == h.getPositionX() && pos_y == h.getPositionY());
	}
	
	public boolean equalsTo(HitboxService h) {
		if(getPositionX() == h.getPositionX() && getPositionY() == h.getPositionY()) {
			return true;
		}
		return false;
	}
		
	//Operators: 
	@Override
	public void moveTo(int x, int y){ 
		this.setPositionX(x);
		this.setPositionY(y);
	}
	
	@Override
	public void setPositionX(int x) {
		pos_x = x;
	}

	@Override
	public void setPositionY(int y) {
		pos_y = y;
	}
	
	//Observations:
}
