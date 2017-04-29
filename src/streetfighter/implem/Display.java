package streetfighter.implem;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import streetfighter.services.DisplayService;
import streetfighter.services.EngineService;

public class Display implements DisplayService {

	private GameContainer container;
	private EngineService engine;
	
	//Observators: 
		@Override
		public GameContainer getContainer() {
			return container;
		}
		
		@Override
		public EngineService getEngine() {
			return engine;
		}

		//Operators: 
	    @Override
	    public void init(GameContainer container) throws SlickException {
	        this.container = container;
	    }
	    
	    @Override
	    public void render(GameContainer container, Graphics g) throws SlickException {
	    }

	    @Override
	    public void update(GameContainer container, int delta) throws SlickException {
	    }
		
	    @Override
		public void keyReleased(int key, char c) {
	    	engine.getPlayer(1).step(key, c);
	    	engine.getPlayer(2).step(key, c);
	    }
	    
	    @Override
		public void initEngine(EngineService engine) {
			this.engine = engine;
		}
}
