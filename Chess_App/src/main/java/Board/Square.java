package Board;

import Game.Position;
import Pieces.Piece;

public class Square {
    private final SquareColor color;
    private final Position position;
    private boolean occupied;
    private Piece piece;

    public Square(SquareColor squareColor, Position position) {
        this.color = squareColor;
        this.position = position;
    }

    void occupy(){
        occupied = true;
    }

    void unoccupy(){
        occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
