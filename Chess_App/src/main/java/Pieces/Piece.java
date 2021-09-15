package Pieces;

import Game.Position;

abstract public class Piece implements Movable {
    private final PlayerColor m_player;
    private final PieceType m_type;
    private Position m_position;


    public Piece(PlayerColor m_player, PieceType m_type, Position m_position) {
        this.m_player = m_player;
        this.m_type = m_type;
        this.m_position = m_position;
    }

    public PieceType getType() {
        return m_type;
    }

    public PlayerColor getPlayer() {
        return getPlayer();
    }
}
