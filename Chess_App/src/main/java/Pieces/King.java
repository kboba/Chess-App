package Pieces;

import Board.ChessBoard;
import Board.Position;

import java.util.Set;

import static java.lang.Math.abs;

public class King extends Piece {

    public King(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.KING, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, ChessBoard chessBoard) {
        if(isNewPositionSame(newPosition))
            return false;

        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        if(canMoveToNeighborSquare(newPositionX, newPositionY))
            return true;
        return false;
    }

    @Override
    public boolean isTakePossible(Position newPosition, ChessBoard chessBoard){
        return isMoveValid(newPosition, chessBoard);
    }

    public boolean isCastlePossible(Position newPosition, ChessBoard chessBoard){
        if (isFirstMoveDone() || allyKingAreNotSafe(chessBoard))
            return false;

        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        var boardSquares = chessBoard.getBoardSquares();
        Piece pieceOnNewPosition = boardSquares[newPositionX][newPositionY].getPiece();
        // if piece on new position is not null
        if(pieceOnNewPosition != null){
            // if piece on new position have not moved
            // and is on same line with king then continue
            // else return false
            if(!pieceOnNewPosition.isFirstMoveDone() && currentPositionY == newPositionY){
                // if player is white get set of blacks control
                // if player is black get set of whites control
                Set<Position> setOfSquaresPositionsEnemyControl;
                if(getPlayerColor()==PlayerColor.WHITE) {
                    setOfSquaresPositionsEnemyControl = chessBoard.getSetOfSquaresPositionsBlacksControl();
                }
                else {
                    setOfSquaresPositionsEnemyControl = chessBoard.getSetOfSquaresPositionsWhitesControl();
                }

                // Check if:
                // 1. Positions between King and Rock are attacked
                // 2. There is nothing between King and Rock
                byte directionOfMove = (byte) (currentPositionX < newPositionX ? 1 : -1);
                for (int x = 0; x <= abs(currentPositionX-newPositionX); x++){
                    if (setOfSquaresPositionsEnemyControl.contains(new Position[currentPositionX+x*directionOfMove][currentPositionY]))
                        return false;
                }
                for (int x = 1; x < abs(currentPositionX-newPositionX); x++){
                    if (boardSquares[currentPositionX+x*directionOfMove][currentPositionY].getPiece() != null)
                        return false;
                }
                return true;
            }
        }

        return false;
    }

    public void castle(Position newPosition, ChessBoard chessBoard){
        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        var boardSquares = chessBoard.getBoardSquares();
        Piece pieceOnNewPosition = boardSquares[newPositionX][newPositionY].getPiece();

        byte directionOfMove = (byte) (currentPositionX < newPositionX ? 1 : -1);
        boardSquares[currentPositionX+2*directionOfMove][newPositionY].setPiece(this);
        boardSquares[currentPositionX+2*directionOfMove-directionOfMove][newPositionY].setPiece(pieceOnNewPosition);
        if(getPlayerColor()==PlayerColor.WHITE)
            chessBoard.setWhiteKingPosition(new Position(currentPositionX+2*directionOfMove, newPositionY));
        else
            chessBoard.setBlackKingPosition(new Position(currentPositionX+2*directionOfMove, newPositionY));
        boardSquares[currentPositionX][currentPositionY].setPiece(null);
        boardSquares[newPositionX][newPositionY].setPiece(null);
        chessBoard.setBoardSquares(boardSquares);
    }

    private boolean canMoveToNeighborSquare(int newPositionX, int newPositionY) {
        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();

        return abs(currentPositionX - newPositionX) == 1 && abs(currentPositionY - newPositionY) == 1
                || abs(currentPositionX - newPositionX) == 1 && abs(currentPositionY - newPositionY) == 0
                || abs(currentPositionX - newPositionX) == 0 && abs(currentPositionY - newPositionY) == 1;
    }
}
