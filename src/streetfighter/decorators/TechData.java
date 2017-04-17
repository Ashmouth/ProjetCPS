package streetfighter.decorators;

import streetfighter.services.HitboxService;

public class TechData {
	int damage = 10;
	int hstun = 5;
	int bstun = 5;
	int sframe = 50;
	int hframe = 50;
	int rframe = 50;

	//Observators: 
	public int getDamage() {
		return damage;
	}

	public int getHstun() {
		return hstun;
	}

	public int getBstun() {
		return bstun;
	}

	public int getSframe() {
		return sframe;
	}

	public int getHframe() {
		return hframe;
	}

	public int getRframe() {
		return rframe;
	}

	public HitboxService getHitbox(int x, int y) {
		HitboxDecorator tmp = new HitboxDecorator(null);
		tmp.init(x, y);
		return tmp;
	}
}
