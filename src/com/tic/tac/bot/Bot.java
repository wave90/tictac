package com.tic.tac.bot;

import java.util.HashSet;
import java.util.Map;

import com.tic.tac.util.PlayerCounter;
import com.tic.tac.util.PositionEnum;


public class Bot implements Player{

	private PlayerCounter playerCounter;
	
	private Map<HashSet<PositionEnum>, PositionEnum> memory;
	
	private Map<HashSet<PositionEnum>, PositionEnum> tempMemory;
	
	public Bot(Map<HashSet<PositionEnum>, PositionEnum> memory) {
		this.memory=memory;
	}
	
	public PositionEnum makeAMove(HashSet<PositionEnum> board) {
		if(memory.containsKey(board)){
			return memory.get(board);
		}
		PositionEnum nextMove = PositionEnum.getPosFromInt((int)(Math.random()*8));
		tempMemory.put(board, nextMove);
		return nextMove;
	}

	public void winner() {
		memory.putAll(tempMemory);
		tempMemory.clear();
	}

	public void loser() {
		tempMemory.clear();
	}
	
	public void setPlayerCounter(PlayerCounter playerCounter) {
		this.playerCounter=playerCounter;
	}

	public PlayerCounter getPlayerCounter() {
		return playerCounter;
	}
	
}
