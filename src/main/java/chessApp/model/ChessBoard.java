package chessApp.model;

import chessApp.model.pieces.*;

import java.awt.*;
import java.util.ArrayList;

public class ChessBoard {

    ArrayList<Piece> pieces;
    Piece selectedPiece;

    public ChessBoard() {

        pieces = new ArrayList<>();
        setUpPieces();

    }

    private void setUpPieces(){

        for (int i = 0; i < 8; i++){
            pieces.add(new Pawn(6, i, Colour.WHITE, Direction.UP, this));
            pieces.add(new Pawn(1, i, Colour.BLACK, Direction.DOWN,this));
        }

        //White pieces
        pieces.add(new Rook(7, 0, Colour.WHITE, this));
        pieces.add(new Knight(7, 1, Colour.WHITE, this));
        pieces.add(new Bishop(7, 2, Colour.WHITE, this));
        pieces.add(new Queen(7, 3, Colour.WHITE, this));
        pieces.add(new King(7, 4, Colour.WHITE, this));
        pieces.add(new Bishop(7, 5, Colour.WHITE, this));
        pieces.add(new Knight(7, 6, Colour.WHITE, this));
        pieces.add(new Rook(7, 7, Colour.WHITE, this));

        //Black pieces
        pieces.add(new Rook(0, 0, Colour.BLACK, this));
        pieces.add(new Knight(0, 1, Colour.BLACK, this));
        pieces.add(new Bishop(0, 2, Colour.BLACK, this));
        pieces.add(new Queen(0, 3, Colour.BLACK, this));
        pieces.add(new King(0, 4, Colour.BLACK, this));
        pieces.add(new Bishop(0, 5, Colour.BLACK, this));
        pieces.add(new Knight(0, 6, Colour.BLACK, this));
        pieces.add(new Rook(0, 7, Colour.BLACK, this));

        selectedPiece = getPieceAt(4, 2);
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
    }

    public Piece getPieceAt(int row, int column){

        Point squareToCheck = new Point(column, row);

        for (Piece piece:
             pieces) {
            if (piece.getSquare().equals(squareToCheck)){
                return piece;
            }
        }
        return null;
    }

    public ArrayList<Piece> getColouredPieces(Colour colour){
        ArrayList<Piece> colouredPieces = new ArrayList<>();
        for (Piece piece:
        pieces){
            if (piece.getColour().equals(colour))
                colouredPieces.add(piece);
        }
        return colouredPieces;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void addPiece(Piece piece){
        pieces.add(piece);
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public Piece getKing(Colour colour){
        for (Piece piece:
        getColouredPieces(colour)){
            if (piece.getPiece().equals("king"))
                return piece;
        }
        return null;
    }

    public ArrayList<Point> getAllAvailableMoves(Colour colour){
        ArrayList<Point> availableMoves = new ArrayList<>();

        for (Piece piece:
        getColouredPieces(colour)){
            availableMoves.addAll(piece.validMoves());
        }
        return availableMoves;
    }
}
