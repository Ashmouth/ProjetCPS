package streetfighter.implem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;

import streetfighter.data.CommandData;
import streetfighter.services.InputService;
import streetfighter.services.PlayerService;

public class Player implements PlayerService {

	private List<CommandData> stack;
	private int numPlayer;
	private boolean[] pressed;
	
	private boolean pr(CommandData c) {
		return pressed[c.ordinal()];
	}
	
	@Override
	public CommandData getCommand() {
		if (pr(CommandData.DOWN)) {
			if(pr(CommandData.RIGHT)) return CommandData.DOWNRIGHT;
			if(pr(CommandData.LEFT)) return CommandData.DOWNLEFT;
		}
		if (pr(CommandData.UP)) {
			if(pr(CommandData.RIGHT)) return CommandData.UPRIGHT;
			if(pr(CommandData.LEFT)) return CommandData.UPLEFT;
		}
		return CommandData.NEUTRAL;
		
	}
	
	//Operators: 
	@Override
	public void init(int num) {
		stack = new ArrayList<CommandData>();
		numPlayer = num;
		
		pressed = new boolean[CommandData.values().length];
		Arrays.fill(pressed, false);
	}
	
	private CommandData input2cmd(int key) {
		if (Input.KEY_ESCAPE == key) {
			//TODO END
		}
		
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
			if (Input.KEY_0 == key) {
				return CommandData.PUNCH;
			}
			if (Input.KEY_1 == key) {
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
	
	public void addInput(int k) {
		pressed[input2cmd(k).ordinal()] = true;
	}
	
	public void clearInput(int k) {
		pressed[input2cmd(k).ordinal()] = false;
	}
}
