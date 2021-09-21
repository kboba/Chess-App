package Game;

import Board.Board;
import GUI.UserInterface;

public class ChessGame {
//    private static BoardDisplay gui;
//    private static Board board;

    public static void main(String[] args) {
        var board = new Board();
        var userInterface = new UserInterface(board);
        userInterface.initialize();
    }
}
