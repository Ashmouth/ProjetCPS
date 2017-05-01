package streetfighter.contracts;

import streetfighter.condition.PreConditionError;
import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;

public class HitboxRectContract extends HitboxContract implements HitboxRectService {
	
	public HitboxRectContract(HitboxRectService delegate) {
		super(delegate);
	}
	
	public void init(int x, int y) {
		//TODO
		delegate.init(x,y);
	}
	
	public void init(int x, int y, int w, int h) {
		((HitboxRectService)delegate).init(x, y, w, h);
		
		//height(init(x,y)) = h ^ width(init(x,y)) = w
		if (getHeight() != h) {
			throw new PreConditionError("Hitbox.init(x,y) X");
		}
		if (getWidth() != w) {
			throw new PreConditionError("Hitbox.init(x,y) Y");
		}
	}
	
	public boolean belongsTo(int x, int y) {
		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/


		/** DELEGATE **/
		boolean ret = ((HitboxRectService)delegate).belongsTo(x,y);

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


		/** DELEGATE **/
		((HitboxRectService)delegate).resize(w,h);

		/** POSTCONDITIONS **/

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
