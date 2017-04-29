package streetfighter.implem;

import streetfighter.data.CommandData;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.PlayerService;

public class Engine implements EngineService {
	private int height;
	private int width;
	private int space;
	private boolean end;
	private PlayerService p1;
	private PlayerService p2;
	private CharacterService c1;
	private CharacterService c2;

	@Override
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		this.p1 = p1;
		this.p2 = p2;
		height = h;
		width = w;
		space = s;
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
		return end; 
	}


	//Operators: 
	@Override
	public void step(CommandData com1, CommandData com2) {
	}

	//Observations: 
	@Override
	public void checkInvariant() {
	}
}
