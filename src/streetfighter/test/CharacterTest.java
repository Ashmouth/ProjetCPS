package streetfighter.test;

import static org.junit.Assert.*;

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

public class CharacterTest {

	@Test
	public void init() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		int l1 = 600;
		int l2 = 150;
		int s = 200;
		
		c1.init(l1, 4, true, engine); // tank
		c2.init(l2, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, s, p1, p2, c1, c2);
		assertTrue(c1.getLife() == l1);
		assertTrue(c2.getLife() == l2);
		assertTrue(c1.getFaceRight());
		assertTrue(!c2.getFaceRight());
	}

	@Test
	public void getcharBox() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		int x = 150;
		int y = 0;
		int l = 600;
		
		c1.init(l, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);
		
		c1.setPosition(x, y);
		c2.setPosition(x, y);
		assertTrue(c1.getcharBox().collidesWith(c2.getcharBox()));
	}

	@Test
	public void setPosition() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		int x = 150;
		int y = 0;
		
		c1.init(600, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);
		
		c1.setPosition(x+100, y);
		assertTrue(c1.getPositionX() == x+100);
		assertTrue(c1.getPositionY() == y);
	}

	@Test
	public void switchSide() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		int x = 150;
		int y = 0;
		
		c1.init(600, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);
		
		
		c1.setPosition(x-100, y);
		
		c2.setPosition(x, y);
		engine.step();
		
		assertTrue(c1.getFaceRight());
		assertTrue(!c2.getFaceRight());
		
		c1.setPosition(x+600, y);
		engine.step();
		c1.switchSide();
		c2.switchSide();
		engine.step();
		assertTrue(!c1.getFaceRight());
		assertTrue(c2.getFaceRight());
	}

	@Test
	public void crouch() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		
		c1.init(600, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);

		assertTrue(!c1.isCrouch());
		c1.crouch();
		assertTrue(c1.isCrouch());
	}

	@Test
	public void isCrouch() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		
		c1.init(600, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);

		assertTrue(!c1.isCrouch());
		c1.crouch();
		assertTrue(c1.isCrouch());
		c1.rise();
		assertTrue(!c1.isCrouch());
	}

	@Test
	public void rise() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		
		c1.init(600, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);

		assertTrue(!c1.isCrouch());
		c1.crouch();
		assertTrue(c1.isCrouch());
		c1.rise();
		assertTrue(!c1.isCrouch());
	}


	@Test
	public void damaged() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		int l = 600;
		
		c1.init(l, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);

		assertTrue(c1.getLife() == l);
		c1.damaged(l/2);
		assertTrue(c1.getLife() == l/2);
	}

	@Test
	public void isDead() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		int l = 600;
		
		c1.init(l, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);

		assertTrue(!c1.isDead());
		c1.damaged(l);
		assertTrue(c1.isDead());
	}

	@Test
	public void moveLeft() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		int l = 600;
		int x = 0;
		int y = 0;
		int w = 90;
		
		c1.init(l, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);
		
		c1.setPosition(x, y);
		
		c2.setPosition(x+w, y);
		engine.step();
		c2.moveLeft();
		engine.step();
		assertTrue(!c1.getcharBox().collidesWith(c2.getcharBox()));
	}

	@Test
	public void moveRight() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		int l = 600;
		int x = 0;
		int y = 0;
		int w = 90;
		
		c1.init(l, 4, true, engine); // tank
		c2.init(150, 10, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);
		
		c1.setPosition(x, y);
		
		c2.setPosition(x+w, y);
		engine.step();
		c1.moveRight();
		engine.step();
		assertTrue(!c1.getcharBox().collidesWith(c2.getcharBox()));
	}

	@Test
	public void step() {
		EngineService engine = new EngineContract(new Engine());
		PlayerService p1 = new PlayerContract(new Player());
		PlayerService p2 = new PlayerContract(new Player());
		
		FightCharService c1 = new FightCharContract(new FightChar());
		FightCharService c2 = new FightCharContract(new FightChar());
		
		int width = 800;
		int height = 600;
		int l = 600;
		int x = 0;
		int y = 0;
		int w = 90;
		int s1 = 4;
		int s2 = 10;
		
		c1.init(l, s1, true, engine); // tank
		c2.init(150, s2, false, engine); // squishy 
		
		p1.init(1);
		p2.init(2);
		
		engine.init(width, height, 200, p1, p2, c1, c2);

		engine.step();
		c1.moveRight();
		c2.moveLeft();
		engine.step();
		assertTrue(c1.getPositionX() != x);
		
	}
//
//	@Test
//	public void jump() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Test
//	public void jumpRight() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Test
//	public void jumpLeft() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Test
//	public boolean isJumpRightHigh() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Test
//	public boolean isJumpLeftHigh() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Test
//	public boolean isJumpHigh() {
//		// TODO Auto-generated method stub
//		return false;
//	}
}
