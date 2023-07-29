package chessApp.model.pieces;

import chessApp.model.Colour;
import chessApp.model.ChessBoard;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(int row, int column, Colour colour, ChessBoard chessBoard) {
        super(row, column, colour, chessBoard);

        piece = "knight";

    }

    @Override
    public ArrayList<Point> validMoves() {

        ArrayList<Point> validMoves = new ArrayList<>();

        int distFromLeft = getColumn();
        int distFromRight = 7 - getColumn();
        int distFromTop = getRow();
        int distFromBottom = 7 - getRow();

        //for each direciton if within bounds and not same piece, add to validmoves

        int[] jumpCoords = {1, -1, 2, -2};

        for (int i: jumpCoords){
            for (int j: jumpCoords){
                if (getColumn()+i < 8 && getColumn()+1 >= 0 && getRow()+j < 8 && getRow() >=0 && Math.abs(i)+Math.abs(j) == 3){
                    Point jump = validJumpMove(j, i);
                    if (jump != null)
                        validMoves.add(jump);
                }
            }
        }
        System.out.println(validMoves.toString());
        return validMoves;
    }

    private Point validJumpMove(int rowJump, int columnJump){
        Piece occupyingPiece = chessBoard.getPieceAt(getRow()+rowJump, getColumn()+columnJump);
        if (occupyingPiece != null && !occupyingPiece.getColour().equals(this.getColour()))
            return new Point(getColumn()+columnJump, getRow()+rowJump);
        else if (occupyingPiece == null){
            return new Point(getColumn()+columnJump, getRow()+rowJump);
        } else return null;
    }

}
