package Pieces;

import Board.ChessBoard;
import Board.Position;
import Board.Square;

import static java.lang.Math.abs;

public class Pawn extends Piece {
    private boolean enPassantPossible;
    private boolean lastStateOfFirstMove;

    public Pawn(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.PAWN, position);
        enPassantPossible = false;
        lastStateOfFirstMove = false;
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
        Piece pieceOnNewPositionSquare = boardSquares[newPositionX][newPositionY].getPiece();

        if (currentPositionX == newPositionX)
            return canMoveForward(currentPositionY, boardSquares[newPositionX], newPositionY);

        if (abs(currentPositionX-newPositionX)==1){
            if(canTakeDiagonally(currentPositionY, pieceOnNewPositionSquare, newPositionY))
                return true;
            else if (canEnPassant(currentPositionY, newPositionX, newPositionY, boardSquares, pieceOnNewPositionSquare))
                return true;
        }

        return false;
    }

    @Override
    public boolean isTakePossible(Position newPosition, ChessBoard chessBoard){
        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();

        if (abs(currentPositionX-newPositionX)==1) {
            return canMoveDiagonally(currentPositionY, newPositionY);
        }

        return false;
    }


    private boolean canTakeDiagonally(int currentPositionY, Piece pieceOnNewPositionSquare, int newPositionY) {
        if (pieceOnNewPositionSquare != null)
            if (canMoveDiagonally(currentPositionY, newPositionY))
                return true;

        return false;
    }

    private boolean canEnPassant(int currentPositionY, int newPositionX, int newPositionY, Square[][] boardSquares, Piece pieceOnNewPositionSquare) {
        if (pieceOnNewPositionSquare == null){
            Piece pieceNextToPawn = boardSquares[newPositionX][currentPositionY].getPiece();
            if (pieceNextToPawn instanceof Pawn && getPlayerColor() != pieceNextToPawn.getPlayerColor() && ((Pawn) pieceNextToPawn).isEnPassantPossible())
                if (canMoveDiagonally(currentPositionY, newPositionY))
                    return true;
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

    private boolean canMoveDiagonally(int currentPositionY, int newPositionY) {
        if (getPlayerColor() == PlayerColor.WHITE) {
            if (newPositionY == currentPositionY + 1) {
                return true;
            }
        } else {
            if (newPositionY == currentPositionY - 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnPassantPossible() {
        return enPassantPossible;
    }

    public void setEnPassantPossible(boolean enPassantPossible) {
        this.enPassantPossible = enPassantPossible;
    }

    public boolean isLastStateOfFirstMove() {
        return lastStateOfFirstMove;
    }

    public void setLastStateOfFirstMove(boolean lastStateOfFirstMove) {
        this.lastStateOfFirstMove = lastStateOfFirstMove;
    }
}
