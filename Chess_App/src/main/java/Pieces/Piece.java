package Pieces;

import Board.Board;
import Board.Position;

abstract public class Piece implements Movable {
    private final PlayerColor playerColor;
    private final PieceType type;
    private Position position;

    public Piece(PlayerColor playerColor, PieceType type, Position position) {
        this.playerColor = playerColor;
        this.type = type;
        this.position = position;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public PieceType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /*
     * move method takes Position and Board
     * 0. get board representation (Square[][])
     * 1. unocupy old position
     * 2. set new position
     * 3. set piece on new position on board square
     * 4. update board representation
     * 5. if Piece is King then set variable with his position
     */
    @Override
    public void move(Position newPosition, Board board) {
        var boardSquares = board.getBoardSquares();
        boardSquares[getPosition().getX()][getPosition().getY()].setPiece(null);
        setPosition(newPosition);
        boardSquares[newPosition.getX()][newPosition.getY()].setPiece(this);
        board.setBoardSquares(boardSquares);

        if (type == PieceType.KING) {
            if (playerColor==PlayerColor.WHITE) {
                board.setWhiteKingPosition(newPosition);
            } else{
                board.setBlackKingPosition(newPosition);
            }
        }
    }
}
