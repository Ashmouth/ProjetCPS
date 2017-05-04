package streetfighter.data;

import streetfighter.implem.HitboxRect;
import streetfighter.services.HitboxRectService;
import streetfighter.services.HitboxService;

public class TechData {
	public int damage;
	public int hstun;
	public int bstun;
	public int sframe;
	public int hframe;
	public int rframe;
	public int width;
	public int height;
	
	public TechData(int d, int hs, int bs, int sf, int hf, int rf, int w, int h) {
		damage = d;
		hstun = hs;
		bstun = bs;
		sframe = sf;
		hframe = hf;
		rframe = rf;
		width = w;
		height = h;
	}
	
	public TechData punch() {
		return new TechData(20, 5, 5, 10, 10, 5, 15, 5);
	}
	
	public TechData kick() {
		return new TechData(50, 15, 10, 20, 10, 10, 20, 5);
	}
	
	public TechData head() {
		return new TechData(30, 5, 5, 10, 5, 20, 10, 10);
	}

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
		HitboxRectService tmp = new HitboxRect();
		tmp.init(x, y, width, height);
		return tmp;
	}
}
