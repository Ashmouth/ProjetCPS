package streetfighter.services;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface DisplayService {

	//Observators: 
	public GameContainer getContainer();
	
	//Operators: 
	public void init(GameContainer arg0) throws SlickException;
	
	public void render(GameContainer arg0, Graphics arg1) throws SlickException;
	
	public void update(GameContainer arg0, int arg1) throws SlickException;
}
