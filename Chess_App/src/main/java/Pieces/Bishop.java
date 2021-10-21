package Pieces;

import Board.Board;
import Board.Position;
import Board.Square;

import static java.lang.Math.abs;

public class Bishop extends Piece {

    public Bishop(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.BISHOP, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {
        if(isNewPositionSame(newPosition))
            return false;

        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        var boardSquares = board.getBoardSquares();

        if(abs(currentPositionX-newPositionX)==abs(currentPositionY-newPositionY))
            return canMoveDiagonally(currentPositionX, currentPositionY, newPositionX, newPositionY, boardSquares);

        return false;
    }

    private boolean canMoveDiagonally(int currentPositionX, int currentPositionY, int newPositionX, int newPositionY, Square[][] boardSquares) {
        int xPosition = currentPositionX;
        int yPosition = currentPositionY;
        byte xDirection = getDirection(currentPositionX, newPositionX);
        byte yDirection = getDirection(currentPositionY, newPositionY);

        xPosition+=xDirection;
        yPosition+=yDirection;

        while(xPosition != newPositionX && yPosition != newPositionY){
            if(boardSquares[xPosition][yPosition].getPiece()!=null){
                return false;
            }
            xPosition+=xDirection;
            yPosition+=yDirection;
        }

        return true;
    }

    private byte getDirection(int currentPositionX, int newPositionX) {
        byte xDirection;
        if (currentPositionX < newPositionX)
            xDirection = 1;
        else
            xDirection = -1;
        return xDirection;
    }
}
