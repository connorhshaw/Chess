package chessApp.model.pieces;

import chessApp.model.Colour;
import chessApp.model.ChessBoard;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(int row, int column, Colour colour, ChessBoard chessBoard) {
        super(row, column, colour, chessBoard);

        piece = "rook";

    }

    @Override
    public ArrayList<Point> validMoves(){

        ArrayList<Point> validMoves = new ArrayList<>();

        validMoves.addAll(validMovesWholeRow(false));
        validMoves.addAll(validMovesWholeColumn(0));

        return validMoves;
    }

}
