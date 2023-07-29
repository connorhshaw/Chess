package chessApp.model;

import chessApp.controller.HumanPlayer;
import chessApp.controller.Player;
import chessApp.model.pieces.Piece;
import chessApp.view.ScreenController;

import java.awt.*;
import java.util.HashMap;

public class ChessGame {

    ScreenController screenController;
    ChessBoard chessBoard;
    HashMap<Colour, Player> players = new HashMap<>();
    Colour turn;

    public ChessGame(){
        chessBoard = new ChessBoard();
        screenController = new ScreenController(this);

        players.put(Colour.WHITE, new HumanPlayer(chessBoard, Colour.WHITE, this));
        players.put(Colour.BLACK, new HumanPlayer(chessBoard, Colour.BLACK, this));

        start();
    }

    private void start(){

        turn = Colour.WHITE;

    }

    public void addMove(Piece piece, Point square){


        Piece capturedPiece = chessBoard.getPieceAt(square.y, square.x);
        if (capturedPiece != null) {
            chessBoard.getPieces().remove(capturedPiece);
        }

        piece.move(square);

        if (turn.equals(Colour.WHITE)){
            turn = Colour.BLACK;
            System.out.println("Black's turn");
        } else{
            turn = Colour.WHITE;
            System.out.println("White's turn");
        }
    }

    public boolean isChecked(){
        return false;
    }

    public void processInput(Point square){

        players.get(turn).processInput(square);

        screenController.update();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

}
