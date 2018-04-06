package com.tic.tac.bot;

import java.util.HashSet;

import com.tic.tac.util.PlayerCounter;
import com.tic.tac.util.PositionEnum;

public class ComputerBot implements Player {

	private PlayerCounter playerCounter;
	
	public PositionEnum makeAMove(HashSet<PositionEnum> board) {
		boolean newMove = false;
		PositionEnum nextMove = null;
		while(!newMove) {
			nextMove = PositionEnum.getPosFromInt((int)(Math.random()*9) + 1);
			if(!board.contains(nextMove)) {
				newMove = true;
				
			}
		}
		return nextMove;
	}

	public void winner() {

	}

	public void draw() {

	}
	
	public void loser() {	

	}

	public void setPlayerCounter(PlayerCounter playerCounter) {
		this.playerCounter=playerCounter;
	}

	public PlayerCounter getPlayerCounter() {
		return playerCounter;
	}
	
}
