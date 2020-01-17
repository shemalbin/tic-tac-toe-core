package test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.EBoardMark;
import domain.EState;
import domain.TicTacToe;
import service.ITicTaeToeService;

/**
 * @author Albin Shema
 * @FileName: TestTicTacToe.java
 * @Date: Jan 15, 2020
 * @ProjectName: tic-tac-toe-core
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/application-context.xml" })
public class TestTicTacToe {
	
	@Autowired
	private ITicTaeToeService ticTacToeService;
	
	@Test
	public void testInitializeBoard() {
		// Check if the result board is in playing state and if it is the turn for the user(cross) to play
		TicTacToe ticTacToe = new TicTacToe();
		TicTacToe ticTacToeResult = ticTacToeService.initializeTheBoard(ticTacToe);
		assertEquals(EState.PLAYING, ticTacToeResult.getGameStatus());
		assertEquals(EBoardMark.CROSS, ticTacToeResult.getBoardMark());
	}
	
	@Test(expected = NullPointerException.class)
	public void testInitializeBoardNegative() {
		// Test to check if it will throw the null pointer exception
		TicTacToe ticTacToe = null;
		TicTacToe ticTacToeResult = ticTacToeService.initializeTheBoard(ticTacToe);
		assertEquals(EState.PLAYING, ticTacToeResult.getGameStatus());
	}
	
	@Test
	public void testGetPlayerMove() {
		// Test to check if the user move will be displayed on the board
		EBoardMark [][] board = this.testBoardMarkSet();
		EBoardMark [][] newBoard = ticTacToeService.getPlayerMove(0, 2, board);
		assertEquals(EBoardMark.CROSS, newBoard[0][2]);
	}
	
	@Test
	public void testGetPlayerMoveInvalid() {
		// Test to check if the user played an invalid move if the board will not change
		EBoardMark [][] board = this.testBoardMarkSet();
		EBoardMark [][] newBoard = ticTacToeService.getPlayerMove(3, 3, board);
		assertArrayEquals(newBoard, board);
	}
	
	@Test
	public void testGetPlayerMoveNotEmptySpace() {
		// Test to check if the user played in an occupied space if the space will not change
		EBoardMark [][] board = this.testBoardMarkSet();
		EBoardMark [][] newBoard = ticTacToeService.getPlayerMove(1, 1, board);
		assertEquals(EBoardMark.CIRCLE, newBoard[1][1]);
	}
	
	private EBoardMark [][] testBoardMarkSet(){
		EBoardMark [][] board = new EBoardMark [3][3];
		board[0][0] = EBoardMark.EMPTY;
		board[0][1] = EBoardMark.CROSS;
		board[0][2] = EBoardMark.EMPTY;
		board[1][0] = EBoardMark.CROSS;
		board[1][1] = EBoardMark.CIRCLE;
		board[1][2] = EBoardMark.EMPTY;
		board[2][0] = EBoardMark.EMPTY;
		board[2][2] = EBoardMark.EMPTY;
		board[2][2] = EBoardMark.CIRCLE;
		
		return board;
	}
}
