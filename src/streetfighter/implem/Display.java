package streetfighter.implem;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import streetfighter.data.TechData;
import streetfighter.services.CharacterService;
import streetfighter.services.DisplayService;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;
import streetfighter.services.HitboxRectService;
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
	    
	    /* draw a rectangle taking "normal" coordinates (like in math) */
	    protected void drawRect(int x, int y, int w, int h, Color c, Graphics g) {
	    	g.setColor(c);
			g.fillRect(x, height-(y+h), w, h);
	    }
	    
	    protected void drawChar(FightCharService f, Color col, Color bcol, Graphics g) {
	    	drawChar((CharacterService)f, (f.isBlocking() ? bcol : col), bcol, g);
	    	
	    	// draw technique
			if (f.isTeching()) {
				TechData tec = f.tech();
				HitboxRectService h = (HitboxRectService) tec.getHitbox(f.getPositionX(), f.getPositionY(), f.getFaceRight(), ((HitboxRectService)f.getcharBox()).getWidth());
				
				drawRect(h.getPositionX(), h.getPositionY(), h.getWidth(), h.getHeight(), Color.green, g);
			}
	    }
	    
	    protected void drawChar(CharacterService c, Color col, Color bcol, Graphics g) {
	    	HitboxService b = c.getcharBox();
	    	
	    	if (b instanceof HitboxRect) {
	    		HitboxRect br = (HitboxRect) b;
	    		int h = br.getHeight();
	    		int w = br.getWidth();
	    		
	    		drawRect(br.getPositionX(), br.getPositionY(), w, h, col, g);
	    	}
	    }
	    
	    @Override
	    public void render(GameContainer container, Graphics g) throws SlickException {
	    	g.resetTransform();
	    	map.draw(0, 0, width, height);
	    	
	    	if(!engine.gameOver()) {
		    	drawChar((FightCharService) engine.getCharacter(1), Color.blue, Color.cyan, g);
		    	drawChar((FightCharService) engine.getCharacter(2), Color.red, Color.magenta, g);
	    	} else {
	    		g.setColor(Color.blue);
		    	g.drawString("GAME OVER", width/2, height/2);
	    	}
	    	
	    	String l1 = ""+engine.getCharacter(1).getLife();
	    	g.setColor(Color.red);
	    	g.drawString(l1, width/2 - 20, 0);
	    	
	    	String l2 = ""+engine.getCharacter(2).getLife();
	    	g.setColor(Color.blue);
	    	g.drawString(l2, width/2 + 20, 0);
	    }

	    @Override
	    public void update(GameContainer container, int delta) throws SlickException {
	    	if(!engine.gameOver()) {
	    		engine.step();
	    	}
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
