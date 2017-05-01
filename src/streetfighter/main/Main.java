package streetfighter.main;

import streetfighter.contracts.EngineContract;
import streetfighter.contracts.PlayerContract;
import streetfighter.implem.Display;
import streetfighter.implem.Engine;
import streetfighter.implem.Player;
import streetfighter.services.DisplayService;
import streetfighter.services.EngineService;
import streetfighter.services.PlayerService;

public class Main {

	public static void main(String[] args) {
		// Engine
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		p1.init(1);
		p2.init(2);
		engine.init(800, 600, 200, p1, p2);
		
		// Display
//		DisplayService display = new Display();
//		display.initEngine(engine);
//		AppGameContainer container = new AppGameContainer((Game) display); 
//	    container.setDisplayMode(800,600,false); 
//	    container.start(); 
	}
}
