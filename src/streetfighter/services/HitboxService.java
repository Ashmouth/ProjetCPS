package streetfighter.services;

public interface HitboxService {

	public void init(int x, int y);
		//PositionX(init(x,y)) = x PositionY(init(x,y)) = y 
	
	//Observators: 
	public int getPositionX();
	public int getPositionY();
	public boolean belongsTo(int x, int y);
	public boolean collidesWith(HitboxService h);
	public boolean equalsTo(HitboxService h);
		
	//Operators: 
	public void moveTo(int x, int y);
	
	//Observations: 
		//[invariant]: 
	public void checkInvariant();
		//CollidesWith(H,H1) = ∃ x,y:int × int, BelongsTo(H,x,y) ∧ BelongsTo(H1,x,y) 
		//EqualsTo(H,H1) = ∀ x,y:int × int, BelongsTo(H,x,y) = BelongsTo(H1,x,y) 
	
		//[MoveTo]: 
	public void checkMoveTo();
		//PositionX(MoveTo(H,x,y)) = x 
		//PositionY(MoveTo(H,x,y)) = y 
		//∀ u,v:int × int, BelongsTo(MoveTo(H,x,y),u,v) = Belongsto(H,u-(x-PositionX(H)),v-(y-PositionY(H))

	public void setPositionX(int x);
		//∀ x:int, x <= 0
	
	public void setPositionY(int y);
		//∀ y:int, y <= 0
}
