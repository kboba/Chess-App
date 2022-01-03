package GUI;


import Board.ChessBoard;

import javax.swing.*;

public class UserInterface {

    private JFrame jFrame;
    private BoardUserInterface boardUserInterface; //private JPanel

    public UserInterface(ChessBoard chessBoard) {
        boardUserInterface = new BoardUserInterface(chessBoard);
    }

    public void initialize(){
        setFrame();
    }

    private void setFrame() {
        jFrame = new JFrame("Chess Game");
        jFrame.setBounds(0, 0, boardUserInterface.getBoardWidth(), boardUserInterface.getBoardHeight());
        jFrame.add(boardUserInterface);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
