package streetfighter.implem;

import streetfighter.services.HitboxService;

public abstract class Hitbox implements HitboxService {
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
