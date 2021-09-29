package Pieces;

import Board.Board;
import Board.Position;

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

        return false;
    }
}
