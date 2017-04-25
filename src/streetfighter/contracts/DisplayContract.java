package streetfighter.contracts;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import streetfighter.decorators.DisplayDecorator;
import streetfighter.services.DisplayService;

public class DisplayContract extends DisplayDecorator implements DisplayService {
	
    private GameContainer container;

	public DisplayContract(DisplayService delegate) {
        super(delegate, "StreetFighterCPS");
    }
	
	//Observators: 
	@Override
	public GameContainer getContainer() {
		return container;
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
	
    
    
	public static void main(String[] args) throws SlickException {
        DisplayService delegate = null;
		new AppGameContainer(new DisplayContract(delegate), 640, 480, false).start();
    }	
}
