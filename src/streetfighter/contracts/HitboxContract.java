package streetfighter.contracts;

import streetfighter.condition.PostConditionError;
import streetfighter.decorators.HitboxDecorator;
import streetfighter.services.HitboxService;

public class HitboxContract extends HitboxDecorator {
	
	private int pos_x, pos_y;
	
	public HitboxContract(HitboxService delegate) {
		super(delegate);
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
		checkInvariant(); 
		/* Capture du centre */
		boolean belongsTo_centre_at_pre = belongsTo(getPositionX(), getPositionY()); 
		/* Capture du centre + 100 */ 
		boolean belongsTo_centre_100_at_pre = belongsTo(getPositionX() + 100, getPositionY() + 100); 
		/* Capture d’un point absolu */
		int PositionX_at_pre = getPositionX();
		int PositionY_at_pre = getPositionY();
		boolean belongsTo_abs_at_pre = belongsTo(300, 0); 
		super.moveTo(x,y);
		checkInvariant();
		/* Test du centre */
		if(! belongsTo(getPositionX(), getPositionY()) == belongsTo_centre_at_pre) {
			throw new PostConditionError("HitboxContract.MoveTo(x, y)");
		} 
		/* Test du centre + 100 */
		if(! belongsTo(getPositionX() + 100, getPositionY() + 100) == belongsTo_centre_100_at_pre) {
			throw new PostConditionError("HitboxContract.MoveTo(x, y)");
		} 
		/* Test d’un point absolu */
		if(! belongsTo(300 + (x - PositionX_at_pre), 0 + (y - PositionY_at_pre)) == belongsTo_abs_at_pre) {
			throw new PostConditionError("HitboxContract.MoveTo(x, y)");
		} 
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
		HitboxContract testH = new HitboxContract(null);
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
