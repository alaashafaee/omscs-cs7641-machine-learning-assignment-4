package burlap.assignment4.util;

import java.util.HashMap;
import java.util.HashSet;

import burlap.assignment4.BasicGridWorld;
import burlap.assignment4.Cell;
import burlap.oomdp.core.objects.ObjectInstance;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.GroundedAction;
import burlap.oomdp.singleagent.RewardFunction;

public class MultiCellRewardFunction implements RewardFunction {

	HashSet<String> goals;
	HashMap<String, Double> rewards;
	
	public MultiCellRewardFunction(Cell[] cellRewards) {
		rewards = new HashMap<String, Double>();
		for (Cell cell: cellRewards) {
			String stateKey = getKey(cell.x, cell.y);
			rewards.put(stateKey, cell.reward);
		}
	}
	
	public MultiCellRewardFunction(int[] goalX, int[] goalY) throws Exception {
		goals = new HashSet<String>();
		
		if (goalX == null || goalY == null || goalX.length != goalY.length) {
			throw new Exception("Invalid reward function input");
		}
		
		for (int i = 0; i < goalX.length; i++) {
			goals.add(getKey(goalX[i], goalY[i]));
		}
	}

	/*@Override
	public double reward(State s, GroundedAction a, State sprime) {

		// get location of agent in next state
		ObjectInstance agent = sprime.getFirstObjectOfClass(BasicGridWorld.CLASSAGENT);
		int ax = agent.getIntValForAttribute(BasicGridWorld.ATTX);
		int ay = agent.getIntValForAttribute(BasicGridWorld.ATTY);

		// are they at goal location?
		String stateKey = getKey(ax, ay);
		if (goals.contains(stateKey)) {
			return 100.;
		}

		return -1;
	}*/
	
	@Override
	public double reward(State s, GroundedAction a, State sprime) {

		// get location of agent in next state
		ObjectInstance agent = sprime.getFirstObjectOfClass(BasicGridWorld.CLASSAGENT);
		int ax = agent.getIntValForAttribute(BasicGridWorld.ATTX);
		int ay = agent.getIntValForAttribute(BasicGridWorld.ATTY);

		// are they at goal location?
		String stateKey = getKey(ax, ay);
		/*if (goals.contains(stateKey)) {
			return 100.;
		}*/
		
		if (rewards.containsKey(stateKey)) {
			return rewards.get(stateKey);
		}

		return -1;
	}

	public String getKey(int x, int y) {
		return x + "#" + y;
	}
}
