package streetfighter.services;

public interface HitboxRectService extends HitboxService {
	
	public void initRect(int x, int y, int h, int w);
		//PositionX(init(x,y)) = x PositionY(init(x,y)) = y 
	
	//Observators: 
	public int getHeight();
	public int getWidth();
	public boolean collidesWith(HitboxRectService h);
	public boolean equalsTo(HitboxRectService h);
		
	//Operators: 
	
	//Observations: 
		//[invariant]: 
	public void checkInvariant();
		//CollidesWith(H,H1) = ∃ x,y:int × int, BelongsTo(H,x,y) ∧ BelongsTo(H1,x,y) 
		//EqualsTo(H,H1) = ∀ x,y:int × int, BelongsTo(H,x,y) = BelongsTo(H1,x,y) 	
}
