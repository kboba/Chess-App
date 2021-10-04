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
        byte xDirection = 0;
        byte yDirection = 0;

        if(currentPositionX < newPositionX)
            xDirection = 1;
        else
            xDirection = -1;

        if(currentPositionY < newPositionY)
            yDirection = 1;
        else
            yDirection = -1;


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

}
