package GUI;


import Board.Square;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BoardDisplay {
    private final byte SQUARE_WIDTH = 64;
    private final byte SQUARE_HEIGHT = 64;
    private final byte xMove = 20;
    private final byte yMove = 35;
    final Color BLACK = new Color(255, 204, 153);
    final Color WHITE = new Color(102, 51, 0);

    private JFrame m_frame;
    private JPanel m_panel;
    private HashMap<String, Image> m_images = new HashMap<>();



    public BoardDisplay() {

    }

    public void initialize(Square[][] boardSquares){
        m_frame = new JFrame(){
            @Override
            public void paint(Graphics g) {
                boolean isWhite;

                g.setColor(Color.BLACK);
                g.drawRect(xMove-1, yMove-1, 8*SQUARE_WIDTH+1, 8*SQUARE_HEIGHT+1);

                for(int x=0; x<8; x++){
                    for(int y=0; y<8; y++){
                        isWhite = (x+y)%2 == 0;

                        if(isWhite){
                            g.setColor(WHITE); // color white
                        }
                        else{
                            g.setColor(BLACK); // black
                        }

                        g.fillRect(x*SQUARE_WIDTH+xMove, y*SQUARE_HEIGHT+yMove, SQUARE_WIDTH, SQUARE_HEIGHT);
                    }
                }

                //for (int i=0; i<8; i++){
                    //for (int j = 0; j < 8; j++) {
                        //Square square = boardSquares[i][j];
                        g.drawImage(m_images.get("wp"), SQUARE_WIDTH+xMove, SQUARE_HEIGHT+yMove, this);
                    //}
               // }


            }
        };


        readImages();
        m_frame.setBounds(0, 0, 555, 565);
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
                images[i*2+j]=img.getSubimage(0, 0, 60, 60).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
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
