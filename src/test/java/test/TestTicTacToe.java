package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.EBoardMark;
import service.AITicTacToe;
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
	
	AITicTacToe ai = new AITicTacToe();	
	
	@Test
	public void testAvailableSpace() {
		// Test to check if it there is an available space
		EBoardMark [][] board = this.testBoardMarkSet();
		boolean result = ai.availableSpaceOnTheBoard(board);
		assertTrue(result);
	}
	
	@Test
	public void testNoAvailableSpace() {
		// Test to check if it there is an available space
		EBoardMark [][] board = this.testBoardMarkSet();
		board[0][0] = EBoardMark.CROSS;
		board[0][2] = EBoardMark.CIRCLE;
		board[1][2] = EBoardMark.CROSS;
		board[2][0] = EBoardMark.CIRCLE;
		board[2][2] = EBoardMark.CROSS;
		boolean result = ai.availableSpaceOnTheBoard(board);
		assertFalse(result);
	}
	
	@Test
	public void testCheckWinner() {
		// Test to check if there is a winner
		EBoardMark [][] board = this.testBoardMarkSet();
		board[0][0] = EBoardMark.CIRCLE;
		board[2][2] = EBoardMark.CIRCLE;
		int winner = ai.checkWinner(board);
		assertEquals(-1, winner);
	}
	
	@Test
	public void testGetBestMove() {
		// Test to get the best move
		EBoardMark [][] board = this.testBoardMarkSet();
		int[] result = ticTacToeService.getBestMove(board);
		assertNotNull(result);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetBestMoveNegative() {
		// Test to check if the get best move will throw the null pointer exception
		EBoardMark [][] board = null;
		ticTacToeService.getBestMove(board);
	}
	
	@Test
	public void testCheckPlayerWin() {
		// Test to check if the circle mark won the game
		EBoardMark [][] board = this.testBoardMarkSet();
		board[0][0] = EBoardMark.CIRCLE;
		board[2][2] = EBoardMark.CIRCLE;
		EBoardMark winner = ticTacToeService.checkTheWinner(board);
		assertEquals(EBoardMark.CIRCLE, winner);
	}
	
	@Test
	public void testCheckDraw() {
		// Test to check if there is a draw
		EBoardMark [][] board = this.testBoardMarkSet();
		EBoardMark winner = ticTacToeService.checkTheWinner(board);
		assertEquals(EBoardMark.EMPTY, winner);
	}
	
	@Test
	public void testCheckComputerWin() {
		// Test to check if the cross mark won the game
		EBoardMark [][] board = this.testBoardMarkSet();
		board[0][0] = EBoardMark.CROSS;
		board[0][2] = EBoardMark.CROSS;
		EBoardMark winner = ticTacToeService.checkTheWinner(board);
		assertEquals(EBoardMark.CROSS, winner);
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
