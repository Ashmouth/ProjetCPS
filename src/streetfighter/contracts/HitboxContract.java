package streetfighter.contracts;

import streetfighter.condition.PostConditionError;
import streetfighter.decorators.HitboxDecorator;
import streetfighter.services.HitboxService;

public class HitboxContract extends HitboxDecorator {

	public HitboxContract(HitboxService delegate) {
		super(delegate);
	}

	public void init(int x, int y) {
		delegate.init(x, y);

		//PositionX(init(x,y)) = x PositionY(init(x,y)) = y
		if (getPositionX() != x) {
			throw new PostConditionError("Hitbox.init(x,y) X");
		}
		if (getPositionY() != y) {
			throw new PostConditionError("Hitbox.init(x,y) Y");
		}
	}

	//Observators: 
	public int getPositionX() {
		return delegate.getPositionX();
	}

	public int getPositionY() {
		return delegate.getPositionY();
	}

	public boolean belongsTo(int x, int y) {
		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/


		/** DELEGATE **/
		boolean ret = delegate.belongsTo(x,y);

		/** POSTCONDITIONS **/

		checkInvariants();
		return ret;
	}

	public boolean collidesWith(HitboxService h) {
		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/


		/** DELEGATE **/
		boolean ret = delegate.collidesWith(h);

		/** POSTCONDITIONS **/

		checkInvariants();
		return ret;
	}

	public boolean equalsTo(HitboxService h) {
		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/


		/** DELEGATE **/
		boolean ret = delegate.equalsTo(h);

		/** POSTCONDITIONS **/
		//EqualsTo(H,H1) = ∀ x,y:int × int, BelongsTo(H,x,y) = BelongsTo(H1,x,y)
		if (ret) { 
			for (int i = 0; i < 100; i++) {
				int x = (int) (getPositionX() + (Math.random()*300));
				int y = (int) (getPositionY() + (Math.random()*300));
				
				if (belongsTo(x, y) != h.belongsTo(x, y)) {
					throw new PostConditionError("HitboxContract.equalsTo");
				}
			}
		}

		checkInvariants();
		return ret;
	}

	//Operators: 
	@Override
	public void moveTo(int x, int y){ 

		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/
		/* Capture du centre */
		boolean belongsTo_centre_at_pre = belongsTo(getPositionX(), getPositionY()); 
		/* Capture du centre + 100 */ 
		boolean belongsTo_centre_100_at_pre = belongsTo(getPositionX() + 100, getPositionY() + 100); 
		/* Capture d’un point absolu */
		int PositionX_at_pre = getPositionX();
		int PositionY_at_pre = getPositionY();
		boolean belongsTo_abs_at_pre = belongsTo(300, 0); 

		/** DELEGATE **/
		delegate.moveTo(x,y);

		/** POSTCONDITIONS **/

		// ∀ u,v:int × int, BelongsTo(MoveTo(H,x,y),u,v) 
		//	  			  = Belongsto(H,u-(x-PositionX(H)),v-(y-PositionY(H))
		/* Test du centre */
		if(! belongsTo(getPositionX(), getPositionY()) == belongsTo_centre_at_pre) {
			throw new PostConditionError("HitboxContract.MoveTo(x, y) belongs");
		} 
		/* Test du centre + 100 */
		if(! belongsTo(getPositionX() + 100, getPositionY() + 100) == belongsTo_centre_100_at_pre) {
			throw new PostConditionError("HitboxContract.MoveTo(x, y) belongs");
		} 
		/* Test d’un point absolu */
		if(! belongsTo(300 + (x - PositionX_at_pre), 0 + (y - PositionY_at_pre)) == belongsTo_abs_at_pre) {
			throw new PostConditionError("HitboxContract.MoveTo(x, y) belongs");
		} 

		// PositionX(MoveTo(H,x,y)) = x 
		if (!(getPositionX() == x)) {
			throw new PostConditionError("HitboxContract.MoveTo(x, y) getPosX");
		}

		// PositionY(MoveTo(H,x,y)) = y 
		if (!(getPositionY() == y)) {
			throw new PostConditionError("HitboxContract.MoveTo(x, y) getPosY");
		}

		checkInvariants();
	}

	//Observations:
	public void checkInvariants() {
		//TODO
		//CollidesWith(H,H1) = ∃ x,y:int × int, BelongsTo(H,x,y) ∧ BelongsTo(H1,x,y) 
		//		if(collidesWith(H1) != (belongsTo(pos_x,pos_y) && H1.belongsTo(pos_x,pos_y))) {
		//			throw new InvariantError("HitboxContract.checkInvariant()");
		//		}
	}
}
