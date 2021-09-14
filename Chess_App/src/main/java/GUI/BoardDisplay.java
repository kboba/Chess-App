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
        frame = new JFrame();
        frame.setBounds(0, 0, 512, 512);
        panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
