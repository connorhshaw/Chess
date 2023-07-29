package chessApp.model.pieces;

import chessApp.model.Colour;
import chessApp.model.Direction;
import chessApp.model.ChessBoard;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece{

    Direction direction;
    Boolean firstMove;

    public Pawn(int row, int column, Colour colour, Direction direction, ChessBoard chessBoard) {
        super(row, column, colour, chessBoard);

        piece = "pawn";
        this.direction = direction;
        firstMove = true;

    }

    @Override
    public void move(Point square){
        this.square = square;
        firstMove = false;
    }

    @Override
    public ArrayList<Point> validMoves(){

        int directionVector = -1;
        if (direction.equals(Direction.DOWN))
            directionVector = 1;

        int dist = 1;
        if (this.firstMove){
            dist = 2;
        }


        ArrayList<Point> validMoves = new ArrayList<>();

        for (int i = 1; i<= dist; i++){

            int checkRow = getRow() + (i * directionVector);
            Piece occupyingPiece = chessBoard.getPieceAt(checkRow, getColumn());

            if (occupyingPiece == null){
                validMoves.add(new Point(getColumn(), checkRow));
            } else {
                break;
            }
        }

        Piece occupyingPieceLeft = chessBoard.getPieceAt(getRow() + directionVector, getColumn()-1);
        Piece occupyingPieceRight = chessBoard.getPieceAt(getRow() + directionVector, getColumn()+1);

        if (occupyingPieceLeft != null && !occupyingPieceLeft.getColour().equals(this.getColour())) {
            validMoves.add(new Point(getColumn() - 1, getRow() + directionVector));
        }
        if (occupyingPieceRight != null&& !occupyingPieceRight.getColour().equals(this.getColour())){
            validMoves.add(new Point(getColumn() + 1, getRow() + directionVector));
        }

        return validMoves;
    }
}
