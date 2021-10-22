package Pieces;

import Board.ChessBoard;
import Board.Position;

import static Pieces.PlayerColor.WHITE;
import static java.lang.Math.abs;

public class King extends Piece {

    public King(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.KING, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, ChessBoard chessBoard) {
        if(isNewPositionSame(newPosition))
            return false;

        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();

        if(canMoveToNeighborSquare(newPositionX, newPositionY)){
            if(getPlayerColor()==WHITE)
                chessBoard.setWhiteKingPosition(newPositionX, newPositionY);
            else
                chessBoard.setBlackKingPosition(newPositionX, newPositionY);

            return true;
        }

        return false;
    }

    @Override
    public boolean isTakePossible(Position newPosition, ChessBoard chessBoard){
        return isMoveValid(newPosition, chessBoard);
    }

    private boolean canMoveToNeighborSquare(int newPositionX, int newPositionY) {
        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();

        return abs(currentPositionX - newPositionX) == 1 && abs(currentPositionY - newPositionY) == 1
                || abs(currentPositionX - newPositionX) == 1 && abs(currentPositionY - newPositionY) == 0
                || abs(currentPositionX - newPositionX) == 0 && abs(currentPositionY - newPositionY) == 1;
    }
}
