package streetfighter.services;

import streetfighter.condition.PreConditionError;
import streetfighter.decorators.TechData;

public interface FightCharService extends CharacterService {
	

	//Observators: 
	public boolean isBlocking();
	public boolean isBlockstunned();
	public boolean isHitstunned();
	public boolean isTeching();
	public TechData tech() throws PreConditionError;
	//pre tech(C) requires isTeching(C) 
	public boolean techFrame() throws PreConditionError;
	//pre techFrame(C) requires isTeching(C)
	public boolean techHasAlreadyHit() throws PreConditionError;
	//pre techHasAlreadyHit(C) requires isTeching(C)

	//Operators: 
	public void startTech(TechData tech) throws PreConditionError;
	//pre startTech(C,T) requires ¬isTeching(C) 

	//Observation: ...
}
