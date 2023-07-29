package chessApp.model.pieces;

import chessApp.model.Colour;
import chessApp.model.ChessBoard;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece{

    public King(int row, int column, Colour colour, ChessBoard chessBoard) {
        super(row, column, colour, chessBoard);

        piece = "king";

    }

    @Override
    public ArrayList<Point> validMoves(){

        ArrayList<Point> validMoves = new ArrayList<>();

        validMoves.addAll(validMovesWholeRow(true));
        validMoves.addAll(validMovesWholeColumn(1));
        validMoves.addAll(validMovesAllDiagonals(true));
        return validMoves;
    }

}
