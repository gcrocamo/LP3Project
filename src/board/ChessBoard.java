package board;

public class ChessBoard {

  private int rows, columns;
  private Pieces[][] pieces;

  public ChessBoard(int rows, int columns) {
    if(rows < 1 || columns < 1){
      throw new BoardException("Board must have at least 1 row and 1 column");
    }
    this.rows = rows;
    this.columns = columns;
    pieces = new Pieces[rows][columns];
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public Pieces pieces(int row, int column) {
    if(!positionExists(row, column)) {
      throw new BoardException("Position isnt in the board");
    }
    return pieces[row][column];
  }

  public Pieces pieces(Position position) {
    if(!positionExists(position)) {
      throw new BoardException("Position isnt in the board");
    }
    return pieces[position.getRow()][position.getColumn()];
  }

  public void placePiece(Pieces piece, Position position) {
    if(thereIsAPiece(position)) {
      throw new BoardException("Already exist a piece in this position" + position);
    }
    pieces[position.getRow()][position.getColumn()] = piece;
    piece.position = position;
  }

  private boolean positionExists(int row, int column) {
    return row >= 0 && row < rows && column >= 0 && column < columns;
  }
  
  public boolean positionExists(Position   position) {
  return positionExists(position.getRow(), position.getColumn());
  }

  public boolean thereIsAPiece(Position position) {
    if(!positionExists(position)) {
      throw new BoardException("Position isnt in the board");
    }
    return pieces(position) != null;
  }
}
