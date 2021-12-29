package Pieces;

import Board.ChessBoard;
import Board.Position;

abstract public class Piece implements Movable {
    private final PlayerColor playerColor;
    private final PieceType type;
    private Position position;
    private boolean firstMoveDone;
    private static boolean whiteMove = true;

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
        int xNewPosition = newPosition.getX();
        int yNewPosition = newPosition.getY();
        Piece pieceOnNewPosition = boardSquares[xNewPosition][yNewPosition].getPiece();

        if(playerColor == PlayerColor.WHITE && !isWhiteMove())
            return;
        if(playerColor == PlayerColor.BLACK && isWhiteMove())
            return;

        int xPositionBeforeMove = getPosition().getX();
        int yPositionBeforeMove = getPosition().getY();
        Position oldWhiteKingPosition = chessBoard.getWhiteKingPosition();
        Position oldBlackKingPosition = chessBoard.getBlackKingPosition();

        boardSquares[xPositionBeforeMove][yPositionBeforeMove].setPiece(null);
        setPosition(newPosition);
        boardSquares[xNewPosition][yNewPosition].setPiece(this);
        if (type == PieceType.KING) {
            if (playerColor==PlayerColor.WHITE) {
                chessBoard.setWhiteKingPosition(newPosition);
            } else{
                chessBoard.setBlackKingPosition(newPosition);
            }
        }
        chessBoard.setBoardSquares(boardSquares);
        setWhiteMove(!whiteMove);


        if (allyKingAreNotSafe(chessBoard)) {
            boardSquares[xNewPosition][yNewPosition].setPiece(pieceOnNewPosition);
            setPosition(new Position(xPositionBeforeMove, yPositionBeforeMove));
            boardSquares[xPositionBeforeMove][yPositionBeforeMove].setPiece(this);
            if (type == PieceType.KING) {
                if (playerColor==PlayerColor.WHITE) {
                    chessBoard.setWhiteKingPosition(oldWhiteKingPosition);
                } else{
                    chessBoard.setBlackKingPosition(oldBlackKingPosition);
                }
            }
            chessBoard.setBoardSquares(boardSquares);
            setWhiteMove(!whiteMove);
        }
        else
            firstMoveDone = true;

        if (this instanceof Pawn){
            boolean lastStateOfFirstMove = ((Pawn) this).isLastStateOfFirstMove();
            if(firstMoveDone == true && lastStateOfFirstMove == false) {
                ((Pawn) this).setLastStateOfFirstMove(true);
                ((Pawn) this).setEnPassantPossible(true);
            }
            else
                ((Pawn) this).setEnPassantPossible(false);

            if (pawnOnLastSquare(chessBoard, yNewPosition)) {
                boardSquares[xNewPosition][yNewPosition].setPiece(new Queen(playerColor, position));
            }


        }
    }

    private boolean pawnOnLastSquare(ChessBoard chessBoard, int yNewPosition) {
        return (playerColor == PlayerColor.WHITE && yNewPosition == chessBoard.ROWS_AMOUNT-1)
                || (playerColor == PlayerColor.BLACK && yNewPosition == 0);
    }

    public abstract boolean isTakePossible(Position newPosition, ChessBoard chessBoard);

    public boolean allyKingAreNotSafe(ChessBoard chessBoard) {
        return (playerColor == PlayerColor.WHITE && !chessBoard.isWhiteKingSafe()) || (playerColor == PlayerColor.BLACK && !chessBoard.isBlackKingSafe());
    }

    public boolean isNewPositionSame(Position newPosition){
        return position.equals(newPosition);
    }

    public boolean isFirstMoveDone() {
        return firstMoveDone;
    }

    public static boolean isWhiteMove() {
        return whiteMove;
    }

    public static void setWhiteMove(boolean whiteMove) {
        Piece.whiteMove = whiteMove;
    }
}
