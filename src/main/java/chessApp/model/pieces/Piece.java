package chessApp.model.pieces;

import chessApp.model.Colour;
import chessApp.model.ChessBoard;

import java.awt.*;
import java.util.ArrayList;

public class Piece {

    private int row;
    private int column;
    private Colour colour;

    protected Point square, lastSquare;
    protected ChessBoard chessBoard;

    protected String piece;

    public Piece(int row, int column, Colour colour, ChessBoard chessBoard){
        this.row = row;
        this.column = column;
        this.colour = colour;
        this.square = new Point(column, row);
        this.lastSquare = square;
        this.chessBoard = chessBoard;
    }

    public ArrayList<Point> validMoves(){
        return null;
    }

    public void capture(){

    }

    public void move(Point square){
        lastSquare = this.square;
        this.square = square;
    }

    public void undoMove(){
        this.square = lastSquare;
    }

    protected ArrayList<Point> validMovesInDiagonal(int xDir, int yDir, int distX, int distY, boolean limited){

        ArrayList<Point> validMovesInDirection = new ArrayList<>();

        if (limited && distX > 0){
            distX = 1;
        }

        if (limited && distY > 0){
            distY = 1;
        }

        for (int i = 1; i<= smallestDistance(distX, distY); i++){

            int checkRow = getRow() + (i * yDir);
            int checkColumn = getColumn() + (i * xDir);
            Piece occupyingPiece = chessBoard.getPieceAt(checkRow, checkColumn);

            if (occupyingPiece == null){
                validMovesInDirection.add(new Point(checkColumn, checkRow));
            } else if (this.getColour().equals(occupyingPiece.getColour())){
                break;
            } else {
                validMovesInDirection.add(new Point(checkColumn, checkRow));
                break;
            }

        }

        return validMovesInDirection;
    }

    protected ArrayList<Point> validMovesAllDiagonals(boolean limited){
        ArrayList<Point> validMoves = new ArrayList<>();

        int distFromLeft = getColumn();
        int distFromRight = 7-getColumn();
        int distFromTop = getRow();
        int distFromBottom = 7-getRow();

        validMoves.addAll(validMovesInDiagonal(-1, -1, distFromLeft, distFromTop, limited));
        validMoves.addAll(validMovesInDiagonal(1, 1, distFromRight, distFromBottom, limited));
        validMoves.addAll(validMovesInDiagonal(1, -1, distFromRight, distFromTop, limited));
        validMoves.addAll(validMovesInDiagonal(-1, 1, distFromLeft, distFromBottom, limited));

        return validMoves;
    }

    private ArrayList<Point> validMovesInRow(int dir, int dist, boolean limited){
        ArrayList<Point> validMovesInDirection = new ArrayList<>();

        if (limited && dist > 0){
            dist = 1;
        }

        for (int i = 1; i<= dist; i++){

            int checkColumn = getColumn() + (i * dir);
            Piece occupyingPiece = chessBoard.getPieceAt(getRow(), checkColumn);

            if (occupyingPiece == null){
                validMovesInDirection.add(new Point(checkColumn, getRow()));
            } else if (this.getColour().equals(occupyingPiece.getColour())){
                break;
            } else {
                validMovesInDirection.add(new Point(checkColumn, getRow()));
                break;
            }

        }

        return validMovesInDirection;
    }

    protected ArrayList<Point> validMovesInColumn(int dir, int dist, int limited){
        ArrayList<Point> validMovesInDirection = new ArrayList<>();

        if (limited > 0 && dist > 0){
            dist = limited;
        }

        for (int i = 1; i<= dist; i++){

            int checkRow = getRow() + (i * dir);
            Piece occupyingPiece = chessBoard.getPieceAt(checkRow, getColumn());

            if (occupyingPiece == null){
                validMovesInDirection.add(new Point(getColumn(), checkRow));
            } else if (this.getColour().equals(occupyingPiece.getColour())){
                break;
            } else {
                validMovesInDirection.add(new Point(getColumn(), checkRow));
                break;
            }

        }

        return validMovesInDirection;
    }

    protected ArrayList<Point> validMovesWholeRow(boolean limited){
        ArrayList<Point> validMoves = new ArrayList<>();

        int distFromLeft = getColumn();
        int distFromRight = 7-getColumn();

        validMoves.addAll(validMovesInRow(-1, distFromLeft, limited));
        validMoves.addAll(validMovesInRow(1, distFromRight, limited));

        return validMoves;
    }

    protected ArrayList<Point> validMovesWholeColumn(int limited){
        ArrayList<Point> validMoves = new ArrayList<>();

        int distFromTop = getRow();
        int distFromBottom = 7-getRow();

        validMoves.addAll(validMovesInColumn(-1, distFromTop, limited));
        validMoves.addAll(validMovesInColumn(1, distFromBottom, limited));

        return validMoves;
    }

    protected ArrayList<Point> validMovesAllStraights(boolean limited){
        return null;
    }

    private int smallestDistance(int dist1, int dist2){
        if (dist1 < dist2)
            return dist1;
        else return dist2;
    }

    public Colour getColour() {
        return colour;
    }

    public String getPiece() {
        return piece;
    }

    public Point getSquare(){
        return square;
    }

    public int getRow() {
        return square.y;
    }

    public int getColumn() {
        return square.x;
    }


}
