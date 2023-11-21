package chess;

import board.ChessBoard;

public class ChessMatch {
	
	private ChessBoard board;
	
	public ChessMatch() {
		board = new ChessBoard(8, 8);
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.pieces(i, j);
			}
		}
		return mat;
	}
}
