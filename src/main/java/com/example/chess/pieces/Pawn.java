package com.example.chess.pieces;

import com.example.chess.ChessBoard;
import com.example.chess.Piece;
import com.example.chess.Player;

import java.util.ArrayList;

public class Pawn implements Piece {
    private Player team;
    private ChessBoard board;
    private ArrayList<int[]> coveredTiles;
    private int tMulti;
    private boolean hasMoved;
    private double value;
    private boolean enPassanted;
    private int[] passantCapture;

    public Pawn(Player team, double value){
        this.team = team;
        tMulti = -1;
        if(team.isWhite()) tMulti = 1;
        hasMoved = false;
        this.value = value;
        enPassanted = false;
        passantCapture = null;
    }

    @Override
    public Player getTeam(){
        return team;
    }
    @Override
    public double getValue() { return value; }
    @Override
    public ArrayList<int[]> cycleMoves(int[] cPos, ChessBoard board) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        int[] leftAttack= new int[]{cPos[0]-tMulti, cPos[1]-tMulti};
        int[] rightAttack = new int[]{cPos[0]-tMulti, cPos[1]+tMulti};
        int[] norm = new int[]{cPos[0]-tMulti, cPos[1]};
        int[] startNorm = new int[]{cPos[0]-(2*tMulti), cPos[1]};
        int[] leftPassant= new int[]{cPos[0], cPos[1]-tMulti};
        int[] rightPassant = new int[]{cPos[0], cPos[1]+tMulti};

        if(isAllowed(leftAttack)
                && board.getTile(leftAttack) != null
                && board.getTile(leftAttack).getTeam() != team)
        {
            possibleMoves.add(leftAttack);
            if(board.getTile(leftPassant) != null
                    && board.getTile(leftPassant).getTeam() != team
                    && board.getTile(leftPassant) instanceof Pawn
                    && ((Pawn) board.getTile(leftPassant)).checkPassant())
            {
                passantCapture = leftPassant;
            }
        }
        if(isAllowed(leftAttack)
                && board.getTile(leftAttack) == null)

        {
            if(board.getTile(leftPassant) != null
                    && board.getTile(leftPassant).getTeam() != team
                    && board.getTile(leftPassant) instanceof Pawn
                    && ((Pawn) board.getTile(leftPassant)).checkPassant())
            {
                possibleMoves.add(leftAttack);
                passantCapture = leftPassant;
            }
        }
        if(isAllowed(rightAttack)
                &&  board.getTile(rightAttack) != null
                && board.getTile(rightAttack).getTeam() != team)
        {
            possibleMoves.add(rightAttack);
            if(board.getTile(rightPassant) != null
                    && board.getTile(rightPassant).getTeam() != team
                    && board.getTile(rightPassant) instanceof Pawn
                    && ((Pawn) board.getTile(rightPassant)).checkPassant())
            {
                passantCapture = rightPassant;
            }
        }
        if(isAllowed(rightAttack)
                &&  board.getTile(rightAttack) == null)

        {
            if(board.getTile(rightPassant) != null
                    && board.getTile(rightPassant).getTeam() != team
                    && board.getTile(rightPassant) instanceof Pawn
                    && ((Pawn) board.getTile(rightPassant)).checkPassant())
            {
                possibleMoves.add(rightAttack);
                passantCapture = rightPassant;
            }
        }
        if(isAllowed(norm) && board.getTile(norm) == null) possibleMoves.add(norm);
        if(isAllowed(startNorm) && !hasMoved && board.getTile(startNorm) == null && board.getTile(norm) == null) { possibleMoves.add(startNorm); enPassanted = true; }
        coveredTiles = possibleMoves;
        return possibleMoves;
    }

    @Override
    public String toString(){
        if(tMulti>0){
            return "▪P▪";
        } else return "▫P▫";
    }
    public void update(){
        hasMoved = true;
    }
    @Override
    public boolean hasMoved(){
        return hasMoved;
    }
    public boolean isAllowed(int[] pos){
        boolean isIt = true;
        if(pos[0]>7 || pos[0]<0) isIt = false;
        if(pos[1]>7 || pos[1]<0) isIt = false;
        return isIt;
    }
    public boolean checkPassant(){
        return enPassanted;
    }
    public void setPassant(boolean b){
        enPassanted = b;
    }
    public int[] isDoPassant(){
        return passantCapture;
    }
    public void resetDoPassant(){
        passantCapture = null;
    }

}
