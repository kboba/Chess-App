package Board;

import GUI.BoardDisplay;
import Game.Position;
import Pieces.Piece;

public class Board {
    private Square[][] boardSquares;
    private BoardDisplay boardDisplay;

    public Board(BoardDisplay boardDisplay) {
        this.boardDisplay = boardDisplay;
        boardDisplay.initialize(boardSquares);
        initialize();
    }

    private void initialize() {
//        boardSquares[0][0];
//        boardSquares[0][1];
//        boardSquares[0][2];
//        boardSquares[0][3];
//        boardSquares[0][4];
//        boardSquares[0][5];
//        boardSquares[0][6];
//        boardSquares[0][7];

    }
}
