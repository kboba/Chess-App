package Board;

import Pieces.Piece;

public class Square {
    private boolean occupied;
    private Piece piece;

    public Square(Piece piece, boolean occupied) {
        this.piece = piece;
        this.occupied = occupied;
    }


    private void occupy(){
        occupied = true;
    }

    public void unoccupy(){
        occupied = false;
        piece = null;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        occupy();
    }
}
