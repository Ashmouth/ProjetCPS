package streetfighter.test;

import static org.junit.Assert.assertTrue;
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
		
		assertTrue(engine.getHeight() == height);
		
		assertTrue(engine.getWidth() == width);
		
		assertTrue(engine.getCharacter(1) == c1);

		assertTrue(engine.getCharacter(2) == c2);

		assertTrue(engine.getPlayer(1) == p1);

		assertTrue(engine.getPlayer(2) == p2);
		
		assertTrue(!engine.gameOver());
	}
		
	@Test
	public void gameOver() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int l = 600;
		int l2 = 150;
		
		c1.init(l, 4, true, engine); // tank
		c2.init(l2, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		int width = 800;
		int height = 600;
		
		engine.init(width, height, 200, p1, p2, c1, c2);
		
		assertTrue(!engine.gameOver());
		
		c2.damaged(l2/2);
		
		assertTrue(!engine.gameOver());
		
		c1.damaged(l);
		
		assertTrue(engine.gameOver());
	}

	@Test
	public void step() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int l = 600;
		int l2 = 150;
		
		c1.init(l, 4, true, engine); // tank
		c2.init(l2, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		int width = 800;
		int height = 600;
		
		engine.init(width, height, 200, p1, p2, c1, c2);
		engine.step();
	}
	
}
