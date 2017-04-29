package streetfighter.contracts;

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
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		checkInvariants();
		
		/** PRECONDITIONS **/
		//pre init(h,w,s,p1,p2) requires h > 0 ∧ s > 0 ∧ w > s ∧ p1 6= p2 
		boolean test = h > 0 && w > 0 && s > 0 && w > s && p1 != p2;
		if(!test) {
			throw new PreConditionError("EngineContract.init()");
		}
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		super.init(h, w, s, p1, p2);
		
		/** POSTCONDITIONS **/
		
		//height(init(h, w, s, p1, p2)) = h 
		//width(init(h, w, s, p1, p2)) = w 
		if(getHeight() != h || getWidth() != w) {
			throw new PreConditionError("EngineContract.init()");
		}
		//player(init(h, w, s, p1, p2), 1) = p1 
		//player(init(h, w, s, p1, p2), 2) = p2
		if(getPlayer(1) != p1 || getPlayer(2) != p2) {
			throw new PreConditionError("EngineContract.init()");
		}
		//Character ::positionX(char(init(h, w, s, p1, p2), 1)) = w//2 − s//2 
		//Character ::positionX(char(init(h, w, s, p1, p2), 2)) = w//2 + s//2 
		if(getCharacter(1).getPositionX() != w/2 - s/2 || getCharacter(2).getPositionX() != w/2 + s/2) {
			throw new PreConditionError("EngineContract.init()");
		}
		//Character ::positionY(char(init(h, w, s, p1, p2), 1)) = 0 
		//Character ::positionY(char(init(h, w, s, p1, p2), 2)) = 0 
		if(getCharacter(1).getPositionY() != 0 || getCharacter(2).getPositionY() != 0) {
			throw new PreConditionError("EngineContract.init()");
		}
		//Character ::faceRight(char(init(h, w, s, p1, p2), 1)) 
		//Character ::¬faceRight(char(init(h, w, s, p1, p2), 2))
		if(!getCharacter(1).getFaceRight() || getCharacter(2).getFaceRight()) {
			throw new PreConditionError("EngineContract.init()");
		}
		
		checkInvariants();
	}

	//Observators: 
	@Override
	public int getHeight() {
		return super.getHeight(); 
	}

	@Override
	public int getWidth() {
		return super.getWidth(); 
	}

	@Override
	public CharacterService getCharacter(int i) {
		
		checkInvariants();
		
		/** PRECONDITIONS **/
		//pre character(E,i) requires i ∈ {1, 2} 
		if(i != 2 && i != 1) {
			throw new PreConditionError("EngineContract.getCharacter()");
		}
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		CharacterService cs= super.getCharacter(i);
		
		/** POSTCONDITIONS **/
	
		
		checkInvariants();
		
		return cs;
	}

	@Override
	public PlayerService getPlayer(int i) throws PreConditionError {
		checkInvariants();
		
		/** PRECONDITIONS **/
		//pre player(E,i) requires i ∈ {1, 2} 
		if(i != 2 && i != 1) {
			throw new PreConditionError("EngineContract.getPlayer()");
		}
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		PlayerService cs= super.getPlayer(i);
		
		/** POSTCONDITIONS **/
		// aucune
		
		
		checkInvariants();
		
		return cs;
	}

	@Override
	public boolean gameOver() {
		return super.gameOver(); 
	}


	//Operators: 
	@Override
	public void step(CommandData com1, CommandData com2) {
		//pre step(E) requires ¬gameOver(E)
		//char(step(E, C1, C2), 1) = step(char(E, 1), C1) 
		//char(step(E, C1, C2), 2) = step(char(E, 2), C2)
		
		checkInvariants();
		
		/** PRECONDITIONS **/
		
		
		/** CAPTURES **/
		
		/** DELEGATE **/
		super.step(com1, com2);
		
		/** POSTCONDITIONS **/
		// aucune
		
		
		checkInvariants();
	}

	//Observations: 
	public void checkInvariants() {
		//gameOver(E) = ∃i ∈ {1, 2} 
		//Character ::dead(player(E, i))
	}
}
