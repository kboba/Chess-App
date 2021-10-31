package GUI;

import Board.ChessBoard;
import Board.Position;
import Board.Square;
import Pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.HashMap;

public class BoardUserInterface extends JPanel implements MouseListener, MouseMotionListener {
    private final byte X_MOVE = 13;
    private final byte Y_MOVE = 3;
    private final byte BORDER_WIDTH = 1;
    final Color BLACK_SQUARES_COLOR = new Color(102, 51, 0);
    final Color WHITE_SQUARES_COLOR = new Color(255,204,153);
    final Color BOARD_COLOR = new Color(68, 28, 0);
    final Color SELECTED_SQUARE_COLOR = new Color(175, 125, 75);
    final Color KING_ATTACKED_SQUARE_COLOR = new Color(255, 0, 0);
    private final byte ROWS_AMOUNT = 8;
    private final byte COLUMNS_AMOUNT = 8;
    private final byte SQUARE_WIDTH = 64;
    private final byte SQUARE_HEIGHT = 64;
    private HashMap<String, Image> stringToImage;
    private ChessBoard chessBoard;
    private Square[][] boardSquares;
    private int xMousePosition = 0;
    private int yMousePosition = 0;
    private int xSelectedSquare;
    private int ySelectedSquare;
    private Piece selectedPiece;

