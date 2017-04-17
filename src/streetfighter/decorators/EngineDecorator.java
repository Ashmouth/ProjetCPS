package streetfighter.decorators;

import streetfighter.condition.PreConditionError;
import streetfighter.services.EngineService;

public class EngineDecorator implements EngineService {
	private int height;
	private int width;
	private int space;
	private boolean end;
	private PlayerDecorator p1;
	private PlayerDecorator p2;
	private CharacterDecorator c1;
	private CharacterDecorator c2;
	private CommandDecorator com;

	public EngineDecorator(int h, int w, int s, PlayerDecorator p1, PlayerDecorator p2) {
		//pre init(h,w,s,p1,p2) requires h > 0 ∧ s > 0 ∧ w > s ∧ p1 6= p2 
		boolean test = h > 0 && w > 0 && s > 0 && w > s && p1 != p2;
		if(!test) {
			throw new PreConditionError("msg");
		}
		this.p1 = p1;
		this.p2 = p2;
		height = h;
		width = w;
		space = s;
	}

	//Observators: 
	public int getHeight() {
		return height; 
	}

	public int getWidth() {
		return width; 
	}

	@Override
	public CharacterDecorator getCharacter(int i) {
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
	public PlayerDecorator getPlayer(int i) {
		//pre player(E,i) requires i ∈ {1, 2} 
		if(i == 1) {
			return p1;
		} else if (i == 2) {
			return p2;
		} else {
			throw new PreConditionError("Engine.getPlayer(i)");
		}
	}

	public boolean gameOver() {
		return end; 
	}


	//Operators: 
	public void step(CommandDecorator com1, CommandDecorator com2) {
		//pre step(E) requires ¬gameOver(E)
	}

	//Observations: 
	//[invariant]: 
	public void checkInvariant() {
		//gameOver(E) = ∃i ∈ {1, 2} 
		//Character ::dead(player(E, i))
	}

	//[init]:
	public void checkInit() {
		/*
			height(init(h, w, s, p1, p2)) = h 
			width(init(h, w, s, p1, p2)) = w 
			player(init(h, w, s, p1, p2), 1) = p1 
			player(init(h, w, s, p1, p2), 2) = p2
			Character ::positionX(char(init(h, w, s, p1, p2), 1)) = w//2 − s//2 
			Character ::positionX(char(init(h, w, s, p1, p2), 2)) = w//2 + s//2 
			Character ::positionY(char(init(h, w, s, p1, p2), 1)) = 0 
			Character ::positionY(char(init(h, w, s, p1, p2), 2)) = 0 
			Character ::faceRight(char(init(h, w, s, p1, p2), 1)) 
			Character ::¬faceRight(char(init(h, w, s, p1, p2), 2)) 
		 */
	}

	//[step]:
	public void checkStep() {
		//char(step(E, C1, C2), 1) = step(char(E, 1), C1) 
		//char(step(E, C1, C2), 2) = step(char(E, 2), C2)
	}
}
