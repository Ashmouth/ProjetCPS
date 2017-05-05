package streetfighter.test;

import org.junit.Test;

import streetfighter.contracts.EngineContract;
import streetfighter.contracts.FightCharContract;
import streetfighter.data.TechData;
import streetfighter.implem.Engine;
import streetfighter.implem.FightChar;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;

public class FightCharTest {

	@Test
	public void init() {
		EngineService engine = new EngineContract(new Engine());
		FightCharService c = new FightCharContract(new FightChar());
		c.init(600, 4, true, engine); // tank
		
	}
	
	@Test
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Test
	public boolean isBlockstunned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Test
	public boolean isHitstunned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Test
	public boolean isTeching() {
		// TODO Auto-generated method stub
		return false;
	}

	@Test
	public TechData tech() {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	public boolean techFrame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Test
	public boolean techHasAlreadyHit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Test
	public void startTech(TechData tech) {
		// TODO Auto-generated method stub
		
	}

}
