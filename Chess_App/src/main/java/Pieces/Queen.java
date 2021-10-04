package Pieces;

import Board.Board;
import Board.Position;
import Board.Square;

import static java.lang.Math.abs;

public class Queen extends Piece {

    public Queen(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.QUEEN, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {
        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        var boardSquares = board.getBoardSquares();

        if(currentPositionX == newPositionX)
            return canMoveVertically(currentPositionX, currentPositionY, newPositionY, boardSquares);

        if(currentPositionY == newPositionY)
            return canMoveHorizontally(currentPositionX, currentPositionY, newPositionX, boardSquares);

        if(abs(currentPositionX-newPositionX)==abs(currentPositionY-newPositionY))
            return canMoveDiagonally(currentPositionX, currentPositionY, newPositionX, newPositionY, boardSquares);

        return false;
    }

    private boolean canMoveHorizontally(int currentPositionX, int currentPositionY, int newPositionX, Square[][] boardSquares) {
        if(currentPositionX < newPositionX)
            for(int xPosition = currentPositionX +1; xPosition < newPositionX; xPosition++){
                if(boardSquares[xPosition][currentPositionY].getPiece()!=null)
                    return false;
            }
        else if(currentPositionX > newPositionX)
            for(int xPosition = currentPositionX -1; xPosition > newPositionX; xPosition--){
                if(boardSquares[xPosition][currentPositionY].getPiece()!=null)
                    return false;
            }

        return true;
    }

    private boolean canMoveVertically(int currentPositionX, int currentPositionY, int newPositionY, Square[][] boardSquares) {
        if(currentPositionY < newPositionY)
            for(int yPosition = currentPositionY +1; yPosition < newPositionY; yPosition++){
                if(boardSquares[currentPositionX][yPosition].getPiece()!=null){
                    return false;
                }
            }
        else if(currentPositionY > newPositionY)
            for(int yPosition = currentPositionY -1; yPosition > newPositionY; yPosition--){
                if(boardSquares[currentPositionX][yPosition].getPiece()!=null){
                    return false;
                }
            }

        return true;
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
