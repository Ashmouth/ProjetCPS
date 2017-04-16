package streetfighter.core;

public class Hitbox {
	
	private boolean test;
	private int pos_x, pos_y;
	
	public Hitbox(int x, int y) {
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
		
	public boolean collidesWith(Hitbox h) {
		//Not possible without width and height
		return true;
	}
	
	public boolean equalsTo(Hitbox h) {
		if(getPositionX() == h.getPositionX() && getPositionY() == h.getPositionY()) {
			return true;
		}
		return false;
	}
		
	//Operators: 
	public void moveTo(int x, int y) {
		checkInvariant(); 
		/* Capture du centre */
		int old_x = getPositionX();
		int old_y = getPositionY();
		boolean oldbelongs = belongsTo(getPositionX(), getPositionY()); 
		if (!oldbelongs) {
			System.out.println("Hitbox.moveTo() oldbelongs");
		}
		
		/* Capture du centre + 100 */
		boolean newbelongs = belongsTo(getPositionX() + 100, getPositionY() + 100); 
		if (newbelongs) {
			System.out.println("Hitbox.moveTo() newbelongs");
		}
		
		/* Capture d’un point absolu */
		int new_x = getPositionX();
		int new_y = getPositionY();
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
		Hitbox testH = new Hitbox(x, y);
		if (testH.getPositionX() != x) {
			System.out.println("Hitbox.checkInit() X");
		}
		if (testH.getPositionY() != y) {
			System.out.println("Hitbox.checkInit() Y");
		}
	}
	
		//[MoveTo]: 
	public void checkMove() {
		//PositionX(MoveTo(H,x,y)) = x 
		//PositionY(MoveTo(H,x,y)) = y 
		//∀ u,v:int × int, BelongsTo(MoveTo(H,x,y),u,v) = Belongsto(H,u-(x-PositionX(H)),v-(y-PositionY(H))
	}
	public void checkMove(int x, int y) {
		checkInvariant(); 
		/* Capture du centre */
		int old_x = getPositionX();
		int old_y = getPositionY();
		boolean oldbelongs = belongsTo(getPositionX(), getPositionY()); 
		if (!oldbelongs) {
			System.out.println("Hitbox.moveTo() oldbelongs");
		}

		/* Capture du centre + 100 */
		boolean bugbelongs = belongsTo(getPositionX() + 100, getPositionY() + 100); 
		if (bugbelongs) {
			System.out.println("Hitbox.moveTo() bugbelongs");
		}

		/* Capture d’un point absolu */
		moveTo(x, y);
		int new_x = getPositionX();
		int new_y = getPositionY();
		if (new_x != x) {
			System.out.println("Hitbox.checkMove() X");
		}
		if (new_y != y) {
			System.out.println("Hitbox.checkMove() Y");
		}
	}
}
