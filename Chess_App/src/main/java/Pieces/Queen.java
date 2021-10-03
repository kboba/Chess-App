package Pieces;

import Board.Board;
import Board.Position;
import Board.Square;

public class Queen extends Piece {

    public Queen(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.QUEEN, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {
        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        var boardSquares = board.getBoardSquares();

        if(currentPositionX == newPositionX) {
            if(currentPositionY < newPositionY)
                for(int yPosition = currentPositionY+1; yPosition < newPositionY; yPosition++){
                    if(boardSquares[currentPositionX][yPosition].getPiece()!=null){
                        return false;
                    }
                }
            else if(currentPositionY > newPositionY)
                for(int yPosition = currentPositionY-1; yPosition > newPositionY; yPosition--){
                    if(boardSquares[currentPositionX][yPosition].getPiece()!=null){
                        return false;
                    }
                }

            return true;
        }

        return false;
    }
}
