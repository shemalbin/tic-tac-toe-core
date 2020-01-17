package service;

import domain.EBoardMark;
import domain.TicTacToe;

/**
 * @author Albin Shema
 * @FileName: ITicTacToeService.java
 * @Date: Jan 15, 2020
 * @ProjectName: tic-tac-toe-core
 */
public interface ITicTaeToeService {
	
	String NAME = "TicTaeToeService";
	
	public TicTacToe initializeTheBoard(TicTacToe ticTacToe);
	public EBoardMark [][] getPlayerMove(int row, int col, EBoardMark [][] board);
	public int checkWinner(EBoardMark [][] board);
}
