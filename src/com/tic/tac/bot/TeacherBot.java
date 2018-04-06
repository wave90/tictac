package com.tic.tac.bot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.tic.tac.game.Board;
import com.tic.tac.util.PositionEnum;

public class TeacherBot {

	private Map<HashSet<PositionEnum>, PositionEnum> memory = new HashMap<HashSet<PositionEnum>, PositionEnum>();
	
	private Bot winner;
	
	public void teachBots(int numBots, int numGames, int numOfGenerations) {
		for(int i = 1; i <= numOfGenerations; i++) {
			System.out.println("-----------");
			System.out.println("Generation " + i);
			winner = null;
			startGeneration(numBots, numGames);
		}
	}
	
	
	private void startGeneration(int numBots, int numGames) {
		for(int i = 0; i < numBots; i++) {
			Bot bot = new Bot(memory);
			ComputerBot computer = new ComputerBot();
			for(int j = 0; j < numGames; j++) {
				Board board = new Board(bot, computer);
				board.startGame();
			}
			if(winner == null) {
				winner = bot;
			} else {
				if(bot.getWins() > winner.getWins()) {
					winner = bot;
				}
			}
		}
		memory = winner.getMemory();
		System.out.println("Winner Memory: " + winner.getMemory());
		System.out.println("Winner Wins: " + winner.getWins());
		System.out.println("Winner Draws: " + winner.getDraws());
		System.out.println("Winner Loses: " + winner.getLoses());
}
}
