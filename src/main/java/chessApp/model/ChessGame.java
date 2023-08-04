package chessApp.model;

import chessApp.controller.HumanPlayer;
import chessApp.controller.Player;
import chessApp.model.pieces.Piece;
import chessApp.view.ScreenController;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ChessGame {

    ScreenController screenController;
    ChessBoard chessBoard;
    HashMap<Colour, Player> players = new HashMap<>();
    Colour turn;
    boolean whiteChecked, blackChecked;

    public ChessGame(){
        chessBoard = new ChessBoard();
        screenController = new ScreenController(this);

        players.put(Colour.WHITE, new HumanPlayer(chessBoard, Colour.WHITE, this));
        players.put(Colour.BLACK, new HumanPlayer(chessBoard, Colour.BLACK, this));

    }

    public void start(){

        turn = Colour.WHITE;

    }

    public void addMove(Piece piece, Point square){

        Piece capturedPiece = chessBoard.getPieceAt(square.y, square.x);

        if (capturedPiece != null) {
            chessBoard.getPieces().remove(capturedPiece);
        }

        piece.move(square);

        checkChecks();

        //if checked
        //if no valid moves

        if (turn.equals(Colour.WHITE)){
            if (whiteChecked){
                piece.undoMove();
                System.out.println("invalid move");
                if (capturedPiece != null) {
                    chessBoard.addPiece(capturedPiece);
                }
            } else{
                turn = Colour.BLACK;
                System.out.println("Black's turn");
            }

        } else{
            if (blackChecked){
                piece.undoMove();
                System.out.println("invalid move");
                if (capturedPiece != null) {
                    chessBoard.addPiece(capturedPiece);
                }
            } else{
                turn = Colour.WHITE;
                System.out.println("White's turn");
            }
        }
    }

    private void checkChecks(){

        Point whiteKingPosition = chessBoard.getKing(Colour.WHITE).getSquare();

        for (Point availableMove: chessBoard.getAllAvailableMoves(Colour.BLACK)) {
            if (availableMove.equals(whiteKingPosition)) {
                System.out.println("White king is checked at " + whiteKingPosition);
                whiteChecked = true;
                break;
            } else
                whiteChecked = false;
            }

        Point blackKingPosition = chessBoard.getKing(Colour.BLACK).getSquare();

        for (Point availableMove: chessBoard.getAllAvailableMoves(Colour.WHITE)) {
            if (availableMove.equals(blackKingPosition)) {
                System.out.println("Black king is checked at " + blackKingPosition);
                blackChecked = true;
                break;
            } else
                blackChecked = false;
        }
    }

    public boolean isChecked(Colour colour){
        if (colour.equals(Colour.WHITE))
            return whiteChecked;
        else return blackChecked;
    }

    public void processInput(Point square){

        players.get(turn).processInput(square);

        screenController.update();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    private Colour getOppositeColour(){
        if (turn.equals(Colour.WHITE))
            return Colour.BLACK;
        else
            return Colour.WHITE;
    }
}
