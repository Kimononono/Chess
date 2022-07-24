package com.example.chess;

import com.example.chess.pieces.Pawn;

import java.util.ArrayList;

public class Player {
    final private ChessEngine engine;
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> captured;
    private boolean isWhite;
    final private double[] values = new double[]{5,5,5,5,5,5};
    public Player(boolean isWhite, ChessEngine engine){
        //pieces = new Piece[]{new Pawn(0,0,this)};
        this.isWhite=isWhite;
        this.engine = engine;
        pieces = new ArrayList<>();
        captured = new ArrayList<>();
    }
    //public ArrayList<int[]> getMoves(int[] currentPos){
    //    Piece selectedPiece = engine.getBoard().getTile(currentPos);
    //    return selectedPiece.cycleMoves(currentPos, boa);
    //}

    public boolean isWhite() { return isWhite;}
    public void addPiece(Piece piece){
        pieces.add(piece);
    }
    public Piece checkBoard(int[] pos){
        Piece piece = engine.getBoard().getTile(pos);
        return piece;
    }
    public double[] getValuesArray(){
        return values;
    }
    public String toString(){
        if(isWhite) return "White";
        else return "Black";
    }

}
