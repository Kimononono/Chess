package com.example.chess.pieces;

import com.example.chess.ChessBoard;
import com.example.chess.Piece;
import com.example.chess.Player;

import java.util.ArrayList;

public class Queen implements Piece {
    private Player team;
    private ChessBoard board;
    private ArrayList<int[]> coveredTiles;
    private int tMulti;
    private boolean hasMoved;
    private double value;

    public Queen(Player team, double value){
        this.team = team;
        tMulti = -1;
        if(team.isWhite()) tMulti = 1;
        hasMoved = false;
        this.value = value;
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
        int inc=0;
        while(inc++<15){

            int[] temp = new int[]{cPos[0]+(inc*tMulti),cPos[1]+(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }

        inc = 0;
        while(inc++<10){

            int[] temp = new int[]{cPos[0]-(inc*tMulti),cPos[1]-(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc = 0;
        while(inc++<15){

            int[] temp = new int[]{cPos[0]-(inc*tMulti),cPos[1]+(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc = 0;
        while(inc++<15){

            int[] temp = new int[]{cPos[0]+(inc*tMulti),cPos[1]-(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc=0;
        while(inc++<10){

            int[] temp = new int[]{cPos[0]+(inc*tMulti),cPos[1]};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc = 0;
        while(inc++<10){

            int[] temp = new int[]{cPos[0]-(inc*tMulti),cPos[1]};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc = 0;
        while(inc++<10){

            int[] temp = new int[]{cPos[0],cPos[1]+(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc = 0;
        while(inc++<10){

            int[] temp = new int[]{cPos[0],cPos[1]-(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        coveredTiles = possibleMoves;
        return possibleMoves;
    }
    @Override
    public String toString(){
        if(tMulti>0){
            return "▪Q▪";
        } else return "▫Q▫";
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
}
