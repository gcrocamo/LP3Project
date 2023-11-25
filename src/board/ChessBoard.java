package board;

public class ChessBoard {

  private int rows, columns;
  private Pieces[][] pieces;

  public ChessBoard(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    pieces = new Pieces[rows][columns];
  }

  public int getRows() {
    return rows;
  }

<<<<<<< HEAD
  public void setRows(int rows) {
    this.rows = rows;
  }

  public int getColumns() {
    return columns;
  }

  public void setColumns(int columns) {
    this.columns = columns;
  }

  public Pieces pieces(int row, int column) {
    return pieces[row][column];
  }

  public Pieces pieces(Position position) {
    return pieces[position.getRow()][position.getColumn()];
  }

  public void placePiece(Pieces piece, Position position) {
    pieces[position.getRow()][position.getColumn()] = piece;
    piece.position = position;
  }
=======
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Pieces pieces(int row, int column) {
		return pieces[row][column];
	}
	 
	public Pieces pieces(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Pieces pieces, Position position) {
		pieces[position.getRow()][position.getColumn()] = pieces;
		pieces.position = position;
	}
>>>>>>> 2507fd78c01676d07987ae79bb9505fce6a4d593
}
