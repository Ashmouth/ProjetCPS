package streetfighter.services;

import streetfighter.data.CommandData;

public interface PlayerService {

	//Observators: 
	public CommandData getCommand();
	
	//Operators: 
	public void init(int num);
	
	public CommandData getInput(int key, char c);
	
	public void step(int key, char c);
}
