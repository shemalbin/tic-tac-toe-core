package service;

import domain.EBoardMark;

/**
 * @author Albin Shema
 * @FileName: ITicTacToeService.java
 * @Date: Jan 15, 2020
 * @ProjectName: tic-tac-toe-core
 */
public interface ITicTaeToeService {
	
	String NAME = "TicTaeToeService";

	public int[] getBestMove(EBoardMark[][] board);
	public EBoardMark checkTheWinner(EBoardMark[][] board);
}
