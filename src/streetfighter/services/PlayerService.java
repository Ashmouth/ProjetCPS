package streetfighter.services;

import streetfighter.condition.PreConditionError;
import streetfighter.data.TechData;

public interface PlayerService {

	//Observators: 
	public TechData tech() throws PreConditionError;
}
