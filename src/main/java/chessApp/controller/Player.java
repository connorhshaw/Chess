package chessApp.controller;

import chessApp.model.Colour;
import chessApp.model.pieces.Piece;

import java.awt.*;

public interface Player {

    void makeMove(Piece piece, Point square);

    void processInput(Point square);

    Colour getColour();

}
