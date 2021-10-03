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
        Square[][] boardSquares = board.getBoardSquares();

        if (currentPositionX == newPositionX)
            return cavPawnMoveForward(currentPositionY, boardSquares[newPositionX], newPositionY);

        return false;
    }

    private boolean cavPawnMoveForward(int currentPositionY, Square[] boardSquare, int newPositionY) {
        Piece pieceOnNewPositionSquare = boardSquare[newPositionY].getPiece();
        if (pieceOnNewPositionSquare == null)
            if (getPlayerColor() == PlayerColor.WHITE) {
                if (newPositionY == currentPositionY + 1) {
                    firstMoveDone = true;
                    return true;
                }
                if (newPositionY == currentPositionY + 2 && !firstMoveDone) {
                    Piece pieceBetweenPositions = boardSquare[newPositionY - 1].getPiece();
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
                    Piece pieceBetweenPositions = boardSquare[newPositionY + 1].getPiece();
                    if (pieceBetweenPositions == null) {
                        firstMoveDone = true;
                        return true;
                    }
                }
            }

        return false;
    }

}
