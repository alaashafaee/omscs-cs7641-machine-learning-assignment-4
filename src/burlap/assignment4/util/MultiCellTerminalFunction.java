package burlap.assignment4.util;

import java.util.HashSet;

import burlap.assignment4.BasicGridWorld;
import burlap.assignment4.Cell;
import burlap.oomdp.core.TerminalFunction;
import burlap.oomdp.core.objects.ObjectInstance;
import burlap.oomdp.core.states.State;

public class MultiCellTerminalFunction implements TerminalFunction {

	HashSet<String> goals;

	public MultiCellTerminalFunction(int[] goalX, int[] goalY) throws Exception {
		goals = new HashSet<String>();
		if (goalX == null || goalY == null || goalX.length != goalY.length) {
			throw new Exception();
		}
		
		for (int i = 0; i < goalX.length; i++) {
			goals.add(getKey(goalX[i], goalY[i]));
		}
	}
	
	public MultiCellTerminalFunction(Cell[] cells) throws Exception {
		goals = new HashSet<String>();
		
		for (Cell cell : cells) {
			goals.add(getKey(cell.x, cell.y));
		}
	}

	@Override
	public boolean isTerminal(State s) {

		// get location of agent in next state
		ObjectInstance agent = s.getFirstObjectOfClass(BasicGridWorld.CLASSAGENT);
		int ax = agent.getIntValForAttribute(BasicGridWorld.ATTX);
		int ay = agent.getIntValForAttribute(BasicGridWorld.ATTY);

		String stateKey = getKey(ax, ay);
		if (goals.contains(stateKey)) {
			return true;
		}

		return false;
	}
	
	public String getKey(int x, int y) {
		return x + "#" + y;
	}

}
