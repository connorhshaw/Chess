# Chess Game

This is a java-based chess game built with a GUI, graphics and basic game funcitonality. The purpose was to demonstrate object oriented concepts which is shown in the Piece and Player classes. The MVC design was used, and so Model, View and Controller all handle separate parts of this application.

Features I would like to add in future:
- Move recording
- Position upload/download to add puzzle functionality
- Castling, promotion and checkmate
- State control to allow for menu screens
- Develop a ComputerPlayer class to call an AI chess API

Bugs still to fix:
- Upon checking a king and attempting a move, pawns can lose their 2-square first-move advantage

There were many lessons learned creating this project. If I were to restart the project I would:
- Further separate the MVC elements so they are more self contained and easier to switch out if required in future
- Implement checking functionality better, by predicting valid future legal moves instead of simply undoing an illegal moves
- Shift game rule functionality into the ChessBoard class, leaving this more self contained
- Standardise coordinate systems instead of switching from row/column to x/y


