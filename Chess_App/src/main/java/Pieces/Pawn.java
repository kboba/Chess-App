package Pieces;

import Board.Board;
import Board.Position;

public class Pawn extends Piece {

    public Pawn(PlayerColor m_player, Position m_position) {
        super(m_player, PieceType.PAWN, m_position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {

        return false;
    }

    @Override
    public void Move(Position newPosition, Board board) {

    }
}
