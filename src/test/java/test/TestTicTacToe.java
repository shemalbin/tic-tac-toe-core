package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.EBoardMark;
import domain.EState;
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
		board[2][1] = EBoardMark.CROSS;
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
		EState winner = ai.checkWinner(board, EBoardMark.CIRCLE);
		assertEquals(EState.PLAYER_WIN, winner);
	}
	
	@Test
	public void testGetBestMoveToBlockAUserWin() {
		// Test to get the best move
		// If the AI doesn't play on [0,2] the user will win
		// Test to check if the AI will block the user
		EBoardMark [][] board = this.testBoardMarkSet();
		board[0][0] = EBoardMark.CROSS;
		int[] result = ticTacToeService.getBestMove(board, EBoardMark.CROSS);
		assertEquals("[0, 2]",Arrays.toString(result));
	}
	
	@Test
	public void testGetBestMoveForAComputerWin() {
		// Test to get the best move
		// The AI will have to play on [0, 0] for the computer to win	
		EBoardMark [][] board = this.testBoardMarkSet();
		int[] result = ticTacToeService.getBestMove(board, EBoardMark.CROSS);
		assertEquals("[0, 0]",Arrays.toString(result));
	}
	
	@Test
	public void testGetBestMoveBoardFull() {
		// Test to get the best move
		// The AI will have to play on [0, 0] for the computer to win	
		EBoardMark [][] board = this.testBoardMarkSet();
		board[0][0] = EBoardMark.CROSS;
		board[0][2] = EBoardMark.CIRCLE;
		board[1][2] = EBoardMark.CROSS;
		board[2][0] = EBoardMark.CIRCLE;
		board[2][1] = EBoardMark.CROSS;
		int[] result = ticTacToeService.getBestMove(board, EBoardMark.CROSS);
		assertEquals("[-100, -100]",Arrays.toString(result));
	}
	
	@Test
	public void testGetBestMoveNegative() {
		// Test to check if the get best move will throw the null pointer exception
		EBoardMark [][] board = null;
		ticTacToeService.getBestMove(board, EBoardMark.CIRCLE);
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
		board[2][1] = EBoardMark.EMPTY;
		board[2][2] = EBoardMark.CIRCLE;
		
		return board;
	}
}
