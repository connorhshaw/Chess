package chessApp.model.pieces;

import chessApp.model.Colour;
import chessApp.model.ChessBoard;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(int row, int column, Colour colour, ChessBoard chessBoard) {
        super(row, column, colour, chessBoard);

        piece = "bishop";

    }

    @Override
    public ArrayList<Point> validMoves(){

        return validMovesAllDiagonals(false);

    }




}
