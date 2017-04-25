package streetfighter.contracts;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.SlickException;

public class DisplayContract extends BasicGame {
	
    private GameContainer container;

	public DisplayContract() {
        super("StreetFighterCPS");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
    }
    
    public GameContainer getContainer() {
		return container;
	}

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }
	
	public static void main(String[] args) throws SlickException {
        new AppGameContainer(new DisplayContract(), 640, 480, false).start();
    }	
}
