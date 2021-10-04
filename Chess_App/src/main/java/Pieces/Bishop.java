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


        if(abs(currentPositionX-newPositionX)==abs(currentPositionY-newPositionY)) {
            int xPosition = currentPositionX;
            int yPosition = currentPositionY;
            byte xDirection = 0;
            byte yDirection = 0;
            if(currentPositionX < newPositionX && currentPositionY < newPositionY) {
                xDirection = 1;
                yDirection = 1;
            }
            else if(currentPositionX < newPositionX && currentPositionY > newPositionY){
                xDirection = 1;
                yDirection =-1;
            }
            else if(currentPositionX > newPositionX && currentPositionY > newPositionY){
                xDirection =-1;
                yDirection =-1;
            }
            else if(currentPositionX > newPositionX && currentPositionY < newPositionY){
                xDirection =-1;
                yDirection = 1;
            }

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

        return false;
    }

}
