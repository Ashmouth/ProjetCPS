package streetfighter.contracts;

import streetfighter.condition.PostConditionError;
import streetfighter.condition.PreConditionError;
import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;

public class HitboxRectContract extends HitboxContract implements HitboxRectService {
	
	public HitboxRectContract(HitboxRectService delegate) {
		super(delegate);
	}
	
	public void init(int x, int y, int w, int h) {
		((HitboxRectService)delegate).init(x, y, w, h);
		
		// PositionX(init(x,y,w,h)) = x
		// PositionY(init(x,y,w,h)) = y
		// Width(init(x,y,w,h)) = w
		// Height(init(x,y,w,h)) = h
		if (getPositionX() != x || getPositionY() != y || getWidth() != w || getHeight() != h) {
			throw new PostConditionError("HitboxRectContract.init");
		}
	}
	
	public boolean belongsTo(int x, int y) {
		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/


		/** DELEGATE **/
		boolean ret = super.belongsTo(x,y);

		/** POSTCONDITIONS **/

		checkInvariants();
		return ret;
	}

	public boolean collidesWith(HitboxService h) {
		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/


		/** DELEGATE **/
		boolean ret = ((HitboxRectService)delegate).collidesWith(h);

		/** POSTCONDITIONS **/

		checkInvariants();
		return ret;
	}

	public boolean equalsTo(HitboxService h) {
		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/


		/** DELEGATE **/
		boolean ret = ((HitboxRectService)delegate).equalsTo(h);

		/** POSTCONDITIONS **/

		checkInvariants();
		return ret;
	}
	
	//Observators:
	public int getHeight() {
		return ((HitboxRectService)delegate).getHeight();
	}

	public int getWidth() {
		return ((HitboxRectService)delegate).getWidth();
	}
	
	//Operators :
	
	@Override
	public void resize(int w, int h) {
		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/
		int x_pre = getPositionX();
		int y_pre = getPositionY();


		/** DELEGATE **/
		((HitboxRectService)delegate).resize(w,h);

		/** POSTCONDITIONS **/
		// Height(resize(H, w, h)) = h
		// Width(resize(H, w, h)) = w 
		if (getHeight() != h || getWidth() != w) {
			throw new PostConditionError("HitboxRectContract.resize.dimension");
		}
		
		// PositionX(resize(H, w, h)) = PositionX(H)
		// PositionY(resize(H, w, h)) = PositionY(H)
		if (x_pre != getPositionX() || y_pre != getPositionY()) {
			throw new PostConditionError("HitboxRectContract.resize.position");
		}

		checkInvariants();
	}
	
	@Override
	public void moveTo(int x, int y) {
		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/


		/** DELEGATE **/
		((HitboxRectService)delegate).moveTo(x,y);

		/** POSTCONDITIONS **/

		checkInvariants();
	}
	
	//Observations: 
		//[invariant]: 
	public void checkInvariants() {
		//TODO en dessous c'est pas bon je pense...
//		int pos_x = getPositionX();
//		int pos_y = getPositionY();
//		HitboxRectService delegate = null;
//		HitboxRectContract H1 = new HitboxRectContract(delegate);
//		H1.init(pos_x, pos_y);
//		
//		//CollidesWith(H,H1) = ∃ x,y:int × int, BelongsTo(H,x,y) ∧ BelongsTo(H1,x,y) 
//		if(collidesWith(H1) != (belongsTo(pos_x,pos_y) && H1.belongsTo(pos_x,pos_y))) {
//			throw new InvariantError("HitboxContract.checkInvariant()");
//		}
//		
//		//EqualsTo(H,H1) = ∀ x,y:int × int, BelongsTo(H,x,y) = BelongsTo(H1,x,y) 
//		if(equalsTo(H1) != (belongsTo(pos_x,pos_y) == H1.belongsTo(pos_x,pos_y))) {
//			throw new InvariantError("HitboxContract.checkInvariant()");
//		}
	}
}
