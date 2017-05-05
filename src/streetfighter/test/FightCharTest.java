package streetfighter.test;

import org.junit.Before;
import org.junit.Test;

import streetfighter.contracts.EngineContract;
import streetfighter.contracts.FightCharContract;
import streetfighter.data.TechData;
import streetfighter.implem.Engine;
import streetfighter.implem.FightChar;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;

public class FightCharTest {

	@Before
	public void initTest() {
		EngineService engine = new EngineContract(new Engine());
		FightCharService c = new FightCharContract(new FightChar());
		c.init(600, 4, true, engine); // tank
		
	}

}
