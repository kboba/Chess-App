package Pieces;

import Board.Board;
import Board.Position;

public class Queen extends Piece {

    public Queen(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.QUEEN, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {

        return false;
    }
}
