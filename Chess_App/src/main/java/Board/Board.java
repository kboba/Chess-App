package Board;

import Pieces.Pawn;
import Pieces.PlayerColor;

public class Board {
    private Square[][] boardSquares;

    public Board() {
        initialize();
    }

    private void initialize() {
        boardSquares[0][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(0, 1)));
        boardSquares[1][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(1, 1)));
        boardSquares[2][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(2, 1)));
        boardSquares[3][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(3, 1)));
        boardSquares[4][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(4, 1)));
        boardSquares[5][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(5, 1)));
        boardSquares[6][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(6, 1)));
        boardSquares[7][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(7, 1)));

        boardSquares[0][6].setPiece(new Pawn(PlayerColor.BLACK, new Position(0, 6)));
        boardSquares[1][6].setPiece(new Pawn(PlayerColor.BLACK, new Position(1, 6)));
        boardSquares[2][6].setPiece(new Pawn(PlayerColor.BLACK, new Position(2, 6)));
        boardSquares[3][6].setPiece(new Pawn(PlayerColor.BLACK, new Position(3, 6)));
        boardSquares[4][6].setPiece(new Pawn(PlayerColor.BLACK, new Position(4, 6)));
        boardSquares[5][6].setPiece(new Pawn(PlayerColor.BLACK, new Position(5, 6)));
        boardSquares[6][6].setPiece(new Pawn(PlayerColor.BLACK, new Position(6, 6)));
        boardSquares[7][6].setPiece(new Pawn(PlayerColor.BLACK, new Position(7, 6)));
    }

    public Square[][] getBoardSquares() {
        return boardSquares;
    }

    public void setBoardSquares(Square[][] boardSquares) {
        this.boardSquares = boardSquares;
    }
}
