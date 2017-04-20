package streetfighter.contracts;

import streetfighter.condition.InvariantError;
import streetfighter.condition.PostConditionError;
import streetfighter.condition.PreConditionError;
import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;

public class HitboxRectContract extends HitboxContract implements HitboxRectService {
	
	private int height, width;
	
	public HitboxRectContract(HitboxRectService delegate) {
		super(delegate);
	}
	
	public void initRect(int x, int y, int h, int w) {
		super.init(x, y);
		height = h;
		width = w;
		
		//height(init(x,y)) = h ^ width(init(x,y)) = w
		if (getHeight() != h) {
			throw new PreConditionError("Hitbox.init(x,y) X");
		}
		if (getWidth() != w) {
			throw new PreConditionError("Hitbox.init(x,y) Y");
		}
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
		return true;
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
	
		//[MoveTo]: 
	public void checkMoveTo() {
		int pos_x = getPositionX();
		int pos_y = getPositionY();
		moveTo(pos_x, pos_y);
		//PositionX(MoveTo(H,x,y)) = x
		if(getPositionX() != pos_x) {
			throw new InvariantError("HitboxContract.checkMoveTo()");
		}
		//PositionY(MoveTo(H,x,y)) = y 
		if(getPositionY() != pos_y) {
			throw new InvariantError("HitboxContract.checkMoveTo()");
		}
		int u = 10;
		int v = 15;
		//∀ u,v:int × int, BelongsTo(MoveTo(H,x,y),u,v) = Belongsto(H,u-(x-PositionX(H)),v-(y-PositionY(H))
		if(belongsTo(u,v) != belongsTo(u-(pos_x-getPositionX()),v-(pos_y-getPositionY()))) {
			throw new InvariantError("HitboxContract.checkMoveTo()");
		}
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
	public boolean equalsTo(HitboxService h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collidesWith(HitboxRectService h) {
		// TODO Auto-generated method stub
		return false;
	}
}
