package Pieces;

import Board.Board;
import Board.Position;

public class King extends Piece {

    public King(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.KING, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {

        return false;
    }
}
