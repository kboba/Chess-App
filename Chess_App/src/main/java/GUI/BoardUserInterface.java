package GUI;

import Board.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BoardUserInterface extends JPanel {
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
    private HashMap<String, Image> stringToImage = new HashMap<>();
    Board board;

    public BoardUserInterface(Board board) {
        readImages();
        this.board = board;
    }

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
                g.drawImage(stringToImage.get("wp"), SQUARE_WIDTH+ X_MOVE, SQUARE_HEIGHT+ Y_MOVE, observer);
            //}
            // }
    }

    private void readImages() {
        Image images[] = new Image[12];
        BufferedImage img= null;
        String pathName = "";
        for(int i=0; i<6; i++){
            // white:1  ; black:2
            for(int j=0; j<2; j++){
                pathName = "C:\\Users\\bobak\\CLOUD\\repos\\Chess_App\\Pieces\\"+(j+1)+(i+1)+".png"; // "(path)\(1;2)(1;6)"
                try {
                    img = ImageIO.read(new File(pathName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                images[i*2+j]=img.getSubimage(0, 0, 60, 60).getScaledInstance(SQUARE_WIDTH, SQUARE_HEIGHT, BufferedImage.SCALE_SMOOTH);
            }
        }

        mapImages(images);
    }

    private void mapImages(Image[] images){
        stringToImage.put("wp", images[0]);
        stringToImage.put("bp", images[1]);
        stringToImage.put("wB", images[2]);
        stringToImage.put("bB", images[3]);
        stringToImage.put("wN", images[4]);
        stringToImage.put("bN", images[5]);
        stringToImage.put("wR", images[6]);
        stringToImage.put("bR", images[7]);
        stringToImage.put("wQ", images[8]);
        stringToImage.put("bQ", images[9]);
        stringToImage.put("wK", images[10]);
        stringToImage.put("bK", images[11]);
    }

    public int getBoardWidth(){
        return COLUMNS_AMOUNT*SQUARE_WIDTH + 42;
    }

    public int getBoardHeight(){
        return ROWS_AMOUNT*SQUARE_HEIGHT + 54;
    }
}
