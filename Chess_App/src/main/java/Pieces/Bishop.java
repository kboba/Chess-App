package Pieces;

import Board.Board;
import Board.Position;

public class Bishop extends Piece {

    public Bishop(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.BISHOP, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {

        return false;
    }
}
