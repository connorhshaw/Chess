package chessApp.view;

import chessApp.model.ChessGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ScreenController {

    final private int PREFWIDTH = 400;
    final private int PREFHEIGHT = 428;

    private JFrame frame;
    private GamePanel gamePanel;
    private ChessGame chessGame;


    private int numSquaresX, numSquaresY, boardWidth, boardHeight, xBuffer, yBuffer;

    public ScreenController(ChessGame chessGame){

        numSquaresX = 8;
        numSquaresY = 8;
        boardWidth = 400;
        boardHeight = 400;

        frame = new JFrame();
        gamePanel = new GamePanel(chessGame.getChessBoard(), numSquaresX, numSquaresY, boardWidth, boardHeight, xBuffer, yBuffer);

        frame.setSize(PREFWIDTH, PREFHEIGHT);
        frame.setBackground(Color.GRAY);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setSize(PREFWIDTH, PREFHEIGHT);
        gamePanel.setOpaque(true);

        frame.add(gamePanel);

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chessGame.processInput(getSquare(e.getX(), e.getY()));
            }
        });
    }

    public void update(){
        gamePanel.repaint();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public Point getSquare(int x, int y){
        return new Point((int) (x/gamePanel.squareWidth), (int) (y/gamePanel.squareHeight));
    }
}
