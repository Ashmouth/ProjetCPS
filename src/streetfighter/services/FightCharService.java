package streetfighter.services;

import streetfighter.data.TechData;

public interface FightCharService extends CharacterService {
	

	//Observators: 
	public boolean isBlocking();
	public boolean isBlockstunned();
	public boolean isHitstunned();
	public boolean isTeching();
	
	public void damaged(int degats, int hstun, int bstun);
	
	//TODO n'y a t'il pas une couille ici ??
	public TechData tech();
	//pre tech(C) requires isTeching(C) 
	public int techFrame();
	//pre techFrame(C) requires isTeching(C)
	public boolean techHasAlreadyHit();
	//pre techHasAlreadyHit(C) requires isTeching(C)

	//Operators: 
	public void startTech(TechData tech);
	//pre startTech(C,T) requires ¬isTeching(C) 
	public void guard();

	//Observation: ...
}
