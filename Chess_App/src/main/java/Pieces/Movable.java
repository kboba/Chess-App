package Pieces;

import Board.ChessBoard;
import Board.Position;

public interface Movable {
    boolean isMoveValid(Position newPosition, ChessBoard chessBoard);
    void move(Position newPosition, ChessBoard chessBoard);
}
