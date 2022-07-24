package com.example.chess.pieces;

import com.example.chess.ChessBoard;
import com.example.chess.Piece;
import com.example.chess.Player;

import java.util.ArrayList;

public class King implements Piece {
    private Player team;
    private ChessBoard board;
    private ArrayList<int[]> coveredTiles;
    private int tMulti;
    private boolean hasMoved;
    private double value;

    public King(Player team, double value){
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
        while(inc++<1){

            int[] temp = new int[]{cPos[0]+(inc*tMulti),cPos[1]+(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }

        inc = 0;
        while(inc++<1){

            int[] temp = new int[]{cPos[0]-(inc*tMulti),cPos[1]-(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc = 0;
        while(inc++<1){

            int[] temp = new int[]{cPos[0]-(inc*tMulti),cPos[1]+(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc = 0;
        while(inc++<1){

            int[] temp = new int[]{cPos[0]+(inc*tMulti),cPos[1]-(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc=0;
        while(inc++<1){

            int[] temp = new int[]{cPos[0]+(inc*tMulti),cPos[1]};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc = 0;
        while(inc++<1){

            int[] temp = new int[]{cPos[0]-(inc*tMulti),cPos[1]};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team) { break; }
            break;
        }
        inc = 0;
        while(inc++<5){

            int[] temp = new int[]{cPos[0],cPos[1]+(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { possibleMoves.add(temp); continue; }
            if(board.getTile(temp).getTeam() != team) { possibleMoves.add(temp); break; }
            if(board.getTile(temp).getTeam() == team && board.getTile(temp).hasMoved() == false && board.getTile(temp) instanceof Rook)  { possibleMoves.add(new int[]{cPos[0],2}); }
            else { break; }
            break;
        }
        inc = 0;
        while(inc++<5){

            int[] temp = new int[]{cPos[0],cPos[1]-(inc*tMulti)};
            if(!isAllowed(temp)) { break; }
            if(board.getTile(temp) == null) { if(inc<=1) {possibleMoves.add(temp);} continue; }
            if(board.getTile(temp).getTeam() != team) { if(inc<=1) {possibleMoves.add(temp);} break; }
            if(board.getTile(temp).getTeam() == team && board.getTile(temp).hasMoved() == false && board.getTile(temp) instanceof Rook) { possibleMoves.add(new int[]{cPos[0],6}); }
            else { break; }
            break;
        }
        if(!hasMoved){
            Piece rook1 = board.getTile(new int[]{7,7});
            Piece rook2 = board.getTile(new int[]{7,0});

        }
        coveredTiles = possibleMoves;
        return possibleMoves;
    }
    @Override
    public String toString(){
        if(tMulti>0){
            return "▪K▪";
        } else return "▫K▫";
    }
    @Override
    public boolean hasMoved(){
        return hasMoved;
    }
    public void update(){
        hasMoved = true;
    }
    public boolean isAllowed(int[] pos){
        boolean isIt = true;
        if(pos[0]>7 || pos[0]<0) isIt = false;
        if(pos[1]>7 || pos[1]<0) isIt = false;
        return isIt;
    }
}
