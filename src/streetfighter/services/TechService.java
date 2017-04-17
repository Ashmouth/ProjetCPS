package streetfighter.services;

public interface TechService {

	//Observators: 
		public int getDamage();
		public int getHstun();
		public int getBstun();
		public int getSframe();
		public int getHframe();
		public int getRframe();
		public HitboxService getHitbox(int x, int y);
}
