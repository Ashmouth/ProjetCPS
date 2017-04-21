package streetfighter.services;

public interface HitboxRectService extends HitboxService {
	
	public void init(int x, int y, int w, int h);
		//PositionX(init(x,y)) = x PositionY(init(x,y)) = y 
	
	//Observators: 
	public int getHeight();
	public int getWidth();
		
	//Operators: 
	public void resize(int w, int h);
}