    public BoardUserInterface(ChessBoard chessBoard) {
        boardSquares = chessBoard.getBoardSquares();
        this.chessBoard = chessBoard;
        stringToImage  = new HashMap<>();
        readImages();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        drawBorder(g);
        drawBoard(g);
        drawAttackedKingSquare(g);
        drawSelectedSquare(g);
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
        xSelectedSquare = xMousePosition/64;
        ySelectedSquare = yMousePosition/64;

        try{
            Piece newSelectedPiece = boardSquares[xSelectedSquare][ySelectedSquare].getPiece();
            moveOrSelectPiece(newSelectedPiece);
        } catch (ArrayIndexOutOfBoundsException error) {
            return;
        }
        repaint();
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

    private void drawAttackedKingSquare(Graphics g) {
        g.setColor(KING_ATTACKED_SQUARE_COLOR);

        if(!chessBoard.isWhiteKingSafe()){
            var attackedKingPosition = chessBoard.getWhiteKingPosition();

            int xAttackedKing = attackedKingPosition.getPosition().getX();
            int yAttackedKing = attackedKingPosition.getPosition().getY();
            g.fillRect(xAttackedKing *SQUARE_WIDTH+ X_MOVE + BORDER_WIDTH, yAttackedKing *SQUARE_HEIGHT+ Y_MOVE + BORDER_WIDTH, SQUARE_WIDTH, SQUARE_HEIGHT);
        }
        else if (!chessBoard.isBlackKingSafe()){
            var attackedKingPosition = chessBoard.getBlackKingPosition();

            int xAttackedKing = attackedKingPosition.getPosition().getX();
            int yAttackedKing = attackedKingPosition.getPosition().getY();
            g.fillRect(xAttackedKing *SQUARE_WIDTH+ X_MOVE + BORDER_WIDTH, yAttackedKing *SQUARE_HEIGHT+ Y_MOVE + BORDER_WIDTH, SQUARE_WIDTH, SQUARE_HEIGHT);
        }
    }

    private void drawSelectedSquare(Graphics g) {
        if(selectedPiece!=null){
            g.setColor(SELECTED_SQUARE_COLOR);
            int selectedPieceX = selectedPiece.getPosition().getX();
            int selectedPieceY = selectedPiece.getPosition().getY();
            g.fillRect(selectedPieceX *SQUARE_WIDTH+ X_MOVE + BORDER_WIDTH, selectedPieceY *SQUARE_HEIGHT+ Y_MOVE + BORDER_WIDTH, SQUARE_WIDTH, SQUARE_HEIGHT);
        }
    }

    private void drawPieces(Graphics g, ImageObserver observer) {
        String pieceInitials;
        for (int y=0; y<ROWS_AMOUNT; y++){
            for (int x = 0; x < COLUMNS_AMOUNT; x++) {
                if(boardSquares[y][x].getPiece() == null)
                    continue;
                var pieceOnSquare = boardSquares[y][x].getPiece();
                var pieceColor = pieceOnSquare.getPlayerColor();
                var type = pieceOnSquare.getType();
                pieceInitials = getPieceInitials(pieceColor, type);
                g.drawImage(stringToImage.get(pieceInitials), y*SQUARE_WIDTH+ X_MOVE+BORDER_WIDTH, x*SQUARE_HEIGHT+ Y_MOVE+BORDER_WIDTH, observer);
            }
        }
    }

    /*
     * Method which:
     * 1. Select or unselect selected piece
     * 2. Check if move with selected piece is possible and then moves
     * 3. Check if take with selected piece is possible and then takes
     */
    private void moveOrSelectPiece(Piece newSelectedPiece) {
        // Current selected piece which is a piece
        if(newSelectedPiece != null) {
            // Selecting piece when nothing is selected
            if (selectedPiece == null)
                    selectedPiece = newSelectedPiece;
            else {
                // Unselecting piece when click twice on same piece
                if(selectedPiece == newSelectedPiece)
                    selectedPiece = null;
                // Check if take is possible and then move
                else if(selectedPiece.isMoveValid(new Position(xSelectedSquare, ySelectedSquare), chessBoard) && selectedPiece.getPlayerColor() != newSelectedPiece.getPlayerColor()){
                    selectedPiece.move(new Position(xSelectedSquare, ySelectedSquare), chessBoard);
                    selectedPiece = null;
                }
                // Conditions for castle
                else if(selectedPiece instanceof King && newSelectedPiece instanceof Rock && selectedPiece.getPlayerColor() == newSelectedPiece.getPlayerColor()) {
                    // Castle if it is possible
                    if (((King) selectedPiece).isCastlePossible(new Position(xSelectedSquare, ySelectedSquare), chessBoard)) {
                        ((King) selectedPiece).castle(new Position(xSelectedSquare, ySelectedSquare), chessBoard);
                        selectedPiece = null;
                    }
                }
            }
        }
        // Current selected piece which is not a piece
        // Check if is move possible and then move
        else if(selectedPiece!=null) {
            if(selectedPiece.isMoveValid(new Position(xSelectedSquare, ySelectedSquare), chessBoard)){
                selectedPiece.move(new Position(xSelectedSquare, ySelectedSquare), chessBoard);
                selectedPiece = null;
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
        String pathName = "C:\\ChessGame\\Pieces.png";
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
        Image[] images = new Image[12];

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

    private void saveGame(){
        try{
            String path = "C:\\ChessGame\\SavedGame.csv)";
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path));
            for (int xPosition = 0; xPosition < COLUMNS_AMOUNT; xPosition++) {
                for (int yPosition = 0; yPosition < ROWS_AMOUNT; yPosition++) {
                    Piece pieceOnPosition = boardSquares[xPosition][yPosition].getPiece();
                    PlayerColor pieceColor = pieceOnPosition.getPlayerColor();
                    PieceType pieceType = pieceOnPosition.getType();
                    if (yPosition!=0)
                        fileWriter.write(",")
                    String pieceInitials = getPieceInitials(pieceColor, pieceType);
                    fileWriter.write(pieceInitials);
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (Exception e){
            return;
        }
    }

    private void loadGame(){
        try{
            String path = "C:\\ChessGame\\SavedGame.csv)";
            BufferedReader fileReader = new BufferedReader(new FileReader(path));
            for (int xPosition = 0; xPosition < COLUMNS_AMOUNT; xPosition++) {
                for (int yPosition = 0; yPosition < ROWS_AMOUNT; yPosition++) {
                }
            }
            fileReader.close();
        } catch (Exception e){
            return;
        }
    }
    public int getBoardWidth(){
        return COLUMNS_AMOUNT*SQUARE_WIDTH + 42;
    }

    public int getBoardHeight(){
        return ROWS_AMOUNT*SQUARE_HEIGHT + 54;
    }
}
