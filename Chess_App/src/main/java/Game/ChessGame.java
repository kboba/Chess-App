package Game;

import Board.ChessBoard;
import GUI.UserInterface;

public class ChessGame {
//    private static BoardDisplay gui;
//    private static Board board;

    public static void main(String[] args) {
        var board = new ChessBoard();
        var userInterface = new UserInterface(board);
        userInterface.initialize();
    }
}
