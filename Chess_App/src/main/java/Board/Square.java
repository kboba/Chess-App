package Board;

import Pieces.Piece;

public class Square {
    private boolean occupied;
    private Piece piece;

    public Square(Piece piece, boolean occupied) {
        this.piece = piece;
        this.occupied = occupied;
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
