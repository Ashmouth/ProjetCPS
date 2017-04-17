package streetfighter.services;

import streetfighter.condition.PreConditionError;

public interface EngineService {

	public void init(int h, int w, int s, PlayerService p1, PlayerService p2);
	//pre init(h,w,s,p1,p2) requires h > 0 ∧ s > 0 ∧ w > s ∧ p1 6= p2 
	
	//Observators: 
	public int getHeight();
	public int getWidth();
	public CharacterService getCharacter(int i) throws PreConditionError;
	//pre player(E,i) requires i ∈ {1, 2} 
	public PlayerService getPlayer(int i) throws PreConditionError;
	//pre player(E,i) requires i ∈ {1, 2} 
	public boolean gameOver();

	//Operators: 
	public void step(CommandService com1, CommandService com2);
		//pre step(E) requires ¬gameOver(E)

	//Observations: 
	//[invariant]: 
	public void checkInvariant();
		//gameOver(E) = ∃i ∈ {1, 2} 
		//Character ::dead(player(E, i))

	//[init]:
	public void checkInit();
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

	//[step]:
	public void checkStep();
		//char(step(E, C1, C2), 1) = step(char(E, 1), C1) 
		//char(step(E, C1, C2), 2) = step(char(E, 2), C2)
	
}
