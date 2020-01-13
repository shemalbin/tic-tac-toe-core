package domain;

/**
 * @author Albin Shema
 * @FileName: EState.java
 * @Date: Jan 13, 2020
 * @ProjectName: tic-tac-toe-core
 */
public enum EState {
	PLAYING("Playing"), DRAW("Draw"), CROSS_WIN("Cross Win"), CIRCLE_WIN("Circle Win");
	
	private String description;
	
	private EState(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
