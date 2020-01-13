package domain;

/**
 * @author Albin Shema
 * @FileName: EBoardMark.java
 * @Date: Jan 13, 2020
 * @ProjectName: tic-tac-toe-core
 */
public enum EBoardMark {
	EMPTY("Empty"), CROSS("Cross"), CIRCLE("Circle");
	
	private String description;

	private EBoardMark(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}