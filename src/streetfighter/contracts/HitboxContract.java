package streetfighter.contracts;

import streetfighter.condition.PostConditionError;
import streetfighter.decorators.HitboxDecorator;
import streetfighter.services.HitboxService;

public class HitboxContract extends HitboxDecorator {

	public HitboxContract(HitboxService delegate) {
		super(delegate);
	}

	public void init(int x, int y) {
		super.init(x, y);

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
		return super.getPositionX();
	}

	public int getPositionY() {
		return super.getPositionY();
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
		boolean ret = super.collidesWith(h);

		/** POSTCONDITIONS **/

		checkInvariants();
		return ret;
	}

	public boolean equalsTo(HitboxService h) {
		checkInvariants();

		/** PRECONDITIONS **/


		/** CAPTURES **/


		/** DELEGATE **/
		boolean ret = super.equalsTo(h);

		/** POSTCONDITIONS **/

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
		super.moveTo(x,y);

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

	@Override
	public void setPositionX(int x) {
		super.setPositionX(x);
	}

	@Override
	public void setPositionY(int y) {
		super.setPositionY(y);
	}

	//Observations:
	public void checkInvariants() {
		//		HitboxService delegate = null;
		//		HitboxContract H1 = new HitboxContract(delegate);

		//TODO
		//CollidesWith(H,H1) = ∃ x,y:int × int, BelongsTo(H,x,y) ∧ BelongsTo(H1,x,y) 
		//		if(collidesWith(H1) != (belongsTo(pos_x,pos_y) && H1.belongsTo(pos_x,pos_y))) {
		//			throw new InvariantError("HitboxContract.checkInvariant()");
		//		}

		//TODO
		//EqualsTo(H,H1) = ∀ x,y:int × int, BelongsTo(H,x,y) = BelongsTo(H1,x,y) 
		//		if(equalsTo(H1) != (belongsTo(pos_x,pos_y) == H1.belongsTo(pos_x,pos_y))) {
		//			throw new InvariantError("HitboxContract.checkInvariant()");
		//		}
	}
}
