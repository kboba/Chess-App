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
        int xPositionBeforeMove = getPosition().getX();
        int yPositionBeforeMove = getPosition().getY();
        int xNewPosition = newPosition.getX();
        int yNewPosition = newPosition.getY();
        Piece pieceOnNewPosition = boardSquares[xNewPosition][yNewPosition].getPiece();

        boardSquares[xPositionBeforeMove][yPositionBeforeMove].setPiece(null);
        setPosition(newPosition);
        boardSquares[xNewPosition][yNewPosition].setPiece(this);
        chessBoard.setBoardSquares(boardSquares);


        if (type == PieceType.KING) {
            if (playerColor==PlayerColor.WHITE) {
                chessBoard.setWhiteKingPosition(newPosition);
            } else{
                chessBoard.setBlackKingPosition(newPosition);
            }
        }

        if (kingsAreNotSafe(chessBoard)) {
            boardSquares[xNewPosition][yNewPosition].setPiece(pieceOnNewPosition);
            setPosition(new Position(xPositionBeforeMove, yPositionBeforeMove));
            boardSquares[xPositionBeforeMove][yPositionBeforeMove].setPiece(this);
            chessBoard.setBoardSquares(boardSquares);
        }

        firstMoveDone = true;
    }

    public abstract boolean isTakePossible(Position newPosition, ChessBoard chessBoard);

    private boolean kingsAreNotSafe(ChessBoard chessBoard) {
        return (playerColor == PlayerColor.WHITE && !chessBoard.isWhiteKingSafe()) || (playerColor == PlayerColor.BLACK && !chessBoard.isBlackKingSafe());
    }

    public boolean isNewPositionSame(Position newPosition){
        return position.equals(newPosition);
    }

    public boolean isFirstMoveDone() {
        return firstMoveDone;
    }
}
