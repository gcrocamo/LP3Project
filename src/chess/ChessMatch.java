package chess;

import board.ChessBoard;
import board.Position;
<<<<<<< HEAD
import chesspieces.Rook;
import chesspieces.King;
=======
import chess.pieces.Rook;
import chess.pieces.King;
>>>>>>> 2507fd78c01676d07987ae79bb9505fce6a4d593

public class ChessMatch {
	
	private ChessBoard board;
	
	public ChessMatch() {
		board = new ChessBoard(8, 8);
		initialSetup();
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

<<<<<<< HEAD
  private void placeNewPiece(char column, int row, ChessPiece piece) {
    board.placePiece(piece, new ChessPosition(column, row).toPosition());
  }
  
  private void initialSetup() {
		placeNewPiece('b', 9, new Rook(board, Color.WHITE));
		placeNewPiece('a', 1, new King(board, Color.BLACK));
		placeNewPiece('d', 4, new King(board, Color.WHITE));
=======
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
>>>>>>> 2507fd78c01676d07987ae79bb9505fce6a4d593
	}
}
//changes in ChessMatch, ChessException, ChessPosition, BoardException