package Pieces;

import Board.Board;
import Board.Position;

public class Rock extends Piece {

    public Rock(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.ROCK, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {

        return false;
    }
}
