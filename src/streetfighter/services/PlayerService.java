package streetfighter.services;

import streetfighter.data.CommandData;

public interface PlayerService {

	//Observators: 
	public CommandData getCommand();
	
	//Operators: 
	public void init(int num);
	
	public void addInput(int k);
	public void clearInput(int k);
}
