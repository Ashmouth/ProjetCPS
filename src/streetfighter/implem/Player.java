package streetfighter.implem;

import java.util.Arrays;
import org.newdawn.slick.Input;
import streetfighter.data.CommandData;
import streetfighter.services.PlayerService;

public class Player implements PlayerService {

	private int numPlayer;
	private boolean[] pressed;
//	private final int tmax = 3;
//	private int t = 0;
//	private CommandData stock;
	
	private boolean pr(CommandData c) {
		return pressed[c.ordinal()];
	}

	public CommandData getCommand() {
		if (pr(CommandData.DOWN)) {
			if(pr(CommandData.GUARD)) return CommandData.DOWNGUARD;
			if(pr(CommandData.RIGHT)) return CommandData.DOWNRIGHT;
			if(pr(CommandData.LEFT)) return CommandData.DOWNLEFT;
			if(pr(CommandData.PUNCH)) return CommandData.KICK;
			return CommandData.DOWN;
		}
		if (pr(CommandData.UP)) {
			if(pr(CommandData.RIGHT)) return CommandData.UPRIGHT;
			if(pr(CommandData.LEFT)) return CommandData.UPLEFT;
			if(pr(CommandData.PUNCH)) return CommandData.HEAD;
			return CommandData.UP;
		}
		if(pr(CommandData.LEFT)) {
			return CommandData.LEFT;
		}
		if(pr(CommandData.RIGHT)) {
			return CommandData.RIGHT;
		}

		if(pr(CommandData.GUARD)) {
			return CommandData.GUARD;
		}

		if(pr(CommandData.PUNCH)) {
			return CommandData.PUNCH;
		}

		return CommandData.NEUTRAL;
	}
	
//	public CommandData getCommand() {
//		if(t == tmax) {
//			t = 0;
//			stock = pressCommand();
//			return CommandData.NEUTRAL;
//		} else {
//			CommandData tmp = pressCommand();
//			stock = combo(tmp);
//		}
//		t++;
//		return stock;
//	}
//	
//	private CommandData combo(CommandData tmp) {
//		System.out.println(">>Combo");
//		if (stock == CommandData.UPLEFT) {
//			return CommandData.UPLEFT;
//		}
//		if(stock == CommandData.DOWN) {
//			if(tmp == CommandData.GUARD) return CommandData.DOWNGUARD;
//			if(tmp == CommandData.RIGHT) return CommandData.DOWNRIGHT;
//			if(tmp == CommandData.LEFT) return CommandData.DOWNLEFT;
//			if(tmp == CommandData.PUNCH) return CommandData.KICK;
//			return CommandData.DOWN;
//		}
//		
//		if (stock == CommandData.UP) {
//			if(tmp == CommandData.RIGHT) return CommandData.UPRIGHT;
//			if(tmp == CommandData.LEFT) {
//				System.out.println(">>PASS");
//				return CommandData.UPLEFT;
//			}
//			if(stock == CommandData.PUNCH) return CommandData.HEAD;
//			return CommandData.UP;
//		}
//		
//		if(stock == CommandData.LEFT) {
//			if (stock == CommandData.UP) {
//				return CommandData.UPLEFT;
//			}
//			return CommandData.LEFT;
//		}
//		if(stock == CommandData.RIGHT) {
//			return CommandData.RIGHT;
//		}
//		
//		if(stock == CommandData.GUARD) {
//			return CommandData.GUARD;
//		}
//		
//		if(stock == CommandData.PUNCH) {
//			return CommandData.PUNCH;
//		}
//		
//		//return stock;
//		return tmp;
//	}
//	
//	public CommandData pressCommand() {
//	if (pr(CommandData.DOWN)) {
//		return CommandData.DOWN;
//	}
//	if (pr(CommandData.UP)) {
//		return CommandData.UP;
//	}
//	if(pr(CommandData.LEFT)) {
//		return CommandData.LEFT;
//	}
//	if(pr(CommandData.RIGHT)) {
//		return CommandData.RIGHT;
//	}
//
//	if(pr(CommandData.GUARD)) {
//		return CommandData.GUARD;
//	}
//
//	if(pr(CommandData.PUNCH)) {
//		return CommandData.PUNCH;
//	}
//
//	return CommandData.NEUTRAL;
//}

	//Operators: 
	@Override
	public void init(int num) {
		//stack = new ArrayList<CommandData>();
		numPlayer = num;
		
		pressed = new boolean[CommandData.values().length];
		Arrays.fill(pressed, false);
//		stock = CommandData.NEUTRAL;
	}
	
	private CommandData input2cmd(int key) {
		//J1
		if(numPlayer == 1) {
			if (Input.KEY_UP == key) {
				return CommandData.UP;
			}
			if (Input.KEY_DOWN == key) {
				return CommandData.DOWN;
			}
			if (Input.KEY_RIGHT == key) {
				return CommandData.RIGHT;
			}
			if (Input.KEY_LEFT == key) {
				return CommandData.LEFT;
			}
			if (Input.KEY_NUMPADENTER == key) {
				return CommandData.PUNCH;
			}
			if (Input.KEY_NUMPAD3 == key) {
				return CommandData.GUARD;
			}


			//J2
		} else {
			if (Input.KEY_Z == key) {
				return CommandData.UP;
			}
			if (Input.KEY_S == key) {
				return CommandData.DOWN;
			}
			if (Input.KEY_D == key) {
				return CommandData.RIGHT;
			}
			if (Input.KEY_Q == key) {
				return CommandData.LEFT;
			}
			if (Input.KEY_LSHIFT == key) {
				return CommandData.GUARD;
			}
			if (Input.KEY_SPACE == key) {
				return CommandData.PUNCH;
			}
		}
		return CommandData.NEUTRAL;
	}
	
	public void addInput(int key) {
		pressed[input2cmd(key).ordinal()] = true;
	}
	
	public void clearInput(int key) {
		pressed[input2cmd(key).ordinal()] = false;
	}
}
