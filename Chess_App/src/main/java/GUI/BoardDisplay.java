package GUI;


import Board.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BoardDisplay {
    private final byte ROWS_AMOUNT = 8;
    private final byte COLUMNS_AMOUNT = 8;
    private final byte SQUARE_WIDTH = 64;
    private final byte SQUARE_HEIGHT = 64;
    private final byte X_MOVE = 20;
    private final byte Y_MOVE = 35;
    private final byte BORDER_WIDTH = 1;
    final Color BLACK = new Color(255, 204, 153);
    final Color WHITE = new Color(102, 51, 0);

    private JFrame m_frame;
    private JPanel m_panel;
    private HashMap<String, Image> m_images = new HashMap<>();

    private Board board;
    
    public BoardDisplay(Board board) {
        this.board = board;
    }

    public void initialize(){
        m_frame = new JFrame(){
            @Override
            public void paint(Graphics g) {
                boolean isWhite;

                g.setColor(Color.BLACK);
                g.drawRect(X_MOVE, Y_MOVE, COLUMNS_AMOUNT*SQUARE_WIDTH + BORDER_WIDTH, ROWS_AMOUNT*SQUARE_HEIGHT + BORDER_WIDTH);

                for(int x=0; x<COLUMNS_AMOUNT; x++){
                    for(int y=0; y<ROWS_AMOUNT; y++){
                        isWhite = (x+y)%2 == 0;

                        if(isWhite){
                            g.setColor(WHITE); // color white
                        }
                        else{
                            g.setColor(BLACK); // black
                        }

                        g.fillRect(x*SQUARE_WIDTH+ X_MOVE + BORDER_WIDTH, y*SQUARE_HEIGHT+ Y_MOVE + BORDER_WIDTH, SQUARE_WIDTH, SQUARE_HEIGHT);
                    }
                }

                //for (int i=0; i<ROWS_AMOUNT; i++){
                    //for (int j = 0; j < COLUMNS_AMOUNT; j++) {
                        //Square square = boardSquares[i][j];
                        g.drawImage(m_images.get("wp"), SQUARE_WIDTH+ X_MOVE, SQUARE_HEIGHT+ Y_MOVE, this);
                    //}
               // }


            }
        };


        readImages();
        m_frame.setBounds(0, 0, COLUMNS_AMOUNT*SQUARE_WIDTH +42, ROWS_AMOUNT*SQUARE_HEIGHT +54);
        m_panel = new JPanel();
        m_frame.add(m_panel);
        m_frame.setVisible(true);
        m_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        m_images.put("wp", images[0]);
        m_images.put("bp", images[1]);
        m_images.put("wB", images[2]);
        m_images.put("bB", images[3]);
        m_images.put("wN", images[4]);
        m_images.put("bN", images[5]);
        m_images.put("wR", images[6]);
        m_images.put("bR", images[7]);
        m_images.put("wQ", images[8]);
        m_images.put("bQ", images[9]);
        m_images.put("wK", images[10]);
        m_images.put("bK", images[11]);
    }
}
