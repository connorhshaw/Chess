package chessApp.controller;

import chessApp.model.ChessGame;
import chessApp.model.Colour;
import chessApp.model.ChessBoard;
import chessApp.model.pieces.Piece;

import java.awt.*;

public class HumanPlayer implements Player {

    ChessGame chessGame;
    ChessBoard chessBoard;
    Colour colour;

    public HumanPlayer(ChessBoard chessBoard, Colour colour, ChessGame chessGame) {
        this.chessGame = chessGame;
        this.chessBoard = chessBoard;
        this.colour = colour;
    }

    @Override
    public void makeMove(Piece piece, Point square) {
        System.out.println("make move to "+square.toString());
        chessGame.addMove(piece, square);
        chessBoard.setSelectedPiece(null);
    }

    @Override
    public void processInput(Point square) {
        Piece selectedPiece = chessBoard.getSelectedPiece();
        Piece pieceAtSquare = chessBoard.getPieceAt(square.y, square.x);

        boolean pieceAtSquareSameColour = false;

        // if we dont have a piece selected, choose one if it's the same colour
        // if we have a piece selected, test our available moves and make one, or allow reselection if same colour, or unselect
        // otherwise

        if (selectedPiece == null) {
            if (pieceAtSquare == null){
                chessBoard.setSelectedPiece(pieceAtSquare);
            } else if (pieceAtSquare.getColour().equals(this.getColour())){
                chessBoard.setSelectedPiece(pieceAtSquare);
            } else{
                chessBoard.setSelectedPiece(null);
            }
        } else if (selectedPiece.validMoves() != null){
            if (selectedPiece.validMoves().contains(square)) {
                makeMove(selectedPiece, square);
            } else if (pieceAtSquare == null){
                chessBoard.setSelectedPiece(null);
            } else if (pieceAtSquare.equals(selectedPiece)) {
                chessBoard.setSelectedPiece(null);
            }
            else if (pieceAtSquare.getColour().equals(this.getColour())){
                chessBoard.setSelectedPiece(pieceAtSquare);
            } else{
                chessBoard.setSelectedPiece(null);
            }
        } else{
            chessBoard.setSelectedPiece(null);
        }
    }

    @Override
    public Colour getColour() {
        return this.colour;
    }
}
