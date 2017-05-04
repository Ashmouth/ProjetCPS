package streetfighter.contracts;

import streetfighter.condition.InvariantError;
import streetfighter.condition.PostConditionError;
import streetfighter.condition.PreConditionError;
import streetfighter.data.CommandData;
import streetfighter.decorators.EngineDecorator;
import streetfighter.services.CharacterService;
import streetfighter.services.EngineService;
import streetfighter.services.PlayerService;

public class EngineContract extends EngineDecorator {
	
	public EngineContract(EngineService delegate) {
		super(delegate);
	}	

	@Override
	public void init(int w, int h, int s, PlayerService p1, PlayerService p2, CharacterService c1, CharacterService c2) {
//		checkInvariants();
		
		/** PRECONDITIONS **/
		// init(w,h,s,p1,p2) requires h > 0 ∧ s > 0 ∧ w > s ∧ p1 != p2 ∧ c1 != c2
		boolean test = h > 0 && w > 0 && s > 0 && w > s && p1 != p2 && c1 != c2;
		if(!test) {
			throw new PreConditionError("EngineContract.init.args");
		}
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		delegate.init(w, h, s, p1, p2, c1, c2);
		
		/** POSTCONDITIONS **/
		
		//height(init(w, h, s, p1, p2)) = h
		//width(init(w, h, s, p1, p2)) = w 
		if(getHeight() != h || getWidth() != w) {
			throw new PostConditionError("EngineContract.init.dimensions");
		}
		//player(init(w, h, s, p1, p2), 1) = p1 
		//player(init(w, h, s, p1, p2), 2) = p2
		if(getPlayer(1) != p1 || getPlayer(2) != p2) {
			throw new PostConditionError("EngineContract.init.players");
		}
		
		//
		if(getCharacter(1) == null || getCharacter(2) == null) {
			throw new PostConditionError("EngineContract.init.characters");
		}
		
		
		//Character ::positionX(char(init(h, w, s, p1, p2), 1)) = w//2 − s//2 
		//Character ::positionX(char(init(h, w, s, p1, p2), 2)) = w//2 + s//2 
		if(getCharacter(1).getPositionX() != w/2 - s/2 || getCharacter(2).getPositionX() != w/2 + s/2) {
			throw new PostConditionError("EngineContract.init.playersPosX");
		}
		//Character ::positionY(char(init(h, w, s, p1, p2), 1)) = 0 
		//Character ::positionY(char(init(h, w, s, p1, p2), 2)) = 0 
		if(getCharacter(1).getPositionY() != 0 || getCharacter(2).getPositionY() != 0) {
			throw new PostConditionError("EngineContract.init.playersPosY");
		}
		//Character ::faceRight(char(init(h, w, s, p1, p2), 1)) 
		//Character ::¬faceRight(char(init(h, w, s, p1, p2), 2))
		if(!getCharacter(1).getFaceRight() || getCharacter(2).getFaceRight()) {
			throw new PostConditionError("EngineContract.init()");
		}
		
		checkInvariants();
	}

	//Observators: 
	@Override
	public int getHeight() {
		return delegate.getHeight(); 
	}

	@Override
	public int getWidth() {
		return delegate.getWidth(); 
	}

	@Override
	public CharacterService getCharacter(int i) {
		
		checkInvariants();
		
		/** PRECONDITIONS **/
		// character(E,i) requires i ∈ {1, 2} 
		if(i != 2 && i != 1) {
			throw new PreConditionError("EngineContract.getCharacter.arg");
		}
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		CharacterService cs= delegate.getCharacter(i);
		
		/** POSTCONDITIONS **/
	
		
		checkInvariants();
		
		return cs;
	}

	@Override
	public PlayerService getPlayer(int i) throws PreConditionError {
		checkInvariants();
		
		/** PRECONDITIONS **/
		// player(E,i) requires i ∈ {1, 2} 
		if(i != 2 && i != 1) {
			throw new PreConditionError("EngineContract.getPlayer.arg");
		}
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		PlayerService cs= delegate.getPlayer(i);
		
		/** POSTCONDITIONS **/
				
		checkInvariants();
		
		return cs;
	}

	@Override
	public boolean gameOver() {
		return delegate.gameOver(); 
	}


	//Operators: 
	@Override
	public void step() {		
		checkInvariants();
		
		/** PRECONDITIONS **/
		// step(E) requires ¬gameOver(E)
		
		if (gameOver()) {
			throw new PreConditionError("EngineContract.step.gameOver");
		}		
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		delegate.step();
		
		/** POSTCONDITIONS **/
		//TODO
		// char(step(E, C1, C2), 1) = step(char(E, 1), C1) 
		// char(step(E, C1, C2), 2) = step(char(E, 2), C2)
		
		checkInvariants();
	}

	//Observations: 
	public void checkInvariants() {
		//gameOver(E) = ∃i ∈ {1, 2} tq Character ::dead(char(E, i))
		if (gameOver() && !getCharacter(1).isDead() && !getCharacter(2).isDead())	{
			throw new InvariantError("EngineContract.invariant.gameOver");
		}
		
	}
}
