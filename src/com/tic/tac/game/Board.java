package com.tic.tac.game;

import java.util.HashSet;

import com.tic.tac.bot.Player;
import com.tic.tac.util.PlayerCounter;
import com.tic.tac.util.PositionEnum;
import com.tic.tac.util.WinnerEmun;

public class Board {

	private HashSet<PositionEnum> board = new HashSet<>();
	
	private HashSet<PositionEnum> ticMoves = new HashSet<>();
	
	private HashSet<PositionEnum> tacMoves = new HashSet<>();
	
	private Player player1;
	
	private Player player2;
	
	private Player currentPlayer;
	
	private PlayerCounter winner;
	
	private boolean matchComplete = false;
	
	public Board(Player bot, Player computer) {
		randomlyAssignPlayers(bot, computer);
	}
	
	public void startGame() {
		if(player1 == null || player2 == null) {
			throw new IllegalStateException("A Player is missing");
		}
		currentPlayer = player1;
		while(!matchComplete) {
			PositionEnum playerMove = currentPlayer.makeAMove(board);
			addMoveToBoard(playerMove);
			checkBoardState();
			changeCurrentPlayer();
		}
		if(winner==null) {
			player1.loser();
			player2.loser();
		} else {
			if(winner.equals(player1.getPlayerCounter())) {
				player1.winner();
				player2.loser();
			} else {
				player1.loser();
				player2.winner();
			}
		}
	}
	
	private void changeCurrentPlayer() {
		if(!matchComplete) {
			if(currentPlayer.equals(player1)) {
				currentPlayer = player2;
			} else {
				currentPlayer = player1;
			}
		}
	}

	private void addMoveToBoard(PositionEnum playerMove) {
		if(board.contains(playerMove)) {
			matchComplete = true;
		} else {
			board.add(playerMove);
			if(PlayerCounter.TAC.equals(currentPlayer.getPlayerCounter())){
				tacMoves.add(playerMove);
			} else {
				ticMoves.add(playerMove);
			}
		}
	}

	private void checkBoardState() {
		if(!matchComplete) {
			if(board.size() == 9) {
				matchComplete = true;
				checkForWinner();
			} else {
				checkForWinner();
			}
		}
	}
	
	private void checkForWinner() {
		for(WinnerEmun winningPositions : WinnerEmun.values()) {
			if(ticMoves.containsAll(winningPositions.getAllPositions())) {
				winner = PlayerCounter.TIC;
				matchComplete = true;
			} else if (tacMoves.containsAll(winningPositions.getAllPositions())) {
				winner = PlayerCounter.TAC;
				matchComplete = true;
			}
		}
	}
	
	private void randomlyAssignPlayers(Player bot, Player computer) {
		int i = (int) (Math.random() * 10);
		if(i <= 5) {
			player1 = bot;
			player2 = computer;
		} else {
			player1 = computer;
			player2 = bot;
		}
	}

	
}
