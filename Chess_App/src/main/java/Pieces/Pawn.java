package Pieces;

import Board.Board;
import Board.Position;

public class Pawn extends Piece {

    public Pawn(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.PAWN, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {

        return false;
    }

    @Override
    public void Move(Position newPosition, Board board) {

    }
}
