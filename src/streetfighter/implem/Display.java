package streetfighter.implem;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;

import streetfighter.services.DisplayService;
import streetfighter.services.EngineService;
import streetfighter.services.InputService;
import streetfighter.services.PlayerService;

public class Display extends BasicGame implements DisplayService, InputListener, InputService {

	private GameContainer container;
	private EngineService engine;
	
	private PlayerService p1, p2;
	
	public Display() {
		super("ShitFighter CPS");
		// TODO Auto-generated constructor stub
	}
	
	//Observators: 
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
		public void init(EngineService engine) {
			this.engine = engine;
			setPlayers(engine.getPlayer(1), engine.getPlayer(2));
		}

		@Override
		public void keyPressed(int k, char c) {
			p1.addInput(k);
			p2.addInput(k);
		}

		@Override
		public void keyReleased(int k, char c) {
			p1.clearInput(k);
			p2.clearInput(k);
		}

		@Override
		public void setPlayers(PlayerService p, PlayerService pp) {
			p1 = p;
			p2 = pp;
		}
}
