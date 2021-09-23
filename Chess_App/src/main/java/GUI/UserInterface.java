package GUI;


import Board.Board;

import javax.swing.*;

public class UserInterface {

    private JFrame jFrame;
    private BoardUserInterface boardUserInterface; //private JPanel
    private Board board;

    public UserInterface(Board board) {
        this.board = board;
        boardUserInterface = new BoardUserInterface(board);
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
