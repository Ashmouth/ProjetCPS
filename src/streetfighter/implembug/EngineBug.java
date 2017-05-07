package streetfighter.implembug;

import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.PlayerService;

public class EngineBug implements EngineService {
	private int height;
	private int width;
	private boolean end;
	private PlayerService p1;
	private PlayerService p2;
	private CharacterService c1;
	private CharacterService c2;

	@Override
	public void init(int w, int h, int s, PlayerService p1, PlayerService p2, CharacterService c1, CharacterService c2) {
		this.p1 = p1;
		this.p2 = p2;
		this.c1 = c1;
		this.c2 = c2;
		height = h;
		width = w;
		
		c1.setPosition(width/2 - s/2, 0);
		c2.setPosition(width/2 + s/2, 0);
	}

	//Observators: 
	@Override
	public int getHeight() {
		return height; 
	}

	@Override
	public int getWidth() {
		return width; 
	}

	@Override
	public CharacterService getCharacter(int i) {
		if(i == 1) {
			return c1;
		} else if (i == 2) {
			return c2;
		}
		return null;
	}

	@Override
	public PlayerService getPlayer(int i) {
		//pre player(E,i) requires i ∈ {1, 2} 
		if(i == 1) {
			return p1;
		} else if (i == 2) {
			return p2;
		}
		return null;
	}

	@Override
	public boolean gameOver() {
		return c1.isDead() || c2.isDead();
	}


	//Operators: 
	@Override
	public void step() {

		boolean c1gc2 = c1.getPositionX() < c2.getPositionX();
		c1.step(p1.getCommand());
		if(!gameOver()) { /* c1.step a pu tuer c2 */
			c2.step(p2.getCommand());
		}

		// changement de direction
		if((c1.getPositionX() < c2.getPositionX()) != c1gc2) {
			c1.switchSide();
			c2.switchSide();
		}

	}

	//Observations: 
}
