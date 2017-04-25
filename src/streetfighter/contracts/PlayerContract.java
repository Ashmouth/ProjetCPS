package streetfighter.contracts;

import java.util.Vector;

import org.newdawn.slick.Input;
import streetfighter.data.CommandData;
import streetfighter.decorators.PlayerDecorator;
import streetfighter.services.PlayerService;

public class PlayerContract extends PlayerDecorator implements PlayerService {

	private Vector<CommandData> CommandStack;
	private int techLen = 4;
	private int numPlayer;
	
	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}
	
	//Observators: 
	@Override
	public CommandData getCommand() {
		if (CommandStack.get(0) == CommandData.DOWN) {
			if (CommandStack.get(1) == CommandData.DOWNRIGHT) {
				if (CommandStack.get(2) == CommandData.RIGHT) {
					return CommandData.QRCIRCLE;
				}
			}
			if (CommandStack.get(0) == CommandData.DOWN) {
				if (CommandStack.get(1) == CommandData.DOWNLEFT) {
					if (CommandStack.get(2) == CommandData.LEFT) {
						return CommandData.QLCIRCLE;
					}
				}
			}
		}
		if (CommandStack.get(0) == CommandData.LEFT) {
			if (CommandStack.get(1) == CommandData.RIGHT) {
				return CommandData.RDASH;
			}
		}
		if (CommandStack.get(0) == CommandData.RIGHT) {
			if (CommandStack.get(1) == CommandData.LEFT) {
				return CommandData.LDASH;
			}
		}
		return CommandStack.get(0);
	}
	
	//Operators: 
	
	@Override
	public void init(int num) {
		CommandStack = new Vector<CommandData>(techLen);
		numPlayer = num;
	}
	
	@Override
	public CommandData keyReleased(int key, char c) {
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
			if (Input.KEY_RSHIFT == key) {
				return CommandData.PUNCH;
			}
			if (Input.KEY_LCONTROL == key) {
				return CommandData.KICK;
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
				return CommandData.PUNCH;
			}
			if (Input.KEY_SPACE == key) {
				return CommandData.KICK;
			}
		}
		return CommandData.NEUTRAL;
	}
	
	@Override
	public void step() {
		CommandStack.remove(techLen);
		//CommandData tmp = getInput();	TODO find how recover input
		//CommandStack.add(0, tmp);
	}
}
