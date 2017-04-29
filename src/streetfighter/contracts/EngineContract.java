package streetfighter.contracts;

import streetfighter.condition.PreConditionError;
import streetfighter.data.CommandData;
import streetfighter.decorators.EngineDecorator;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.PlayerService;

public class EngineContract extends EngineDecorator {
	private int height;
	private int width;
	private int space;
	private boolean end;
	private PlayerService p1;
	private PlayerService p2;
	private CharacterService c1;
	private CharacterService c2;

	public EngineContract(EngineService delegate) {
		super(delegate);
	}

	@Override
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) throws PreConditionError {
		//pre init(h,w,s,p1,p2) requires h > 0 ∧ s > 0 ∧ w > s ∧ p1 6= p2 
		boolean test = h > 0 && w > 0 && s > 0 && w > s && p1 != p2;
		if(!test) {
			throw new PreConditionError("EngineDecorator.EngineDecorator()");
		}
		this.p1 = p1;
		this.p2 = p2;
		height = h;
		width = w;
		space = s;

		//height(init(h, w, s, p1, p2)) = h 
		//width(init(h, w, s, p1, p2)) = w 
		if(getHeight() != h || getWidth() != w) {
			throw new PreConditionError("EngineDecorator.init(h, w, s, p1, p2)");
		}
		//player(init(h, w, s, p1, p2), 1) = p1 
		//player(init(h, w, s, p1, p2), 2) = p2
		if(getPlayer(1) != p1 || getPlayer(2) != p2) {
			throw new PreConditionError("EngineDecorator.init(h, w, s, p1, p2)");
		}
		//Character ::positionX(char(init(h, w, s, p1, p2), 1)) = w//2 − s//2 
		//Character ::positionX(char(init(h, w, s, p1, p2), 2)) = w//2 + s//2 
		if(c1.getPositionX() != w/2 - s/2 || c2.getPositionX() != w/2 + s/2) {
			throw new PreConditionError("EngineDecorator.init(h, w, s, p1, p2)");
		}
		//Character ::positionY(char(init(h, w, s, p1, p2), 1)) = 0 
		//Character ::positionY(char(init(h, w, s, p1, p2), 2)) = 0 
		if(c1.getPositionY() != 0 || c2.getPositionY() != 0) {
			throw new PreConditionError("EngineDecorator.init(h, w, s, p1, p2)");
		}
		//Character ::faceRight(char(init(h, w, s, p1, p2), 1)) 
		//Character ::¬faceRight(char(init(h, w, s, p1, p2), 2))
		if(!c1.getFaceRight() || c2.getFaceRight()) {
			throw new PreConditionError("EngineDecorator.init(h, w, s, p1, p2)");
		}
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
	public CharacterService getCharacter(int i) throws PreConditionError {
		//pre player(E,i) requires i ∈ {1, 2} 
		if(i == 1) {
			return c1;
		} else if (i == 2) {
			return c2;
		} else {
			throw new PreConditionError("Engine.getCharacter(i)");
		}
	}

	@Override
	public PlayerService getPlayer(int i) throws PreConditionError {
		//pre player(E,i) requires i ∈ {1, 2} 
		if(i == 1) {
			return p1;
		} else if (i == 2) {
			return p2;
		} else {
			throw new PreConditionError("Engine.getPlayer(i)");
		}
	}

	@Override
	public boolean gameOver() {
		return end; 
	}


	//Operators: 
	@Override
	public void step(CommandData com1, CommandData com2) {
		//pre step(E) requires ¬gameOver(E)
		//char(step(E, C1, C2), 1) = step(char(E, 1), C1) 
		//char(step(E, C1, C2), 2) = step(char(E, 2), C2)
	}

	//Observations: 
	@Override
	public void checkInvariant() {
		//gameOver(E) = ∃i ∈ {1, 2} 
		//Character ::dead(player(E, i))
	}
}
