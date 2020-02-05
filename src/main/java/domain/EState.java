package domain;

/**
 * @author Albin Shema
 * @FileName: EState.java
 * @Date: Jan 13, 2020
 * @ProjectName: tic-tac-toe-core
 */
public enum EState {
	COMPUTER_WIN("Computer Win"), PLAYER_WIN("Player Win"), NO_WIN("No Win");
	
	private String description;
	
	private EState(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
