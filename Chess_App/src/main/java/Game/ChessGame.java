package Game;

import Board.Board;
import GUI.BoardDisplay;

public class ChessGame {
//    private static BoardDisplay gui;
//    private static Board board;

    public static void main(String[] args) {
        var boardDisplay = new BoardDisplay();
        var board = new Board(boardDisplay);

    }
}
