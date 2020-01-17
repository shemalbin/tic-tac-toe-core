package service;

import org.springframework.beans.factory.annotation.Autowired;

import domain.EBoardMark;
import domain.TicTacToe;

/**
 * @author Albin Shema
 * @FileName: AITicTacToe.java
 * @Date: Jan 17, 2020
 * @ProjectName: tic-tac-toe-core
 */
public class AITicTacToe {
	
	/*
	 * The ai is using an algorithm called minimax
	 * it is an algorithm used in decision making
	 * it is also used in different games where a player is searching for a better move.*/
	private int minimax(EBoardMark[][] board, int depth, Boolean isMaximizing) {
		
		//It will first check for the row, the col, and the diagonals if there is a winner of the game
		int result = checkWinner(board);
		
		if(result == 1) {
			return result;
		}
		
		if(result == -1) {
			return result;
		}
		
		if(availableSpaceOnTheBoard(board)) {
			return 0;
		}
		
		if(isMaximizing) {
			int bestScore = Integer.MIN_VALUE;
			
			for(int i=0; i<TicTacToe.getRows(); i++) {
				for(int j=0; j<TicTacToe.getColumns(); j++) {
					if(board[i][j] == EBoardMark.EMPTY) {
						// Place the point on the board
						board[i][j] = EBoardMark.CIRCLE;
						
						bestScore = Math.max(bestScore, minimax(board, depth+1, !isMaximizing));
						
						// Remove the point to the board
						board[i][j] = EBoardMark.EMPTY;
					}
				}
			}
			return bestScore;
		}
		else {
			int bestScore = Integer.MAX_VALUE;
			
			for(int i=0; i<TicTacToe.getRows(); i++) {
				for(int j=0; j<TicTacToe.getColumns(); j++) {
					if(board[i][j] == EBoardMark.EMPTY) {
						// Place the point on the board
						board[i][j] = EBoardMark.CROSS;
						
						bestScore = Math.min(bestScore, minimax(board, depth+1, !isMaximizing));
						
						// Remove the point to the board
						board[i][j] = EBoardMark.EMPTY;
					}
				}
			}
			return bestScore;
		}
	}
	
	
	public int[] bestMove(EBoardMark[][] board){
		int row = -100;
		int col = -100;
		int bestScore = Integer.MIN_VALUE;
		
		for(int i=0; i<TicTacToe.getRows(); i++) {
			for(int j=0; j<TicTacToe.getColumns(); j++) {
				if(board[i][j] == EBoardMark.EMPTY) {
					board[i][j] = EBoardMark.CIRCLE;
					int score = minimax(board, 0, false);
					board[i][j] = EBoardMark.EMPTY;
					
					if(score > bestScore) {
						row = i;
						col = j;
						bestScore = score;
					}
				}
			}
		}
		return new int[]{row,col};
	}
	
	private boolean availableSpaceOnTheBoard(EBoardMark [][] boardMark) {
		for(int i=0; i<TicTacToe.getRows(); i++) {
			for(int j=0; j<TicTacToe.getColumns(); j++) {
				if(boardMark[i][j] == EBoardMark.EMPTY) {
					return true;
				}
			}
		}
		return false;
	}
	
	/* This method will check if there is a winner
	 * It will return 1 if the computer won
	 * and -1 if the user won and 0 if none of them won*/
	public int checkWinner(EBoardMark[][] board) {
		// Check if the we have a winner on the rows
		for(int row=0; row<TicTacToe.getRows(); row++) {
			if(board[row][0] == board[row][1] && board[row][0] == board[row][2]) {
				if(board[row][0].equals(EBoardMark.CROSS)) {
					return -1;
				} else if(board[row][0].equals(EBoardMark.CIRCLE)) {
					return 1;
				}
			}
		}
		// Check if the we have a winner on the columns
		for(int col=0; col<TicTacToe.getColumns(); col++) {
			if(board[0][col] == board[1][col] && board[0][col] == board[2][col]) {
				if(board[0][col].equals(EBoardMark.CROSS)) {
					return -1;
				} else if(board[0][col].equals(EBoardMark.CIRCLE)) {
					return 1;
				}
			}
		}
		// Check if the we have a winner on the diagonals
		if(board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
			if(board[0][0].equals(EBoardMark.CROSS)) {
				return -1;
			} else if(board[0][0].equals(EBoardMark.CIRCLE)) {
				return 1;
			}
		}
		if(board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
			if(board[0][2].equals(EBoardMark.CROSS)) {
				return -1;
			} else if(board[0][2].equals(EBoardMark.CIRCLE)) {
				return 1;
			}
		}
		return 0;
	}
}