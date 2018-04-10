package com.tic.tac.bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import com.tic.tac.game.Board;
import com.tic.tac.util.PositionEnum;

public class TeacherBot {

	private Map<HashSet<PositionEnum>, Map<PositionEnum,Integer>> memory = new HashMap<HashSet<PositionEnum>, Map<PositionEnum,Integer>>();
	
	private Bot winner;
	
	public void teachBots(int numBots, int numGames, int numOfGenerations) {
		ComputerBot computer = new ComputerBot();
		for(int i = 1; i <= numOfGenerations; i++) {
			winner = null;
			System.out.println("-----------");
			System.out.println("Generation " + i);
			List<Bot> bots = new ArrayList<Bot>(numBots);
			for(int j = 0; j < numBots; j++) {
				Bot bot = new Bot(memory);
				bots.add(bot);
			}
			bots.parallelStream()
				.forEach(bot -> {
					for(int k = 0; k < numGames; k++) {
						Board board = new Board(bot, computer);
						board.startGame();
					}
					if(winner == null) {
						winner = bot;
					} else if (bot.getWins() > winner.getWins()) {
						winner = bot;
					}
				});
			memory = winner.getMemory();
			System.out.println("Winner Memory: " + winner.getMemory());
			System.out.println("Winner Wins: " + winner.getWins());
			System.out.println("Winner Draws: " + winner.getDraws());
			System.out.println("Winner Loses: " + winner.getLoses());
		}
	}
}
