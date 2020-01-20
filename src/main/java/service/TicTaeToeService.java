package service;

import domain.EBoardMark;
import domain.EState;
import domain.TicTacToe;
import org.springframework.stereotype.Service;

/**
 * @author Albin Shema
 * @FileName: TicTacToeService.java
 * @Date: Jan 15, 2020
 * @ProjectName: tic-tac-toe-core
 */
@Service(ITicTaeToeService.NAME)
public class TicTaeToeService implements ITicTaeToeService {
	
	private EBoardMark [][] board;
	

	// The method which will initialize the board and start over
	public TicTacToe initializeTheBoard(TicTacToe ticTacToe) {
		try {
			board = new EBoardMark [TicTacToe.getRows()][TicTacToe.getColumns()];
			
			/* 
			 * Initializing the board with the empty spaces 
			 * Starting by looping through the rows and then the columns */
			for(int i=0; i<TicTacToe.getRows(); i++) {
				for(int j=0; j<TicTacToe.getColumns(); j++) {
					board[i][j] = EBoardMark.EMPTY;
				}
			}
			
			/*
			 * The player with the cross mark will start to play 
			 * and the status of the game will be in playing mode */		
			ticTacToe.setBoard(board);
			ticTacToe.setBoardMark(EBoardMark.CROSS);
			ticTacToe.setGameStatus(EState.PLAYING);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ticTacToe;
	}

	public EBoardMark [][] getPlayerMove(int row, int column, EBoardMark [][] boardMark) {
		try {
			//Validate first if the move is valid
			boolean isMoveValid = validatePlayerMove(row, column, boardMark);
			// Add the player move on the board
			if(isMoveValid) {
				boardMark[row] [column] = EBoardMark.CROSS;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return boardMark;
	}
	
	public boolean validatePlayerMove(int row, int col, EBoardMark [][] boardMark) {
		boolean valid = true;
		// First we will validate if the row and the col is not out of the board
		if(row<0 || row>2 || col<0 || col>2 )
			valid = false;
		// Secondly we will validate if the player played in an empty space on the board	
		if(!boardMark[row][col].equals(EBoardMark.EMPTY))
			valid = false;
		return valid;
	}
	
	/* This method will check if there is a winner
	 * It will return cross if the computer won
	 * and circle if the user won and empty if none of them won*/
	public EBoardMark checkTheWinner(EBoardMark[][] board) {
		// Check if the we have a winner on the rows
		for(int row=0; row<TicTacToe.getRows(); row++) {
			if(board[row][0] == board[row][1] && board[row][0] == board[row][2]) {
				if(board[row][0].equals(EBoardMark.CROSS)) {
					return EBoardMark.CROSS;
				} else if(board[row][0].equals(EBoardMark.CIRCLE)) {
					return EBoardMark.CIRCLE;
				}
			}
		}
		// Check if the we have a winner on the columns
		for(int col=0; col<TicTacToe.getColumns(); col++) {
			if(board[0][col] == board[1][col] && board[0][col] == board[2][col]) {
				if(board[0][col].equals(EBoardMark.CROSS)) {
					return EBoardMark.CROSS;
				} else if(board[0][col].equals(EBoardMark.CIRCLE)) {
					return EBoardMark.CIRCLE;
				}
			}
		}
		// Check if the we have a winner on the diagonals
		if(board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
			if(board[0][0].equals(EBoardMark.CROSS)) {
				return EBoardMark.CROSS;
			} else if(board[0][0].equals(EBoardMark.CIRCLE)) {
				return EBoardMark.CIRCLE;
			}
		}
		if(board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
			if(board[0][2].equals(EBoardMark.CROSS)) {
				return EBoardMark.CROSS;
			} else if(board[0][2].equals(EBoardMark.CIRCLE)) {
				return EBoardMark.CIRCLE;
			}
		}
		return EBoardMark.EMPTY;
	}

	public int[] getBestMove(EBoardMark[][] board) {
		AITicTacToe ai = new AITicTacToe();
		int [] result =  ai.bestMove(board);
		return result;
	}
}
