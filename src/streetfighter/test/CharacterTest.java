package streetfighter.test;

import static org.junit.Assert.*;

import org.junit.Test;

import streetfighter.contracts.CharacterContract;
import streetfighter.contracts.EngineContract;
import streetfighter.contracts.FightCharContract;
import streetfighter.data.CommandData;
import streetfighter.implem.Engine;
import streetfighter.implem.HitboxRect;
import streetfighter.implem.Character;
import streetfighter.services.EngineService;
import streetfighter.services.FightCharService;
import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;

public class CharacterTest {

	@Test
	public void init() {
		EngineService engine = new EngineContract(new Engine());
		Character c = new CharacterContract(new Character());
		int l = 600;
		int s = 3;
		int x = 0;
		int y = 0;
		int w = 90;
		int h = 150;
		
		c.init(l, s, true, engine);
		if(c.getLife() != l) {
			fail("CharacterTest getLife");
		}
		if(c.getSpeed() != s) {
			fail("CharacterTest getSpeed");
		}
		if(!c.getFaceRight()) {
			fail("CharacterTest FaceRight");
		}
		if(c.getPositionX() != x) {
			fail("CharacterTest getPositionX");
		}
		if(c.getPositionY() != y) {
			fail("CharacterTest getPositionY");
		}
		
		HitboxRectService hr = new HitboxRect();
		hr.init(x,y,w,h);
		if(c.getcharBox() != hr) {
			fail("CharacterTest getcharBox");
		}
		
		c.setPosition(x+10, y+10);
		if(c.getPositionX() != x+10) {
			fail("CharacterTest setPosition");
		}
		if(c.getPositionY() != y+10) {
			fail("CharacterTest setPosition");
		}
		
		
		c.switchSide();
		if(c.getFaceRight()) {
			fail("CharacterTest switchSide");
		}
		
		
		c.crouch();
		if(((HitboxRectService)(c.getcharBox())).getHeight() >= h) {
			fail("CharacterTest crouch");
		}
		
		if(!c.isCrouch()) {
			fail("CharacterTest isCrouch");
		}
		
		c.rise();
		if(((HitboxRectService)(c.getcharBox())).getHeight() != h) {
			fail("CharacterTest rise");
		}
		
		if(c.isCrouch()) {
			fail("CharacterTest isCrouch");
		}
		
		
		c.damaged(l/2);
		if(c.getLife() != l/2) {
			fail("CharacterTest damaged");
		}
		c.damaged(l/2);
		if(!c.isDead()) {
			fail("CharacterTest isDead");
		}
	}

	@Test
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void moveRight() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void step(CommandData c) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void jump() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void jumpRight() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void jumpLeft() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public boolean isJumpRightHigh() {
		// TODO Auto-generated method stub
		return false;
	}

	@Test
	public boolean isJumpLeftHigh() {
		// TODO Auto-generated method stub
		return false;
	}

	@Test
	public boolean isJumpHigh() {
		// TODO Auto-generated method stub
		return false;
	}
}
