package streetfighter.services;

import streetfighter.data.CommandData;

public interface PlayerService {

	//Observators: 
	public CommandData getCommand();
	
	//Operators: 
	public void init(int num);
	
	//public void addInput(int key);
	//public void clearInput(int key);
	public void keyPressed(int key, char c);
	public void keyReleased(int key, char c);
}
