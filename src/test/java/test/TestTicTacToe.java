package test;
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
		TicTacToe ticTacToe = new TicTacToe();
		TicTacToe ticTacToeResult = ticTacToeService.initializeTheBoard(ticTacToe);
		assertEquals(EState.PLAYING, ticTacToeResult.getGameStatus());
		assertEquals(EBoardMark.CROSS, ticTacToeResult.getBoardMark());
	}
	
	@Test(expected = NullPointerException.class)
	public void testInitializeBoardNegative() {
		TicTacToe ticTacToe = null;
		TicTacToe ticTacToeResult = ticTacToeService.initializeTheBoard(ticTacToe);
		assertEquals(EState.PLAYING, ticTacToeResult.getGameStatus());
	}
}
