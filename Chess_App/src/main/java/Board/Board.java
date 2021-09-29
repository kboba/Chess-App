package Board;

import Pieces.King;
import Pieces.Pawn;
import Pieces.PlayerColor;

public class Board {
    private Square[][] boardSquares;
    private Position blackKingPosition, whiteKingPosition;

    public Board() {
        boardSquares = new Square[8][8];
        initialize();
        blackKingPosition = new Position (3, 0);
        whiteKingPosition = new Position (3, 7);
    }

    private void initialize() {

        for(int i=0; i<8; i++){
            for (int j = 0; j < 8; j++) {
                boardSquares[i][j] = new Square();
            }
        }
//        boardSquares[0][1] = new Square(new Pawn(PlayerColor.WHITE, new Position(0, 1)), true);

        boardSquares[3][0].setPiece(new King(PlayerColor.WHITE, new Position(3, 0)));

        boardSquares[0][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(0, 1)));
        boardSquares[1][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(1, 1)));
        boardSquares[2][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(2, 1)));
        boardSquares[3][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(3, 1)));
        boardSquares[4][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(4, 1)));
        boardSquares[5][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(5, 1)));
        boardSquares[6][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(6, 1)));
        boardSquares[7][1].setPiece(new Pawn(PlayerColor.WHITE, new Position(7, 1)));



        boardSquares[3][7].setPiece(new King(PlayerColor.BLACK, new Position(3, 7)));

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

    public Position getBlackKingPosition() {
        return blackKingPosition;
    }

    public void setBlackKingPosition(Position blackKingPosition) {
        this.blackKingPosition = blackKingPosition;
    }

    public Position getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public void setWhiteKingPosition(Position whiteKingPosition) {
        this.whiteKingPosition = whiteKingPosition;
    }
}
