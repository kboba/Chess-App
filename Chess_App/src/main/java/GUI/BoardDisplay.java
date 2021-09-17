package GUI;


import Board.Board;

import javax.swing.*;

public class BoardDisplay implements Displayable {
    private final byte ROWS_AMOUNT = 8;
    private final byte COLUMNS_AMOUNT = 8;
    private final byte SQUARE_WIDTH = 64;
    private final byte SQUARE_HEIGHT = 64;


    private JFrame jFrame;
    private BoardGraphics boardGraphics; //private JPanel
    private Board board;

    public BoardDisplay(Board board) {
        this.board = board;
    }

    public void initialize(){
        setFrame();
    }

    private void setFrame() {
        jFrame = new JFrame("Chess Game");
        jFrame.setBounds(0, 0, COLUMNS_AMOUNT*SQUARE_WIDTH +42, ROWS_AMOUNT*SQUARE_HEIGHT +54);
        boardGraphics = new BoardGraphics(board);
        jFrame.add(boardGraphics);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



    @Override
    public void display() {

    }


}
