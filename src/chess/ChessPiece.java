package chess;

import board.ChessBoard;
import board.Pieces;

public class ChessPiece extends Pieces{

	private Color color;

	public ChessPiece(ChessBoard board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
		
}
