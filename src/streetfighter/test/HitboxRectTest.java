package streetfighter.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import streetfighter.implem.HitboxRect;

public class HitboxRectTest {

	@Test
	public void init() {
		HitboxRect hr = new HitboxRect();
		int x = 0;
		int y = 0;
		int w = 0;
		int h = 0;
		hr.init(x, y, w, h);
		
		if(hr.getPositionX() != x) {
			fail("CharacterTest getPositionX");
		}
		if(hr.getPositionY() != y) {
			fail("CharacterTest getPositionX");
		}
		if(hr.getWidth() != w) {
			fail("CharacterTest getWidth");
		}
		if(hr.getHeight() != h) {
			fail("CharacterTest getHeight");
		}
		
		hr.moveTo(x+10, y+10);
		if(hr.getPositionX() != x+10) {
			fail("CharacterTest moveTo");
		}
		if(hr.getPositionY() != y+10) {
			fail("CharacterTest moveTo");
		}
		if(hr.getWidth() != w) {
			fail("CharacterTest moveTo");
		}
		if(hr.getHeight() != h) {
			fail("CharacterTest moveTo");
		}
			
		hr.moveTo(x, y);
		
		hr.resize(w/2, h/2);
		if(hr.getPositionX() != x) {
			fail("CharacterTest resize");
		}
		if(hr.getPositionY() != y) {
			fail("CharacterTest resize");
		}
		if(hr.getWidth() != w/2) {
			fail("CharacterTest resize");
		}
		if(hr.getHeight() != h/2) {
			fail("CharacterTest resize");
		}
		
		for(int i = 0; i < w/2; i++) {
			for(int j = 0; j < h/2; j++) {
				if(!hr.belongsTo(i, j)) {
					fail("CharacterTest belongsTo");
				}
			}
		}
		for(int i = w/2; i < w; i++) {
			for(int j = h/2; j < h; j++) {
				if(hr.belongsTo(i, j)) {
					fail("CharacterTest belongsTo");
				}
			}
		}
		
		HitboxRect hd = new HitboxRect();
		hd.init(x, y, w/2, h/2);
		if(!hr.equalsTo(hd)) {
			fail("CharacterTest equalsTo");
		}
		if(!hr.collidesWith(hd)) {
			fail("CharacterTest collidesWith");
		}
	}
}
