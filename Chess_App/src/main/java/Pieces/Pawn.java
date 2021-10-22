package Pieces;

import Board.ChessBoard;
import Board.Position;
import Board.Square;

import static java.lang.Math.abs;

public class Pawn extends Piece {
    public Pawn(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.PAWN, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, ChessBoard chessBoard) {
        if(isNewPositionSame(newPosition))
            return false;

        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        var boardSquares = chessBoard.getBoardSquares();

        if (currentPositionX == newPositionX)
            return canMoveForward(currentPositionY, boardSquares[newPositionX], newPositionY);

        if (abs(currentPositionX-newPositionX)==1) {
            return canTakeDiagonally(currentPositionY, boardSquares[newPositionX][newPositionY].getPiece(), newPositionY);
        }

        return false;
    }

    @Override
    public boolean isTakePossible(Position newPosition, ChessBoard chessBoard){
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        var boardSquares = chessBoard.getBoardSquares();

        return canTakeDiagonally(currentPositionY, boardSquares[newPositionX][newPositionY].getPiece(), newPositionY);
    }


    private boolean canTakeDiagonally(int currentPositionY, Piece pieceOnNewPositionSquare, int newPositionY) {
        if (pieceOnNewPositionSquare != null){
            if (getPlayerColor() == PlayerColor.WHITE) {
                if (newPositionY == currentPositionY + 1) {
                    return true;
                }
            } else {
                if (newPositionY == currentPositionY - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean canMoveForward(int currentPositionY, Square[] boardColumnSquares, int newPositionY) {
        Piece pieceOnNewPositionSquare = boardColumnSquares[newPositionY].getPiece();
        if (pieceOnNewPositionSquare == null)
            if (getPlayerColor() == PlayerColor.WHITE) {
                if (newPositionY == currentPositionY + 1) {
                    return true;
                }
                if (newPositionY == currentPositionY + 2 && !isFirstMoveDone()) {
                    Piece pieceBetweenPositions = boardColumnSquares[newPositionY - 1].getPiece();
                    if (pieceBetweenPositions == null) {
                        return true;
                    }
                }
            } else {
                if (newPositionY == currentPositionY - 1) {
                    return true;
                }
                if (newPositionY == currentPositionY - 2 && !isFirstMoveDone()) {
                    Piece pieceBetweenPositions = boardColumnSquares[newPositionY + 1].getPiece();
                    if (pieceBetweenPositions == null) {
                        return true;
                    }
                }
            }
        return false;
    }

}
