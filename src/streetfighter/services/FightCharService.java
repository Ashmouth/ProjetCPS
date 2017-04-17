package streetfighter.services;

import streetfighter.condition.PreConditionError;

public interface FightCharService extends CharacterService {
	

	//Observators: 
	public boolean isBlocking();
	public boolean isBlockstunned();
	public boolean isHitstunned();
	public boolean isTeching();
	public TechService tech() throws PreConditionError;
	public boolean techFrame() throws PreConditionError;
	public boolean techHasAlreadyHit() throws PreConditionError;

	//Operators: 
	public void startTech(TechService tech) throws PreConditionError;

	//Observation: ...
}
