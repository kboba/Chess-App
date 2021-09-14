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
                for(int x = 0; x < 8; x++){
                    for(int y = 0; y < 8; y++){
                        isWhite = (x+y)%2 == 0;

                        if(isWhite){
                            g.setColor(Color.WHITE); // color white
                        }
                        else{
                            g.setColor(Color.DARK_GRAY); // black
                        }

                        g.fillRect(x*BoardDisplay.WIDTH, y*BoardDisplay.HEIGHT, BoardDisplay.WIDTH, BoardDisplay.HEIGHT);
                    }
                }
            }
        };

        frame.setBounds(100, 100, 512, 512);
        panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
