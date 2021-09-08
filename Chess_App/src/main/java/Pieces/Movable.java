package Pieces;

import Game.Position;

public interface Movable {
    void isMoveValid();
    void Move(Position newPosition);
}
