package Pieces;

import Board.Board;
import Board.Position;

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

        if(getPlayerColor()==PlayerColor.WHITE){
            if(newPositionY==currentPositionY+1) {
                firstMoveDone = true;
                return true;
            }
            if(newPositionY==currentPositionY+2 && !firstMoveDone) {
                firstMoveDone = true;
                return true;
            }
        } else {
            if(newPositionY==currentPositionY-1) {
                firstMoveDone = true;
                return true;
            }
            if(newPositionY==currentPositionY-2 && !firstMoveDone) {
                firstMoveDone = true;
                return true;
            }
        }

        return false;
    }
}
