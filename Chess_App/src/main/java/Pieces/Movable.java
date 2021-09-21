package Pieces;

import Board.Board;
import Board.Position;

public interface Movable {
    boolean isMoveValid(Position newPosition, Board board);
    void move(Position newPosition, Board board);
}
