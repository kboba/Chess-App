package Board;

import Pieces.*;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private static final int COLUMNS_AMOUNT = 8;
    private static final int ROWS_AMOUNT = 8;
    private Square[][] boardSquares;
    private Position blackKingPosition, whiteKingPosition;
    private Set<Square> setOfSquaresWhitesControl = new HashSet<>();
    private Set<Square> setOfSquaresBlacksControl = new HashSet<>();

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
        boardSquares[0][0].setPiece(new Rock(PlayerColor.WHITE, new Position(0, 0)));
        boardSquares[1][0].setPiece(new Knight(PlayerColor.WHITE, new Position(1, 0)));
        boardSquares[2][0].setPiece(new Bishop(PlayerColor.WHITE, new Position(2, 0)));
        boardSquares[3][0].setPiece(new King(PlayerColor.WHITE, new Position(3, 0)));
        boardSquares[4][0].setPiece(new Queen(PlayerColor.WHITE, new Position(4, 0)));
        boardSquares[5][0].setPiece(new Bishop(PlayerColor.WHITE, new Position(5, 0)));
        boardSquares[6][0].setPiece(new Knight(PlayerColor.WHITE, new Position(6, 0)));
        boardSquares[7][0].setPiece(new Rock(PlayerColor.WHITE, new Position(7, 0)));

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

        boardSquares[0][7].setPiece(new Rock(PlayerColor.BLACK, new Position(0, 7)));
        boardSquares[1][7].setPiece(new Knight(PlayerColor.BLACK, new Position(1, 7)));
        boardSquares[2][7].setPiece(new Bishop(PlayerColor.BLACK, new Position(2, 7)));
        boardSquares[3][7].setPiece(new King(PlayerColor.BLACK, new Position(3, 7)));
        boardSquares[4][7].setPiece(new Queen(PlayerColor.BLACK, new Position(4, 7)));
        boardSquares[5][7].setPiece(new Bishop(PlayerColor.BLACK, new Position(5, 7)));
        boardSquares[6][7].setPiece(new Knight(PlayerColor.BLACK, new Position(6, 7)));
        boardSquares[7][7].setPiece(new Rock(PlayerColor.BLACK, new Position(7, 7)));
    }

    public Square[][] getBoardSquares() {
        return boardSquares;
    }

    public void setBoardSquares(Square[][] boardSquares) {
        this.boardSquares = boardSquares;
        updateSetsOfSquaresPlayersControl();
    }

    private void updateSetsOfSquaresPlayersControl() {
        boolean isWhite;
        for(int x = 0; x<COLUMNS_AMOUNT; x++){
            for(int y=0; y<ROWS_AMOUNT; y++){
                if(boardSquares[x][y].getPiece() == null)
                    continue;
                var pieceOnSquare = boardSquares[x][y].getPiece();

                if(pieceOnSquare.getPlayerColor()==PlayerColor.WHITE){
                    //  add his valid moves to
                    //  listOfSquaresWhitesControl
                }
                else (pieceOnSquare.getPlayerColor()==PlayerColor.BLACK){
                    //  add his valid moves to
                    //  listOfSquaresBlacksControl
                }

            }
        }
    }

    public Position getBlackKingPosition() {
        return blackKingPosition;
    }

    public void setBlackKingPosition(Position newBlackKingPosition) {
        blackKingPosition = newBlackKingPosition;
    }

    public void setBlackKingPosition(int xPosition, int yPosition) {
        blackKingPosition = new Position(xPosition, yPosition);
    }


    public Position getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public void setWhiteKingPosition(Position newWhiteKingPosition) {
        newWhiteKingPosition = newWhiteKingPosition;
    }

    public void setWhiteKingPosition(int xPosition, int yPosition) {
        whiteKingPosition = new Position(xPosition, yPosition);
    }
}
