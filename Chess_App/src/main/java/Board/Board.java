package Board;

public class Board {
    private Square[][] boardSquares;

    public Board() {
        initialize();
    }

    private void initialize() {
//        boardSquares[0][0];
//        boardSquares[0][1];
//        boardSquares[0][2];
//        boardSquares[0][3];
//        boardSquares[0][4];
//        boardSquares[0][5];
//        boardSquares[0][6];
//        boardSquares[0][7];

    }

    public Square[][] getBoardSquares() {
        return boardSquares;
    }

    public void setBoardSquares(Square[][] boardSquares) {
        this.boardSquares = boardSquares;
    }
}
