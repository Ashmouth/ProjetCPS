package streetfighter.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import streetfighter.contracts.EngineContract;
import streetfighter.contracts.FightCharContract;
import streetfighter.contracts.PlayerContract;
import streetfighter.implem.Engine;
import streetfighter.implem.FightChar;
import streetfighter.implem.Player;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;
import streetfighter.services.PlayerService;

public class EngineTest {

	@Test
	public void init() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int l = 600;
		
		c1.init(l, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		int width = 800;
		int height = 600;
		
		engine.init(width, height, 200, p1, p2, c1, c2);
		
		if(engine.getHeight() != width) {
			fail("EngineTest getHeight");
		}
		
		if(engine.getWidth() != height) {
			fail("EngineTest getWidth");
		}
		

		if(engine.getCharacter(1) != c1) {
			fail("EngineTest getCharacter");
		}

		if(engine.getCharacter(2) != c2) {
			fail("EngineTest getCharacter");
		}

		if(engine.getPlayer(1) != p1) {
			fail("EngineTest getPlayer");
		}

		if(engine.getPlayer(2) != p2) {
			fail("EngineTest getPlayer");
		}
		
		
		if(engine.gameOver()) {
			fail("EngineTest gameOver");
		}
		
		c1.damaged(l);
		
		if(!engine.gameOver()) {
			fail("EngineTest gameOver");
		}
	}

	@Test
	public void step() {
		// TODO Auto-generated method stub
		
	}
	
}
