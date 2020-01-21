package service;

import org.springframework.stereotype.Service;

import domain.EBoardMark;
import domain.TicTacToe;

/**
 * @author Albin Shema
 * @FileName: TicTacToeService.java
 * @Date: Jan 15, 2020
 * @ProjectName: tic-tac-toe-core
 */
@Service(ITicTaeToeService.NAME)
public class TicTaeToeService implements ITicTaeToeService {
	
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
	
	/*This method will return the best move from the AI*/
	public int[] getBestMove(EBoardMark[][] board) {
		AITicTacToe ai = new AITicTacToe();
		int [] result =  ai.bestMove(board);
		return result;
	}
}
