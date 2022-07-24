package com.example.chess;

import com.example.chess.pieces.Empty;

import java.util.ArrayList;

public interface Piece  {

    Player getTeam();
    ArrayList<int[]> cycleMoves(int[] currentPos, ChessBoard board);
    String toString();
    double getValue();
    void update();
    boolean hasMoved();
}
