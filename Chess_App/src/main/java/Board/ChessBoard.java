package Board;

import Pieces.*;

import java.util.HashSet;
import java.util.Set;

public class ChessBoard {
    public static final int COLUMNS_AMOUNT = 8;
    public static final int ROWS_AMOUNT = 8;
    private Square[][] boardSquares;
    private Position blackKingPosition, whiteKingPosition;
    private Set<Position> setOfSquaresPositionsWhitesControl = new HashSet<>();
    private Set<Position> setOfSquaresPositionsBlacksControl = new HashSet<>();

    public ChessBoard() {
        boardSquares = new Square[COLUMNS_AMOUNT][ROWS_AMOUNT];
        initialize();
        blackKingPosition = new Position (3, 7);
        whiteKingPosition = new Position (3, 0);

    }

    public void initialize() {
        for(int i=0; i<COLUMNS_AMOUNT; i++){
            for (int j = 0; j < ROWS_AMOUNT; j++) {
                boardSquares[i][j] = new Square();
            }
        }
        addPiecesToBoard();
    }

    private void addPiecesToBoard() {
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

    private void updateSetsOfSquaresPlayersControl() {
        setOfSquaresPositionsWhitesControl.clear();
        setOfSquaresPositionsBlacksControl.clear();
        for(int xCurrent = 0; xCurrent<COLUMNS_AMOUNT; xCurrent++){
            for(int yCurrent = 0; yCurrent<ROWS_AMOUNT; yCurrent++){
                if(boardSquares[xCurrent][yCurrent].getPiece() == null)
                    continue;
                var pieceOnSquare = boardSquares[xCurrent][yCurrent].getPiece();

                for (int xToMove = 0; xToMove < COLUMNS_AMOUNT; xToMove++) {
                    for (int yToMove = 0; yToMove < ROWS_AMOUNT; yToMove++) {
                        if(pieceOnSquare.isTakePossible(new Position(xToMove, yToMove), this))
                            if(pieceOnSquare.getPlayerColor()==PlayerColor.WHITE){
                                setOfSquaresPositionsWhitesControl.add(new Position(xToMove, yToMove));
                            }
                            else {
                                setOfSquaresPositionsBlacksControl.add(new Position(xToMove, yToMove));
                            }
                    }
                }
            }
        }
    }

    public Square[][] getBoardSquares() {
        return boardSquares;
    }

    public void setBoardSquares(Square[][] boardSquares) {
        this.boardSquares = boardSquares;
        updateSetsOfSquaresPlayersControl();
    }

    public Position getBlackKingPosition() {
        return blackKingPosition;
    }

    public void setBlackKingPosition(Position newBlackKingPosition) {
        blackKingPosition = newBlackKingPosition;
    }

    public Position getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public void setWhiteKingPosition(Position newWhiteKingPosition) {
        whiteKingPosition = newWhiteKingPosition;
    }

    public Set<Position> getSetOfSquaresPositionsWhitesControl() {
        return setOfSquaresPositionsWhitesControl;
    }

    public Set<Position> getSetOfSquaresPositionsBlacksControl() {
        return setOfSquaresPositionsBlacksControl;
    }

    public boolean isWhiteKingSafe(){
        for (Position position:setOfSquaresPositionsBlacksControl) {
            if(position.equals(whiteKingPosition))
                return false;
        }

        return true;
    }

    public boolean isBlackKingSafe() {
        for (Position position:setOfSquaresPositionsWhitesControl) {
            if(position.equals(blackKingPosition))
                return false;
        }

        return true;
    }

    public void setWhiteMove() {
        Piece.setWhiteMove(true);
    }
}
