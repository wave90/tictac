package com.tic.tac.bot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.tic.tac.util.PlayerCounter;
import com.tic.tac.util.PositionEnum;


public class Bot implements Player{

	private static final int NEW_POSITION_THRESHOLD = 25;
	
	private static final int NEW_POSITION_WEIGHT = 10;
	
	private static final int WEIGHTING_NUM = 5;
	
	private PlayerCounter playerCounter;
	
	private Map<HashSet<PositionEnum>, Map<PositionEnum,Integer>> memory;
	
	private Map<HashSet<PositionEnum>, Map<PositionEnum,Integer>> tempMemory = new HashMap<HashSet<PositionEnum>, Map<PositionEnum,Integer>>();
	
	private int wins = 0;
	
	private int draws = 0;
	
	private int loses = 0;
	
	public Bot() {
		this.memory=new HashMap<HashSet<PositionEnum>, Map<PositionEnum,Integer>>();
	}
	
	public Bot(Map<HashSet<PositionEnum>, Map<PositionEnum,Integer>> memory) {
		this.memory=new HashMap<HashSet<PositionEnum>, Map<PositionEnum,Integer>>(memory);
	}
	
	public PositionEnum makeAMove(HashSet<PositionEnum> board) {
		if(memory.containsKey(board)){
			return getWeighting(board, memory.get(board));
		}
		return getNewPosition(board);
	}
	
	private PositionEnum getWeighting(HashSet<PositionEnum> board, Map<PositionEnum,Integer> map) {
		int totalWeight = 0;
		for(int weight : map.values()) {
			totalWeight = totalWeight + weight;
		}
		int weight;
		if(totalWeight <= NEW_POSITION_THRESHOLD) {
			weight = (int) (Math.random() * (totalWeight + NEW_POSITION_WEIGHT));
		} else {
			weight = (int) (Math.random() * totalWeight);
		}
		if(weight > totalWeight) {
			return getNewPosition(board);
		} else {
			for (PositionEnum nextPosition : map.keySet()) {
				int posWeight = map.get(nextPosition);
					if(weight > posWeight) {
						weight = weight - posWeight;
					} else {
						posWeight = posWeight + WEIGHTING_NUM;
						map.put(nextPosition, posWeight);
						return nextPosition;
					}
			}
		}
		throw new IllegalStateException("Must get next move from weighting");
	}

	private PositionEnum getNewPosition(HashSet<PositionEnum> board) {
		PositionEnum nextMove = null;
		if(tempMemory.containsKey(board)) {
			Map<PositionEnum,Integer> weightingMap = tempMemory.get(board);
			boolean newMove = false;
			while(!newMove) {
				nextMove = PositionEnum.getPosFromInt((int)(Math.random()*9) + 1);
				if(!weightingMap.containsKey(nextMove)) {
					newMove = true;	
				}
			}
			weightingMap.put(nextMove, WEIGHTING_NUM);
		} else {
			nextMove = PositionEnum.getPosFromInt((int)(Math.random()*9)+1);
			Map<PositionEnum,Integer> weightingMap = new HashMap<PositionEnum, Integer>();
			weightingMap.put(nextMove, WEIGHTING_NUM);
			tempMemory.put(new HashSet<>(board), weightingMap);	
		}
		return nextMove;
	}
	
	public void winner() {
		for(HashSet<PositionEnum> tempMainKey : tempMemory.keySet()) {
			if(memory.containsKey(tempMainKey)) {
				Map<PositionEnum,Integer> weightingPositionMap = memory.get(tempMainKey);
				Map<PositionEnum,Integer> tempWeightingPositionMap = tempMemory.get(tempMainKey);
				for(PositionEnum tempWeightKey: tempWeightingPositionMap.keySet()) {
					if(weightingPositionMap.containsKey(tempWeightKey)) {
						int weightMoveNum = weightingPositionMap.get(tempWeightKey);
						weightMoveNum = weightMoveNum + WEIGHTING_NUM;
						weightingPositionMap.put(tempWeightKey, weightMoveNum);
					} else {
						weightingPositionMap.put(tempWeightKey, WEIGHTING_NUM);
					}
				}
			} else {
				memory.put(tempMainKey, tempMemory.get(tempMainKey));
			}
		}
		tempMemory.clear();
		wins++;
	}

	public void draw() {
		tempMemory.clear();
		draws++;
	}

	public void loser() {
		for(HashSet<PositionEnum> tempMainKey : tempMemory.keySet()) {
			if(memory.containsKey(tempMainKey)) {
				Map<PositionEnum,Integer> weightingPositionMap = memory.get(tempMainKey);
				Map<PositionEnum,Integer> tempWeightingPositionMap = tempMemory.get(tempMainKey);
				for(PositionEnum tempWeightKey: tempWeightingPositionMap.keySet()) {
					if(weightingPositionMap.containsKey(tempWeightKey)) {
						int weightMoveNum = weightingPositionMap.get(tempWeightKey);
						if(weightMoveNum != 0) {
							weightMoveNum = weightMoveNum - WEIGHTING_NUM;
							weightingPositionMap.put(tempWeightKey, weightMoveNum);
						}
					}
				}
			}
		}
		tempMemory.clear();
		loses++;
	}
	
	public void setPlayerCounter(PlayerCounter playerCounter) {
		this.playerCounter=playerCounter;
	}

	public PlayerCounter getPlayerCounter() {
		return playerCounter;
	}
	
	public Map<HashSet<PositionEnum>, Map<PositionEnum,Integer>> getMemory(){
		return memory;
	}

	public int getWins() {
		return wins;
	}

	public int getDraws() {
		return draws;
	}

	public int getLoses() {
		return loses;
	}
	
	
	
}
