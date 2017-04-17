package streetfighter.decorators;

import streetfighter.condition.PostConditionError;
import streetfighter.services.HitboxService;

public class HitboxDecorator implements HitboxService {
	
	private int pos_x, pos_y;
	private HitboxService delegate;
	
	public HitboxDecorator(HitboxService delegate) {
		this.delegate = delegate;
	}
	
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
		//Not possible without width and height
		return true;
	}
		
	public boolean collidesWith(HitboxService h) {
		//Not possible without width and height
		return true;
	}
	
	public boolean equalsTo(HitboxService h) {
		if(getPositionX() == h.getPositionX() && getPositionY() == h.getPositionY()) {
			return true;
		}
		return false;
	}
		
	//Operators: 
	public void MoveTo(int x, int y){
		pos_x += pos_x + x;
		pos_y += pos_y + y;
	}
	
	//Observations: 
		//[invariant]: 
	public void checkInvariant() {
		//CollidesWith(H,H1) = ∃ x,y:int × int, BelongsTo(H,x,y) ∧ BelongsTo(H1,x,y) 
		//EqualsTo(H,H1) = ∀ x,y:int × int, BelongsTo(H,x,y) = BelongsTo(H1,x,y) 
	}
	
		//[init]: 
	public void checkInit() {
		//PositionX(init(x,y)) = x PositionY(init(x,y)) = y 
		int x = 10;
		int y = 20;
		HitboxDecorator testH = new HitboxDecorator(null);
		testH.init(x, y);
		if (testH.getPositionX() != x) {
			System.out.println("Hitbox.checkInit() X");
		}
		if (testH.getPositionY() != y) {
			System.out.println("Hitbox.checkInit() Y");
		}
	}
	
		//[MoveTo]: 
	public void checkMoveTo() {
		//PositionX(MoveTo(H,x,y)) = x 
		//PositionY(MoveTo(H,x,y)) = y 
		//∀ u,v:int × int, BelongsTo(MoveTo(H,x,y),u,v) = Belongsto(H,u-(x-PositionX(H)),v-(y-PositionY(H))
	}
}
