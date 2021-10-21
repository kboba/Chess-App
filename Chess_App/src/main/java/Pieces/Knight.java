package Pieces;

import Board.Board;
import Board.Position;

import static java.lang.Math.abs;

public class Knight extends Piece {

    public Knight(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.KNIGHT, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {
        if(isNewPositionSame(newPosition))
            return false;

        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        
        return canJump(currentPositionX, currentPositionY, newPositionX, newPositionY);
    }

    private boolean canJump(int currentPositionX, int currentPositionY, int newPositionX, int newPositionY) {
        if(abs(currentPositionX - newPositionX)==2 && abs(currentPositionY - newPositionY)==1)
            return true;
        else if(abs(currentPositionX - newPositionX)==1 && abs(currentPositionY - newPositionY)==2)
            return true;

        return false;
    }
}
