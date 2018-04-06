package com.tic.tac.bot;

import java.util.HashSet;

import com.tic.tac.util.PlayerCounter;
import com.tic.tac.util.PositionEnum;

public interface Player {

	public PositionEnum makeAMove(HashSet<PositionEnum> board);
	
	public void winner();
	
	public void draw();
	
	public void loser();
	
	public void setPlayerCounter(PlayerCounter playerCounter);
	
	public PlayerCounter getPlayerCounter();
}
