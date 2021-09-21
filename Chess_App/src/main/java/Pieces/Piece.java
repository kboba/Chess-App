package Pieces;

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
}
