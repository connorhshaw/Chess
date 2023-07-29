package chessApp.view;

import chessApp.model.ChessBoard;
import chessApp.model.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    public boolean showBoard;
    int numSquaresX, squaresY, boardWidth, boardHeight, xBuffer, yBuffer, selectionWidth;
    float squareWidth, squareHeight;
    ChessBoard chessBoard;
    String resourceFolder, fileType;

    public GamePanel(ChessBoard chessBoard, int numSquaresX, int numSquaresY, int boardWidth,
                     int boardHeight, int xBuffer, int yBuffer){
        this.chessBoard = chessBoard;

        this.numSquaresX = numSquaresX;
        this.squaresY = numSquaresY;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.xBuffer = xBuffer;
        this.yBuffer = yBuffer;

        this.selectionWidth = 4;

        resourceFolder = "src/main/java/chessApp/resources/";
        fileType = ".png";

        squareWidth = boardWidth/numSquaresX;
        squareHeight = boardHeight/numSquaresY;

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.fillRect(0, 0, getWidth(), getHeight());
        boolean white = true;
        g.setColor(Color.white);

        for (int j = 0; j < squaresY; j++){
            for (int i = 0; i < numSquaresX; i++){
                if ((i % 2) == (j % 2)) {
                    g.setColor(Color.white);
                } else{
                    g.setColor(Color.gray);
                }
                g.fillRect((int) (i*squareWidth) + xBuffer, (int) (j*squareHeight) + yBuffer,
                        (int) squareWidth, (int) squareHeight);
            }
        }

        if (chessBoard.getSelectedPiece() != null) {
            drawMoves(g, chessBoard.getSelectedPiece().validMoves());
            drawSelection(g, chessBoard.getSelectedPiece().getSquare());
        }

        try {
            updatePieces(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    private void updatePieces(Graphics g) throws IOException {
        for (Piece piece:
                chessBoard.getPieces())
        {
            BufferedImage bufferedImage = ImageIO.read(new File(resourceFolder+piece.getPiece()+
                    piece.getColour().toString()+fileType));
            Image img = bufferedImage.getScaledInstance((int) squareWidth, (int) squareHeight, Image.SCALE_SMOOTH);
            Point square = piece.getSquare();
            int row = square.x;
            int column = square.y;
            g.drawImage(img, (int) (row * squareWidth), (int) (column * squareHeight), this);

        }
    }

    private void drawMoves(Graphics g, ArrayList<Point> moves){

        if (moves != null) {

            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(selectionWidth));

            for (Point move :
                    moves) {
                g2.drawRect((int) (move.getX() * squareWidth)+selectionWidth/2, (int) (move.getY() * squareHeight)+selectionWidth/2,
                        (int) squareWidth-selectionWidth, (int) squareHeight-selectionWidth);
            }
        }

    }

    private void drawSelection(Graphics g, Point selectedSquare){

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(selectionWidth));
        g2.drawRect((int) (selectedSquare.getX() * squareWidth)+selectionWidth/2, (int) (selectedSquare.getY() * squareHeight)+selectionWidth/2,
                (int) squareWidth-selectionWidth, (int) squareHeight-selectionWidth);

    }
}

