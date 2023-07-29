package chessApp.model.pieces;

import chessApp.model.Colour;
import chessApp.model.ChessBoard;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(int row, int column, Colour colour, ChessBoard chessBoard) {
        super(row, column, colour, chessBoard);

        piece = "queen";

    }

    @Override
    public ArrayList<Point> validMoves(){
        ArrayList<Point> validMoves = new ArrayList<>();

        validMoves.addAll(validMovesWholeRow(false));
        validMoves.addAll(validMovesWholeColumn(0));
        validMoves.addAll(validMovesAllDiagonals(false));
        return validMoves;
    }

}
