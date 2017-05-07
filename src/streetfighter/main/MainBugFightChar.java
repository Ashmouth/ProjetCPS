package streetfighter.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import streetfighter.contracts.EngineContract;
import streetfighter.contracts.FightCharContract;
import streetfighter.contracts.PlayerContract;
import streetfighter.implem.Display;
import streetfighter.implem.Engine;
import streetfighter.implem.Player;
import streetfighter.implembug.FightCharBug;
import streetfighter.services.DisplayService;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;
import streetfighter.services.PlayerService;

public class MainBugFightChar {

	public static void main(String[] args) {
		// Engine
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightCharBug());
		FightCharService c2 = new FightCharContract(new FightCharBug());
		
		c1.init(600, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		engine.init(screenSize.width, screenSize.height, 200, p1, p2, c1, c2);
		
		// Display
		DisplayService display = new Display();
		display.init(engine);
	}
}
