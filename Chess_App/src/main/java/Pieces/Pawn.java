package Pieces;

import Board.Board;
import Board.Position;
import Board.Square;

import static java.lang.Math.abs;

public class Pawn extends Piece {
    private boolean firstMoveDone;

    public Pawn(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.PAWN, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {
        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        var boardSquares = board.getBoardSquares();

        if (currentPositionX == newPositionX)
            return canPawnMoveForward(currentPositionY, boardSquares[newPositionX], newPositionY);

        if (abs(currentPositionX-newPositionX)==1) {
            return canPawnTakeDiagonally(currentPositionY, boardSquares[newPositionX][newPositionY].getPiece(), newPositionY);
        }

        return false;
    }


    private boolean canPawnTakeDiagonally(int currentPositionY, Piece pieceOnNewPositionSquare, int newPositionY) {
        if (pieceOnNewPositionSquare != null){
            if (getPlayerColor() == PlayerColor.WHITE) {
                if (newPositionY == currentPositionY + 1) {
                    firstMoveDone = true;
                    return true;
                }
            } else {
                if (newPositionY == currentPositionY - 1) {
                    firstMoveDone = true;
                    return true;
                }
            }
        }
        return false;
    }


    private boolean canPawnMoveForward(int currentPositionY, Square[] boardColumnSquares, int newPositionY) {
        Piece pieceOnNewPositionSquare = boardColumnSquares[newPositionY].getPiece();
        if (pieceOnNewPositionSquare == null)
            if (getPlayerColor() == PlayerColor.WHITE) {
                if (newPositionY == currentPositionY + 1) {
                    firstMoveDone = true;
                    return true;
                }
                if (newPositionY == currentPositionY + 2 && !firstMoveDone) {
                    Piece pieceBetweenPositions = boardColumnSquares[newPositionY - 1].getPiece();
                    if (pieceBetweenPositions == null) {
                        firstMoveDone = true;
                        return true;
                    }
                }
            } else {
                if (newPositionY == currentPositionY - 1) {
                    firstMoveDone = true;
                    return true;
                }
                if (newPositionY == currentPositionY - 2 && !firstMoveDone) {
                    Piece pieceBetweenPositions = boardColumnSquares[newPositionY + 1].getPiece();
                    if (pieceBetweenPositions == null) {
                        firstMoveDone = true;
                        return true;
                    }
                }
            }
        return false;
    }

}
