package streetfighter.implem;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;
import streetfighter.services.CharacterService;
import streetfighter.services.DisplayService;
import streetfighter.services.EngineService;
import streetfighter.services.HitboxService;
import streetfighter.services.InputService;
import streetfighter.services.PlayerService;

public class Display extends BasicGame implements DisplayService, InputListener, InputService {

	private GameContainer container;
	private EngineService engine;
	private int width;
	private int height;
	
	private PlayerService p1, p2;
	private Image map;
	
	public Display() {
		super("ShitFighter CPS");
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
	        map = new Image("ressources/little.png");
	        container.setTargetFrameRate(60);
	        width = container.getWidth();
	        height = container.getHeight();
	        
	        container.setFullscreen(true);
	    }
	    
	    @Override
	    public void render(GameContainer container, Graphics g) throws SlickException {
	    	g.resetTransform();
	    	map.draw(0, 0, width, height);
	    	
	    	CharacterService c1 = engine.getCharacter(1);
	    	int x1 = c1.getPositionX();
	    	int y1 = c1.getPositionY();
	    	HitboxService b1 = c1.getcharBox();
	    	if (b1 instanceof HitboxRect) {
	    		HitboxRect br1 = (HitboxRect) b1;
	    		int h1 = br1.getHeight();
	    		int w1 = br1.getWidth();
	    		g.setColor(Color.red);
		        g.fillRect(x1, y1, w1, h1);
	    	}
	    	
	    	CharacterService c2 = engine.getCharacter(2);
	    	int x2 = c2.getPositionX();
	    	int y2 = c2.getPositionY();
	    	HitboxService b2 = c2.getcharBox();
	    	if (b2 instanceof HitboxRect) {
	    		HitboxRect br2 = (HitboxRect) b2;
	    		int h2 = br2.getHeight();
	    		int w2 = br2.getWidth();
	    		g.setColor(Color.blue);
		        g.fillRect(x2, y2, w2, h2);
	    	}
	    	
	    }

	    @Override
	    public void update(GameContainer container, int delta) throws SlickException {
	    	engine.step();
	    }

	    
	    @Override
		public void init(EngineService engine) {
			this.engine = engine;
			setPlayers(engine.getPlayer(1), engine.getPlayer(2));
			
			
			AppGameContainer container;
			try {
				container = new AppGameContainer(this);
				container.setDisplayMode(engine.getWidth(), engine.getHeight(), false); 
				container.start(); 
			} catch (SlickException e) {
				e.printStackTrace();
			} 
		}

	    @Override
        public void keyPressed(int key, char c) {
            p1.addInput(key);
            p2.addInput(key);
        }

        @Override
        public void keyReleased(int key, char c) {
            if (Input.KEY_ESCAPE == key) {
                container.exit();
            }
            p1.clearInput(key);
            p2.clearInput(key);
        }

		@Override
		public void setPlayers(PlayerService p1, PlayerService p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
}
