package com.tic.tac.bot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.tic.tac.util.PlayerCounter;
import com.tic.tac.util.PositionEnum;


public class Bot implements Player{

	private PlayerCounter playerCounter;
	
	private Map<HashSet<PositionEnum>, PositionEnum> memory;
	
	private Map<HashSet<PositionEnum>, PositionEnum> tempMemory = new HashMap<HashSet<PositionEnum>, PositionEnum>();
	
	private int wins = 0;
	
	private int draws = 0;
	
	private int loses = 0;
	
	public Bot() {
		this.memory=new HashMap<HashSet<PositionEnum>, PositionEnum>();
	}
	
	public Bot(Map<HashSet<PositionEnum>, PositionEnum> memory) {
		this.memory=memory;
	}
	
	public PositionEnum makeAMove(HashSet<PositionEnum> board) {
		if(memory.containsKey(board)){
			return memory.get(board);
		}
		PositionEnum nextMove = PositionEnum.getPosFromInt((int)(Math.random()*9)+1);
		tempMemory.put(new HashSet<>(board), nextMove);
		return nextMove;
	}

	public void winner() {
		memory.putAll(tempMemory);
		tempMemory.clear();
		wins++;
	}

	public void draw() {
		tempMemory.clear();
		draws++;
	}

	public void loser() {
		tempMemory.clear();
		loses++;
	}
	
	public void setPlayerCounter(PlayerCounter playerCounter) {
		this.playerCounter=playerCounter;
	}

	public PlayerCounter getPlayerCounter() {
		return playerCounter;
	}
	
	public Map<HashSet<PositionEnum>, PositionEnum> getMemory(){
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
