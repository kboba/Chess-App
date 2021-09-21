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

    public PlayerColor getPlayer() {
        return getPlayer();
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
     */
    @Override
    public void move(Position newPosition, Board board) {
        var boardSquares = board.getBoardSquares();
        boardSquares[getPosition().getX()][getPosition().getY()].unoccupy();
        setPosition(newPosition);
        boardSquares[newPosition.getX()][newPosition.getY()].setPiece(this);
        board.setBoardSquares(boardSquares);
    }
}
