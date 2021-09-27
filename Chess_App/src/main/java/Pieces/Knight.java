package Pieces;

import Board.Board;
import Board.Position;

public class Knight extends Piece {

    public Knight(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.KNIGHT, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {

        return false;
    }
}
