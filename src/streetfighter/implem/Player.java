package streetfighter.implem;

import java.util.ArrayList;
import org.newdawn.slick.Input;
import streetfighter.data.CommandData;
import streetfighter.services.PlayerService;

public class Player implements PlayerService {

	private ArrayList<CommandData> stack;
	private int numPlayer;
	private int mx, zx, my, zy;
	private int lentech = 4;
	
	//Operators: 
	@Override
	public void init(int num) {
		stack = new ArrayList<CommandData>(lentech);
		for(int i = 0; i < lentech; i++) {
			stack.add(CommandData.NEUTRAL);
		}
		numPlayer = num;
		mx = 0;
		zx = 0;
		my = 0;
		zy = 0;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		System.out.println(">> Test Pressed");
		if(numPlayer == 1) {
			switch (key) {
			case Input.KEY_UP:    mx = 1; break;
			case Input.KEY_DOWN:  zx = 1;  break;
			case Input.KEY_RIGHT: my = 1;  break;
			case Input.KEY_LEFT:  zy = 1; break;
			case Input.KEY_0:	add(CommandData.PUNCH); break;
			case Input.KEY_1:	add(CommandData.GUARD); break;
			}
		} else {
			switch (key) {
			case Input.KEY_Z:    mx = 1; break;
			case Input.KEY_S:    zx = 1;  break;
			case Input.KEY_D:    my = 1;  break;
			case Input.KEY_Q:    zy = 1; break;
			case Input.KEY_LSHIFT:	add(CommandData.PUNCH); break;
			case Input.KEY_SPACE:	add(CommandData.GUARD); break;
			}
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		System.out.println(">> Test Realeased");
		if(numPlayer == 1) {
			switch (key) {
			case Input.KEY_UP:    mx = 0; break;
			case Input.KEY_DOWN:  zx = 0; break;
			case Input.KEY_RIGHT: my = 0; break;
			case Input.KEY_LEFT:  zy = 0; break;
			}
		} else {
			switch (key) {
			case Input.KEY_Z:    mx = 0; break;
			case Input.KEY_S:    zx = 0; break;
			case Input.KEY_D:    my = 0; break;
			case Input.KEY_Q:    zy = 0; break;
			}
		}
	}
	
	private void evalDirection() {
		int x = mx - zx;
		int y = my - zy;
		if (x == 0 && y == 0) {
			System.out.println(">> Test Neutral");
			add(CommandData.NEUTRAL);
		}
		if (x == 0 && y == 1) {
			System.out.println(">> Test UP");
			add(CommandData.UP);
		}
		if (x == 1 && y == 1) {
			add(CommandData.UPRIGHT);
		}
		if (x == -1 && y == 1) {
			add(CommandData.UPLEFT);
		}
		if (x == -1 && y == 0) {
			add(CommandData.LEFT);
		}
		if (x == 1 && y == 0) {
			add(CommandData.RIGHT);
		}
		if (x == 0 && y == -1) {
			add(CommandData.DOWN);
		}
		if (x == -1 && y == -1) {
			add(CommandData.DOWNLEFT);
		}
		if (x == 1 && y == -1) {
			add(CommandData.DOWNRIGHT);
		}
	}
	
	public void add(CommandData c) {
		stack.add(c);
		stack.remove(0);
	}
		
	@Override
	public CommandData getCommand() {
		
		//On evalue la direction
		evalDirection();
		
		//On cherche une command de technique
		if (stack.get(0) == CommandData.DOWN) {
			if (stack.get(1) == CommandData.DOWNRIGHT) {
				if (stack.get(2) == CommandData.RIGHT) {
					return CommandData.QRCIRCLE;
				}
			}
			if (stack.get(0) == CommandData.DOWN) {
				if (stack.get(1) == CommandData.DOWNLEFT) {
					if (stack.get(2) == CommandData.LEFT) {
						return CommandData.QLCIRCLE;
					}
				}
			}
		}
		if (stack.get(0) == CommandData.LEFT) {
			if (stack.get(1) == CommandData.RIGHT) {
				return CommandData.RDASH;
			}
		}
		if (stack.get(0) == CommandData.RIGHT) {
			if (stack.get(1) == CommandData.LEFT) {
				return CommandData.LDASH;
			}
		}
		if (stack.get(0) == CommandData.UP) {
			if (stack.get(1) == CommandData.UP) {
				return CommandData.DOUBLEJUMP;
			}
		}
		
		//Sinon on retourne la derniere commande
		return stack.get(stack.size());	
	}
}