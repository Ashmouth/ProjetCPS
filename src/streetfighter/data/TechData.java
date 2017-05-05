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
	public int y;
	public HitboxRectService hitbox;
	
	public TechData(int d, int hs, int bs, int sf, int hf, int rf, int w, int h, int y) {
		damage = d;
		hstun = hs;
		bstun = bs;
		sframe = sf;
		hframe = hf;
		rframe = rf;
		width = w;
		height = h;
		this.y = y;
	}
	
	public static TechData punch() { // coup de poing normal
		return new TechData(20, 30, 5, 10, 10, 10, 60, 5, 60);
	}
	
	public static TechData kick() { // coup dans les jambes
		return new TechData(20, 15, 5, 12, 10, 10, 60, 5, 30);
	}
	
	public static TechData head() { // coup dans la tete
		return new TechData(30, 45, 10, 20, 10, 20, 40, 5, 90);
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

	public HitboxService getHitbox(int x, int y, boolean fr, int wi) {
		HitboxRectService tmp = new HitboxRect();
		
		// si regarde à gauche, on décalle à gauche la hitbox
		if (!fr) {
			x -= width;
		} else {
			x += wi;
		}
		
		tmp.init(x, y+this.y, width, height);
		return tmp;
	}
}
