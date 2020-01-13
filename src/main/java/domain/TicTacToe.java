package domain;

import java.util.Arrays;

/**
 * @author Albin Shema
 * @FileName: TicTacToe.java
 * @Date: Jan 13, 2020
 * @ProjectName: tic-tac-toe-core
 */
public class TicTacToe {
	
	private static final int COLUMNS = 3;
	private static final int ROWS = 3;
	private EBoardMark boardMark;
	private EState gameStatus;
	private EBoardMark[][] board;
	
	public TicTacToe() {
		super();
	}

	public TicTacToe(EBoardMark boardMark, EState gameStatus, EBoardMark[][] board) {
		super();
		this.boardMark = boardMark;
		this.gameStatus = gameStatus;
		this.board = board;
	}

	public EBoardMark getBoardMark() {
		return boardMark;
	}

	public void setBoardMark(EBoardMark boardMark) {
		this.boardMark = boardMark;
	}

	public EState getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(EState gameStatus) {
		this.gameStatus = gameStatus;
	}

	public EBoardMark[][] getBoard() {
		return board;
	}

	public void setBoard(EBoardMark[][] board) {
		this.board = board;
	}

	public static int getColumns() {
		return COLUMNS;
	}

	public static int getRows() {
		return ROWS;
	}

	@Override
	public String toString() {
		return "TicTacToe [boardMark=" + boardMark + ", gameStatus=" + gameStatus + ", board=" + Arrays.toString(board)
				+ "]";
	}
		
}
