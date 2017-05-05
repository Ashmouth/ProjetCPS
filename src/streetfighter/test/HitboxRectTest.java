package streetfighter.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import streetfighter.implem.HitboxRect;

public class HitboxRectTest {

	@Before
	public void init() {
//		HitboxRect hr = new HitboxRect();
//		int x = 0;
//		int y = 0;
//		int w = 0;
//		int h = 0;
//		hr.init(x, y, w, h);
//		
//		if(hr.getPositionX() != x) {
//			fail("CharacterTest getPositionX");
//		}
//		if(hr.getPositionY() != y) {
//			fail("CharacterTest getPositionX");
//		}
//		if(hr.getWidth() != w) {
//			fail("CharacterTest getWidth");
//		}
//		if(hr.getHeight() != h) {
//			fail("CharacterTest getHeight");
//		}
//		
//		hr.moveTo(x+10, y+10);
//		if(hr.getPositionX() != x+10) {
//			fail("CharacterTest moveTo");
//		}
//		if(hr.getPositionY() != y+10) {
//			fail("CharacterTest moveTo");
//		}
//		if(hr.getWidth() != w) {
//			fail("CharacterTest moveTo");
//		}
//		if(hr.getHeight() != h) {
//			fail("CharacterTest moveTo");
//		}
//			
//		hr.moveTo(x, y);
//		
//		hr.resize(w/2, h/2);
//		if(hr.getPositionX() != x) {
//			fail("CharacterTest resize");
//		}
//		if(hr.getPositionY() != y) {
//			fail("CharacterTest resize");
//		}
//		if(hr.getWidth() != w/2) {
//			fail("CharacterTest resize");
//		}
//		if(hr.getHeight() != h/2) {
//			fail("CharacterTest resize");
//		}
//		
//		for(int i = 0; i < w/2; i++) {
//			for(int j = 0; j < h/2; j++) {
//				if(!hr.belongsTo(i, j)) {
//					fail("CharacterTest belongsTo");
//				}
//			}
//		}
//		for(int i = w/2; i < w; i++) {
//			for(int j = h/2; j < h; j++) {
//				if(hr.belongsTo(i, j)) {
//					fail("CharacterTest belongsTo");
//				}
//			}
//		}
//		
//		HitboxRect hd = new HitboxRect();
//		hd.init(x, y, w/2, h/2);
//		if(!hr.equalsTo(hd)) {
//			fail("CharacterTest equalsTo");
//		}
//		if(!hr.collidesWith(hd)) {
//			fail("CharacterTest collidesWith");
//		}
	}
	
	@Test
	public void collisions() {
		HitboxRect a = new HitboxRect();
		HitboxRect b = new HitboxRect();
		HitboxRect c = new HitboxRect();
		HitboxRect d = new HitboxRect();
		
		a.init(0, 0, 50, 50);
		b.init(25, 25, 50,50);
		c.init(60, -10, 5, 20);
		d.init(61, -9, 3, 3);
		
		assertTrue(a.collidesWith(b));
		assertTrue(b.collidesWith(a));
		assertTrue(c.collidesWith(d));
		assertTrue(d.collidesWith(c));
		assertTrue(!a.collidesWith(c));
		assertTrue(!a.collidesWith(d));
		assertTrue(!a.collidesWith(c));
		assertTrue(!b.collidesWith(d));
		assertTrue(!c.collidesWith(b));
		assertTrue(!d.collidesWith(a));
	}
	
	@Test
	public void belongsTo() {
		HitboxRect a = new HitboxRect();
		a.init(0, 0, 50, 50);
		
		assertTrue(a.belongsTo(1, 1));
		assertTrue(a.belongsTo(0, 0));
		assertTrue(a.belongsTo(7, 50));
		assertTrue(a.belongsTo(50, 50));
		assertTrue(!a.belongsTo(200, 4));
		assertTrue(!a.belongsTo(50, 51));
		assertTrue(!a.belongsTo(0, -1));
	}
	
}
