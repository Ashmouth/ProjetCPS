package streetfighter.decorators;

import streetfighter.services.TechService;

public class TechDecorator implements TechService {
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
		
		public HitboxDecorator getHitbox(int x, int y) {
			return new HitboxDecorator(x, y);
		}
}
