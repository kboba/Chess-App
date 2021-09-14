package GUI;


import javax.swing.*;
import java.awt.*;

public class BoardDisplay {
    public static final byte WIDTH = 64;
    private static final byte HEIGHT = 64;

    private JFrame frame;
    private JPanel panel;

    public BoardDisplay() {

    }

    public void initialize(){
        frame = new JFrame(){
            @Override
            public void paint(Graphics g) {
                boolean isWhite;

                final var BLACK = new Color(255, 204, 153);
                final var WHITE = new Color(102, 51, 0);

                g.setColor(Color.BLACK);
                g.drawRect(19, 34, 8*BoardDisplay.WIDTH+1, 8*BoardDisplay.HEIGHT+1);

                for(int x = 0; x < 8; x++){
                    for(int y = 0; y < 8; y++){
                        isWhite = (x+y)%2 == 0;

                        if(isWhite){
                            g.setColor(WHITE); // color white
                        }
                        else{
                            g.setColor(BLACK); // black
                        }

                        g.fillRect(x*BoardDisplay.WIDTH+20, y*BoardDisplay.HEIGHT+35, BoardDisplay.WIDTH, BoardDisplay.HEIGHT);
                    }
                }
            }
        };

        frame.setBounds(0, 0, 555, 565);
        panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
