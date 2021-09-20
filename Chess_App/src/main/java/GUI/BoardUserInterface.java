package GUI;

import Board.Board;
import Board.Square;
import Pieces.Piece;
import Pieces.PieceType;
import Pieces.PlayerColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BoardUserInterface extends JPanel implements MouseListener, MouseMotionListener {
    private final byte X_MOVE = 13;
    private final byte Y_MOVE = 3;
    private final byte BORDER_WIDTH = 1;
    final Color BLACK_SQUARES_COLOR = new Color(102, 51, 0);
    final Color WHITE_SQUARES_COLOR = new Color(255,204,153);
    final Color BOARD_COLOR = new Color(68, 28, 0);
    private final byte ROWS_AMOUNT = 8;
    private final byte COLUMNS_AMOUNT = 8;
    private final byte SQUARE_WIDTH = 64;
    private final byte SQUARE_HEIGHT = 64;
    private HashMap<String, Image> stringToImage;
    Board board;
    Square[][] boardSquares;
    private int xMousePosition = 0;
    private int yMousePosition = 0;
    private int xSquare;
    private int ySquare;
    Piece selectedPiece;

    public BoardUserInterface(Board board) {
        boardSquares = board.getBoardSquares();
        this.board = board;
        stringToImage  = new HashMap<>();
        readImages();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        drawBorder(g);
        drawBoard(g);
        drawPieces(g, this);
    }

    /*
     * MouseListener implementation
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        xMousePosition = e.getX()-X_MOVE;
        yMousePosition = e.getY()-Y_MOVE;
        xSquare = xMousePosition/64;
        ySquare = yMousePosition/64;
        
        selectedPiece = boardSquares[xSquare][ySquare].getPiece();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /*
     * MouseMotionListener implementation
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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
        String pieceInitials;
        for (int i=0; i<ROWS_AMOUNT; i++){
            for (int j = 0; j < COLUMNS_AMOUNT; j++) {
                if(boardSquares[i][j].getPiece() == null)
                    continue;
                var pieceOnSquare = boardSquares[i][j].getPiece();
                var pieceColor = pieceOnSquare.getPlayerColor();
                var type = pieceOnSquare.getType();
                pieceInitials = getPieceInitials(pieceColor, type);
                g.drawImage(stringToImage.get(pieceInitials), i*SQUARE_WIDTH+ X_MOVE+BORDER_WIDTH, j*SQUARE_HEIGHT+ Y_MOVE+BORDER_WIDTH, observer);
            }
        }
    }

    private String getPieceInitials(PlayerColor pieceColor, PieceType type) {
        String pieceInitials = pieceColor ==PlayerColor.WHITE ? "w" : "b";

        if(type ==PieceType.PAWN) {
            pieceInitials += "p";
        }
        else if(type ==PieceType.BISHOP) {
            pieceInitials += "B";
        }
        else if(type ==PieceType.KNIGHT) {
            pieceInitials += "N";
        }
        else if(type ==PieceType.ROCK) {
            pieceInitials += "R";
        }
        else if(type ==PieceType.QUEEN) {
            pieceInitials += "Q";
        }
        else if(type ==PieceType.KING) {
            pieceInitials += "K";
        }
        return pieceInitials;
    }

    private void readImages() {
        BufferedImage img= null;
        String pathName = "C:\\Pieces\\Pieces.png";
        try {
            img = ImageIO.read(new File(pathName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image[] images = getCutImages(img);
        mapImages(images);
    }

    private Image[] getCutImages(BufferedImage img) {
        final int IMAGE_WIDTH = 200;
        final int IMAGE_HEIGHT = 200;
        Image images[] = new Image[12];

        int i=0;
        for(int y = 0; y<2* IMAGE_HEIGHT; y+= IMAGE_HEIGHT){
            for(int x = 0; x<6* IMAGE_WIDTH; x+= IMAGE_WIDTH){
                images[i++]= img.getSubimage(x, y, IMAGE_WIDTH, IMAGE_HEIGHT).getScaledInstance(SQUARE_WIDTH, SQUARE_HEIGHT, BufferedImage.SCALE_SMOOTH);
            }
        }

        return images;
    }

    private void mapImages(Image[] images){
        stringToImage.put("wK", images[0]);
        stringToImage.put("wQ", images[1]);
        stringToImage.put("wB", images[2]);
        stringToImage.put("wN", images[3]);
        stringToImage.put("wR", images[4]);
        stringToImage.put("wp", images[5]);
        stringToImage.put("bK", images[6]);
        stringToImage.put("bQ", images[7]);
        stringToImage.put("bB", images[8]);
        stringToImage.put("bN", images[9]);
        stringToImage.put("bR", images[10]);
        stringToImage.put("bp", images[11]);
    }

    public int getBoardWidth(){
        return COLUMNS_AMOUNT*SQUARE_WIDTH + 42;
    }

    public int getBoardHeight(){
        return ROWS_AMOUNT*SQUARE_HEIGHT + 54;
    }
}
