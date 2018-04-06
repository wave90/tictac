package com.tic.tac.bot;

import java.util.HashSet;

import com.tic.tac.util.PlayerCounter;
import com.tic.tac.util.PositionEnum;

public class ComputerBot implements Player {

	private PlayerCounter playerCounter;
	
	public PositionEnum makeAMove(HashSet<PositionEnum> board) {
		return PositionEnum.getPosFromInt((int)(Math.random()*8));
	}

	public void winner() {
		
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
