package board;

public class Pieces {

	protected Position position;
	private ChessBoard board;
	
	public Pieces(ChessBoard board) {
		this.board = board;
	}

	protected ChessBoard getBoard() {
		return board;
	}
	
}
