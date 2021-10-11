package Pieces;

import Board.Board;
import Board.Position;

import static Pieces.PlayerColor.WHITE;
import static java.lang.Math.abs;

public class King extends Piece {
    private boolean castleDone;

    public King(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.KING, position);
        castleDone = false;
    }

    public boolean isCastleDone() {
        return castleDone;
    }

    public void setCastleDone() {
        this.castleDone = true;
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();

        if(canMoveToNeighborSquare(newPositionX, newPositionY)){
            if(getPlayerColor()==WHITE)
                board.setWhiteKingPosition(newPositionX, newPositionY);
            else
                board.setBlackKingPosition(newPositionX, newPositionY);

            return true;
        }

        return false;
    }

    private boolean canMoveToNeighborSquare(int newPositionX, int newPositionY) {
        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();

        return abs(currentPositionX - newPositionX) == 1 && abs(currentPositionY - newPositionY) == 1
                || abs(currentPositionX - newPositionX) == 1 && abs(currentPositionY - newPositionY) == 0
                || abs(currentPositionX - newPositionX) == 0 && abs(currentPositionY - newPositionY) == 1;
    }
}
