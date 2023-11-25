package chesspieces;

import board.ChessBoard;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

}