package streetfighter.services;

public interface FightCharService extends CharacterService {
	

	//Observators: 
	public boolean isBlocking();
	public boolean isBlockstunned();
	public boolean isHitstunned();
	public boolean isTeching();
	public TechService tech();
	public boolean techFrame();
	public boolean techHasAlreadyHit();

	//Operators: 
	public void startTech(TechService tech);

	//Observation: ...
}
