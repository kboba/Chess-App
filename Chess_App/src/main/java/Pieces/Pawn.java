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

    /*
     * move method takes Position and Board
     * 0. get board representation (Square[][])
     * 1. unocupy old position
     * 2. set new position
     * 3. set piece on new position on board square
     * 4. update board representation
     */
    @Override
    public void Move(Position newPosition, Board board) {
        var boardSquares = board.getBoardSquares();
        boardSquares[getPosition().getX()][getPosition().getY()].unoccupy();
        setPosition(newPosition);
        boardSquares[newPosition.getX()][newPosition.getY()].setPiece(this);
        board.setBoardSquares(boardSquares);
    }
}
