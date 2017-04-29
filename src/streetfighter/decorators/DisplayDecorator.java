package streetfighter.decorators;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import streetfighter.services.DisplayService;
import streetfighter.services.EngineService;

public class DisplayDecorator extends BasicGame implements DisplayService {

	private DisplayService delegate;

	public DisplayDecorator(DisplayService delegate, String title) {
		super(title);
		this.delegate = delegate;
	}
	
	//Observators: 
	@Override
	public GameContainer getContainer() {
		return delegate.getContainer();
	}
	
	@Override
	public EngineService getEngine() {
		return delegate.getEngine();
	}

	//Operators: 
	@Override
	public void init(GameContainer arg0) throws SlickException {
		delegate.init(arg0);
	}
	
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		delegate.render(arg0, arg1);
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		delegate.update(arg0, arg1);
	}
	
	@Override
	public void keyReleased(int key, char c) {
		delegate.keyReleased(key, c);
    }

	@Override
	public void initEngine(EngineService engine) {
		delegate.initEngine(engine);
	}
}
