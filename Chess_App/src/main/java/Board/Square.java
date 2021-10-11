package Board;

import Pieces.Piece;

public class Square {
    private Piece piece;

    public Square(){}

    public Square(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
