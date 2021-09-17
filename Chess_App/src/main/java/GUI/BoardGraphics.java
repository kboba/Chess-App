package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class BoardGraphics extends JPanel {
    private final byte X_MOVE = 20;
    private final byte Y_MOVE = 35;
    private final byte BORDER_WIDTH = 1;
    final Color BLACK_SQUARES_COLOR = new Color(102, 51, 0);
    final Color WHITE_SQUARES_COLOR = new Color(255,204,153);
    final Color BOARD_COLOR = new Color(68, 28, 0);
    private final byte ROWS_AMOUNT = 8;
    private final byte COLUMNS_AMOUNT = 8;
    private final byte SQUARE_WIDTH = 64;
    private final byte SQUARE_HEIGHT = 64;

    @Override
    public void paint(Graphics g) {
        drawBorder(g);
        drawBoard(g);
        drawPieces(g, this);
    }

    private void drawBorder(Graphics g) {
        g.setColor(BOARD_COLOR);
        g.drawRect(X_MOVE, Y_MOVE, COLUMNS_AMOUNT*SQUARE_WIDTH + BORDER_WIDTH, ROWS_AMOUNT*SQUARE_HEIGHT + BORDER_WIDTH);
    }

    private void drawBoard(Graphics g) {
        boolean isWhite;
        for(int x = 0; x<COLUMNS_AMOUNT; x++){
            for(int y=0; y<ROWS_AMOUNT; y++){
                isWhite = (x+y)%2 == 0;

                if(isWhite){
                    g.setColor(WHITE_SQUARES_COLOR); // color white
                }
                else{
                    g.setColor(BLACK_SQUARES_COLOR); // black
                }

                g.fillRect(x*SQUARE_WIDTH+ X_MOVE + BORDER_WIDTH, y*SQUARE_HEIGHT+ Y_MOVE + BORDER_WIDTH, SQUARE_WIDTH, SQUARE_HEIGHT);
            }
        }
    }

    private void drawPieces(Graphics g, ImageObserver observer) {
        //for (int i=0; i<ROWS_AMOUNT; i++){
        //for (int j = 0; j < COLUMNS_AMOUNT; j++) {
        //Square square = boardSquares[i][j];
        //}
        // }
    }
}
