package Pieces;

import Board.ChessBoard;
import Board.Position;

abstract public class Piece implements Movable {
    private final PlayerColor playerColor;
    private final PieceType type;
    private Position position;
    private boolean firstMoveDone;

    public Piece(PlayerColor playerColor, PieceType type, Position position) {
        this.playerColor = playerColor;
        this.type = type;
        this.position = position;
        firstMoveDone = false;
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
    public void move(Position newPosition, ChessBoard chessBoard) {
        var boardSquares = chessBoard.getBoardSquares();
        boardSquares[getPosition().getX()][getPosition().getY()].setPiece(null);
        setPosition(newPosition);
        boardSquares[newPosition.getX()][newPosition.getY()].setPiece(this);
        chessBoard.setBoardSquares(boardSquares);
        firstMoveDone = true;

        if (type == PieceType.KING) {
            if (playerColor==PlayerColor.WHITE) {
                chessBoard.setWhiteKingPosition(newPosition);
            } else{
                chessBoard.setBlackKingPosition(newPosition);
            }
        }

    }

    public abstract boolean isTakePossible();

    public boolean isNewPositionSame(Position newPosition){
        return position.equals(newPosition);
    }

    public boolean isFirstMoveDone() {
        return firstMoveDone;
    }
}
